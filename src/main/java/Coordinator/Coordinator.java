package Coordinator;

import Model.Connection;
import Model.CoordinatorRequest;
import Model.CoordinatorRequest.*;
import Model.Endpoint;
import Model.ResourceType;
import Resource.Resource;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Coordinator implements Runnable {
    public static final int PORT = 11111;
    private ServerSocket ss = null;
    private HashMap<ResourceType, Queue<Request>> resourceRequestQueues = null;
    private int currentSequenceNumber = 0;
    private RequestQueue UI = null;
    private Timer timeoutTimer = new Timer();
    private final long timeoutDuration = 10000;

    public static class Request {
        Integer sequenceNumber;
        Endpoint endpoint;
        AccessMode accessMode;
        Date timestamp;
        TimerTask timerTask;

        public Request(Integer sequenceNumber, Endpoint endpoint, AccessMode accessMode) {
            this.sequenceNumber = sequenceNumber;
            this.endpoint = endpoint;
            this.accessMode = accessMode;
            this.timestamp = new Date();
        }

        public TimerTask getTimerTask() {
            return timerTask;
        }

        public Integer getSequenceNumber() {
            return sequenceNumber;
        }

        public Endpoint getEndpoint() {
            return endpoint;
        }

        public AccessMode getAccessMode() {
            return accessMode;
        }

        public String getTimestamp() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        }

        @Override
        public String toString() {
            return "Request{" +
                    "sequenceNumber=" + sequenceNumber +
                    ", timestamp=" + getTimestamp() +
                    ", accessMode=" + accessMode +
                    '}';
        }
    }

    public Coordinator(RequestQueue UI) {
        this.UI = UI;
        initializeQueues();
        timeoutTimer = new Timer();
    }

    private void initializeQueues() {
        resourceRequestQueues = new HashMap<>();
        for (ResourceType resourceType : ResourceType.values()) {
            resourceRequestQueues.put(resourceType, new LinkedList<>());
        }
    }

    @Override
    public void run() {
        bindServer();
        if (ss == null)
            return;

        startServer();
    }

    private void bindServer() {
        try {
            ss = new ServerSocket(PORT);
            System.out.println("Bound server to port: " + PORT);
        } catch (IOException ex) {
            System.out.println("Unable to bind server");
            ss = null;
        }
    }

    private void startServer() {
        System.out.println("Coordinator server listening");
        while (true) {
            try {
                Socket connection = ss.accept();
                Thread thread = new Thread(new SocketHandler(connection));
                thread.start();
            } catch (IOException ex) {
                System.out.println("Error accepting connection");
            }
        }
    }


    private class SocketHandler implements Runnable {
        private static final Object Lock = new Object();

        private Socket socket = null;
        private Connection c = null;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                c = new Connection(socket);
            } catch (IOException e) {
                System.out.println("Unable to establish connection");
                return;
            }

            CoordinatorRequest request;
            try {
                Object rawRequest = c.in.readObject();
                if (!(rawRequest instanceof CoordinatorRequest)) {
                    throw new IOException("Invalid request");
                }
                request = (CoordinatorRequest) rawRequest;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Unable to read request");
                return;
            }

            switch (request.operationMode) {
                case ACQUIRE:
                    try {
                        handleAcquire(request);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case RELEASE:
                    handleRelease(request);
                    break;
            }

            try {
                c.disconnect();
            } catch (IOException e) {
                System.out.println("Unable to disconnect");
            }
        }

        private void handleRelease(CoordinatorRequest request) {
            Queue<Request> queue = resourceRequestQueues.get(request.resourceType);

            synchronized (queue) {
                Request front = queue.poll();
                if (front != null) {
                    if (front.timerTask != null) {
                        front.timerTask.cancel();
                    }
                    System.out.println("Removing " + front);
                }
                System.out.println("release " + request.resourceType + " : " + queue);
                queue.notifyAll();
                UI.updateRequestsTable(request.resourceType, queue);
            }
        }

        private void handleAcquire(CoordinatorRequest request) throws IOException {
            int sequenceNumber;
            Queue<Request> queue = Coordinator.this.resourceRequestQueues.get(request.resourceType);

            synchronized (queue) {
                sequenceNumber = currentSequenceNumber;
                incrementSequenceNumber();

                Request newRequest = new Request(sequenceNumber, new Endpoint(c.socket), request.accessMode);
                queue.add(newRequest);
                UI.updateRequestsTable(request.resourceType, queue);
                System.out.println("request " + request.resourceType + " : " + queue);
                if (request.accessMode == AccessMode.WRITE) {
                    while (queue.peek().getSequenceNumber() != sequenceNumber) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted thread " + Thread.currentThread().getName());
                        }
                    }
                } else {
                    while (queue.peek().getAccessMode() == AccessMode.WRITE) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted thread " + Thread.currentThread().getName());
                        }
                    }
                }

                TimerTask timeoutTask = new TimerTask() {
                    @Override
                    public void run() {
                        synchronized (queue) {
                            if (!queue.isEmpty() && queue.peek() == newRequest) {
                                queue.poll();
                                System.out.println("TIMEOUT Removing " + newRequest);
                                System.out.println("TIMEOUT release " + request.resourceType + " : " + queue);
                                UI.updateRequestsTable(request.resourceType, queue);
                                queue.notifyAll();
                            }
                        }
                    }
                };
                newRequest.timerTask = timeoutTask;
                timeoutTimer.schedule(timeoutTask, timeoutDuration);
            }

            c.writeObject(Resource.ENDPOINTS.get(request.resourceType));
        }

        private void incrementSequenceNumber() {
            currentSequenceNumber = (currentSequenceNumber + 1) % Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
//        new Coordinator();
    }
}
      
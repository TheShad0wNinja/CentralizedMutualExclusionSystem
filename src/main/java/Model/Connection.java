package Model;

import java.io.*;
import java.net.Socket;

public class Connection {
    public Socket socket;
    public ObjectInputStream in = null;
    public ObjectOutputStream out = null;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(this.socket.getOutputStream());
        in = new ObjectInputStream(this.socket.getInputStream());
    }

    public Connection(Endpoint endpoint) throws IOException {
        this(new Socket(endpoint.host, endpoint.port));
    }

    public Connection(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public void writeUTF(String msg) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }

    public void writeObject(Object obj) throws IOException {
        out.writeObject(obj);
        out.flush();
    }

    public void disconnect() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    @Override
    public String toString() {
        return "Connection{" + socket + '}';
    }
}

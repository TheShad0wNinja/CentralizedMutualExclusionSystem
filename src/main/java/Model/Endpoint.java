package Model;

import java.io.Serializable;
import java.net.Socket;

public class Endpoint implements Serializable {
    public String host;
    public int port;

    public Endpoint(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Endpoint(Socket s) {
        this.host = s.getInetAddress().getHostName();
        this.port = s.getPort();
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}

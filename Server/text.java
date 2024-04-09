package Server;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class text {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public text() {
    }

    public boolean ConnectProcess() {
        try {
            InetAddress add = InetAddress.getByName("localhost");
            String hostAddress = add.getHostAddress();
            socket = new Socket(hostAddress, 8888);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            new ClientReaderThread(socket, messageQueue).start(); // Start a thread that reads server messages
            return true;
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return false;
        }
    }

    public boolean disConnectProcess() {
        try {
            if (socket != null && !socket.isClosed()) {
                dos.close();
                dis.close();
                socket.close();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Disconnection failed: " + e.getMessage());
            return false;
        }
    }

    public String getAddress() {
        return socket != null ? socket.getInetAddress().toString() : "Not connected";
    }

    public String getHost() {
        return socket != null ? String.valueOf(socket.getPort()) : "Not connected";
    }

    public String getAIText(String inputText) throws InterruptedException {
        if (socket == null || !socket.isConnected()) {
            return "Sorry, not connected to the server.";
        } else {
            try {
                dos.writeUTF(inputText);
                dos.flush();
                return messageQueue.take(); // Get the server's response from the message queue
            } catch (IOException e) {
                //System.out.println("Error in communication: " + e.getMessage());
                return "Sorry, not connected to the server.";
            }
        }
    }
}

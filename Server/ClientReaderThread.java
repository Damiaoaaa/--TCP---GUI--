package Server;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;

public class ClientReaderThread extends Thread {
    private Socket socket;
    private BlockingQueue<String> messageQueue;

    public ClientReaderThread(Socket socket, BlockingQueue<String> messageQueue) {
        this.socket = socket;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             DataInputStream dis = new DataInputStream(is)) {
            while (true) { // Here is an infinite loop that continues to listen for messages from the client
                try {
                    String msg = dis.readUTF(); // Read messages sent from the client
                    messageQueue.put(msg); // Put messages into queue for processing by other threads
                } catch (InterruptedException e) {
                    // If the thread is interrupted, print exception information and exit the loop
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (SocketException e) {
            // If a SocketException is caught, it usually means the connection was interrupted or lost
            System.out.println("Connection closed: " + socket.getRemoteSocketAddress());
        } catch (IOException e) {
            // Handling other types of IO exceptions
            System.out.println("Error handling client at " + socket.getRemoteSocketAddress());
            e.printStackTrace();
        } finally {
            // Close the socket resource in the final finally block
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

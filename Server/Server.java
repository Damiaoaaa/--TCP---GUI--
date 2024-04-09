package Server;



import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket>onLineSockets=new ArrayList<>();
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8888);
        while (true) {
            Socket socket=serverSocket.accept();
            onLineSockets.add(socket);
            System.out.println(socket.getRemoteSocketAddress()+" comming");
            new ServerReaderThread(socket).start();
        }
    }
}
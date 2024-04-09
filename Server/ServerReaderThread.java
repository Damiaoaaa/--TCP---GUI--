package Server;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import MusicPlayer.MusicPlayer;

public class ServerReaderThread extends Thread {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int n = 0;
    private MusicPlayer music;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
        this.music = new MusicPlayer();

        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            
            while (true) {
            	//n++;
                String msg = dis.readUTF();
                if (msg != null && !msg.isEmpty()) {
                    if (msg.equals("y") ) {
                        sendMegToAll("Please enter the story you want to know.(1.The tinder box,2.little tuk,3.god can never die,\n"
                        		+ "4.dance,dance,doll of mine,5.croak!,6.a rose from the grave of homer)");
                    }else if (msg.equals("n")) {
                       // n = 0;
                        sendMegToAll("Maybe you don't want to hear the story now.\n"
                        		+ "But if you change your mind, please enter \"y\" let me know.");
                    }//reset
                    else if(msg.equals("The tinder box")||msg.equals("1")||msg.equals("1.The tinder box")) {
                    	music.stop();
                    	music.play("musics/The tinder box.wav");
                    	getFile("The tinder box");
                    }else if(msg.equals("little tuk")||msg.equals("2")||msg.equals("2.little tuk")) {
                    	music.stop();
                    	music.play("musics/little tuk.wav");
                    	getFile("little tuk");
                    }else if(msg.equals("god can never die")||msg.equals("3")||msg.equals("3.god can never die")) {
                    	music.stop();
                    	music.play("musics/god can never die.wav");
                    	getFile("god can never die");
                    }else if(msg.equals("dance,dance,doll of mine")||msg.equals("4")||msg.equals("4.dance,dance,doll of mine")) {
                    	music.stop();
                    	music.play("musics/dance,dance,doll of mine.wav");
                    	getFile("dance,dance,doll of mine");
                    }else if(msg.equals("croak!")||msg.equals("5")||msg.equals("5.croak!")) {
                    	music.stop();
                    	music.play("musics/croak!.wav");
                    	getFile("croak!");
                    }else if(msg.equals("a rose from the grave of homer")||msg.equals("6")||msg.equals("6.a rose from the grave of homer")) {
                    	music.stop();
                    	music.play("musics/a rose from the grave of homer.wav");
                    	getFile("a rose from the grave of homer");
                    }
                    else if(msg.equals("exit")) {
                    	music.stop();
                    	sendMegToAll("I will also look forward to seeing you next time.");
                    	Server.onLineSockets.remove(socket); // Here you need to define onLineSockets in the Server class
                        dis.close();
                        dos.close();
                        socket.close();
                    }
                    else {
                        sendMegToAll("I'm sorry, I do not understand what you mean. \n"
                        		+ "Please follow the prompts to enter the correct information.");
                    }
                    
                    
                } else {
                    sendMegToAll("Please enter valid information.");
                }
            }
        } catch (EOFException e) {
        	music.stop();
            System.out.println(socket.getRemoteSocketAddress() + " Offline");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Suppose there is a Server class that manages online sockets
                Server.onLineSockets.remove(socket); // Here you need to define onLineSockets in the Server class
                dis.close();
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMegToAll(String msg) throws IOException {
        for (Socket onLineSocket : Server.onLineSockets) { // Here you also need to define onLineSockets in the Server class
            if (!onLineSocket.isClosed()) {
                new DataOutputStream(onLineSocket.getOutputStream()).writeUTF(msg);
            }
        }
    }
    private void getFile(String storyName) {
        File file = new File("story/"+storyName + ".txt");
        
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            StringBuilder message = new StringBuilder();
            while (scanner.hasNextLine()) {
                message.append(scanner.nextLine()).append("\n");
            }
            message.append("\nPlease select the story you want to know again \n"
            		+ "(1.The tinder box,2.little tuk,3.god can never die,\n"
            		+ "4.dance,dance,doll of mine,5.croak!,6.a rose from the grave of homer).\n"
            		+ "\nIf you change your mind, please enter \"exit\".");
            sendMegToAll(message.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + file.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while sending message");
            e.printStackTrace();
        }
    }
}

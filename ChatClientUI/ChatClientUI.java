package ChatClientUI;



import javax.swing.*;

import Server.text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClientUI extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton connectButton;
    private JButton disconnectButton;
    private JPanel usersPanel,usersPanel1,usersPanel2,usersPanel3,usersPanel4,panel;
    text text;

    public ChatClientUI() {
        createUI();
        this.text=new text();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setForeground(Color.BLUE);
        textArea.setEditable(false);

        inputField = new JTextField();

        sendButton = new JButton("Send");
        connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(200, 100));

        disconnectButton = new JButton("Disconnect");
        disconnectButton.setPreferredSize(new Dimension(200, 100));

        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));

        usersPanel1 = new JPanel();
        usersPanel2 = new JPanel();
        usersPanel3 = new JPanel();
        usersPanel4 = new JPanel();

        usersPanel1.add(new JLabel(new ImageIcon("pictures/LinFeng.jpg")));
        usersPanel1.add(new JLabel("   Lin Feng (A184539)       "));
        usersPanel2.add(new JLabel(new ImageIcon("pictures/WangHegong.png")));
        usersPanel2.add(new JLabel("Wang Hegong (A185844)"));
        usersPanel3.add(new JLabel(new ImageIcon("pictures/SongYufeng.jpg")));
        usersPanel3.add(new JLabel("Song Yufeng (A178877)"));
        usersPanel4.add(new JLabel(new ImageIcon("pictures/DengZhihong.png")));
        usersPanel4.add(new JLabel("Deng Zhihong (A185015)"));


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        buttonPanel.add(disconnectButton);
        //8add(buttonPanel, BorderLayout.NORTH);
        //add(buttonPanel, BorderLayout.SOUTH);

        usersPanel.add(usersPanel1);
        usersPanel.add(usersPanel2);
        usersPanel.add(usersPanel3);
        usersPanel.add(usersPanel4);

        //put the image and matrix number into the scroll
        JScrollPane usersScrollPane = new JScrollPane(usersPanel);

        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(usersScrollPane);
        panel.add(buttonPanel);

        // Example users, you would add these dynamically from your server


        JScrollPane chatScrollPane = new JScrollPane(textArea);
        chatScrollPane.setPreferredSize(new Dimension(500, 520));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        JPanel chat =new JPanel();
        chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS));
        chat.add(chatScrollPane);
        chat.add(bottomPanel);

        JPanel show=new JPanel();
        show.add(panel);
        show.add(chat);

        JProgressBar progressBar=new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Not connected");

        JPanel windows=new JPanel();
        windows.add(panel);
        windows.add(chat);

        this.setTitle("TN3223");
        this.getContentPane().add(windows, BorderLayout.NORTH);
        this.getContentPane().add(progressBar, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);

        // Layout the main frame
        //JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, usersScrollPane, chatScrollPane);
        //add(splitPane, BorderLayout.CENTER);

        // Action listeners for buttons
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Send message
                String inputText=inputField.getText();
                if(inputText.equals("exit")) {
                	new DisconnectProgress(progressBar, disconnectButton,textArea,text).start();
                	textArea.append("Client >> "+inputText);
                    textArea.append("\n");
                    inputField.setText("");
                }else {
                textArea.append("Client >> "+inputText);
                textArea.append("\n");
                inputField.setText("");

                try {
                    textArea.append("Server >> "+text.getAIText(inputText));
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                textArea.append("\n");
            }
            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Connect to server
                new ConnectProgress(progressBar, connectButton,textArea,text).start();
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disconnect from server
                new DisconnectProgress(progressBar, disconnectButton,textArea,text).start();
            }
        });
    }
}


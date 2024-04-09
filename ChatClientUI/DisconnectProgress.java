package ChatClientUI;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import Server.text;

public class DisconnectProgress extends Thread{
    JProgressBar progressBar;
    JButton button;
    JTextArea textArea;
    text text;
    DisconnectProgress(JProgressBar progressBar,JButton button,JTextArea textArea,text text)
    {
        this.progressBar=progressBar;
        this.button=button;
        this.textArea=textArea;
        this.text=text;
    }
    public void run() {
        textArea.append("Client >> "+"Try to disconnect!\n");
        if(text.disConnectProcess()) {
            try {
                Thread.sleep(1000);
                progressBar.setIndeterminate(false);
                progressBar.setValue(0);
                progressBar.setString("DisConnect");
                textArea.append("Client >> "+"Success to disconnect!\n");
                button.setEnabled(true);
            } catch(InterruptedException e) {
                e.printStackTrace();
                textArea.append("Client >> Error during disconnect: " + e.getMessage() + "\n");
            }
        } else {
            try {
                Thread.sleep(1000);
                progressBar.setString("Failed to disconnect");
                textArea.append("Client >> "+"Failed to disconnect!\n");
                button.setEnabled(true);
            } catch(InterruptedException e) {
                e.printStackTrace();
                textArea.append("Client >> Error during disconnect: " + e.getMessage() + "\n");
            }
        }
    }

}


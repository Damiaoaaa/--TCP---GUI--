package ChatClientUI;



import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import Server.text;

public class ConnectProgress extends Thread
{
    text process;
    JProgressBar progressBar;
    JButton button;
    JTextArea textArea;
    //the number in the process bar
    int[] progressValues={50,100};
    ConnectProgress(JProgressBar progressBar,JButton button,JTextArea textArea,text process)
    {
        this.progressBar=progressBar;
        this.button=button;
        this.textArea=textArea;
        this.process=process;
    }
    public void run()
    {
        progressBar.setValue(progressValues[0]);
        textArea.append("Client >> "+"Try to link to serverClient\n");
        if(process.ConnectProcess())
        {
            try
            {
                Thread.sleep(1000);
                progressBar.setValue(progressValues[1]);
                progressBar.setIndeterminate(true);
                String Address="Connect to host: "+process.getAddress()+" post: "+process.getHost();
                //String start="Please select the story you want to know about";
                progressBar.setString(Address);
                //progressBar.setString(start);
                textArea.append("Client >> "+Address);
                textArea.append("\n");
                textArea.append("Client >> Welcome to the Story Paradise\n");
                textArea.append("Client >> Do you also want to enter the world of\n"
                		+ " fairy tales with me and take a wonderful journey?(y/n)\n");
                
                button.setEnabled(true);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }else {
            try
            {
                Thread.sleep(1000);
                progressBar.setString("Fail to connect:Please check you internet!");
                textArea.append("Client >> "+"Fail to connect:Please check you internet!\n");
                button.setEnabled(true);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

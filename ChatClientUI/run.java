package ChatClientUI;

import javax.swing.SwingUtilities;

public class run {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ChatClientUI();
            }
        });
    }
}

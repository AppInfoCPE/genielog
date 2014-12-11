
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class miseAJourDateThread extends Thread {
	PanelInformation panel;
    boolean running;

    public miseAJourDateThread(PanelInformation p) {
        panel = p;
        running = true;
    }

	public void arret() {
        running = false;
    }

    public void run() {
        while (running) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.miseAJourDate();
        }
    }
}

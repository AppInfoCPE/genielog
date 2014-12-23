
import java.util.ArrayList;

import javax.swing.JFrame;

public class MiseAJourStockThread extends Thread {
    InterfaceEmpCuisson fenetre;
    boolean running;

    public MiseAJourStockThread(InterfaceEmpCuisson f) {
        fenetre = f;
        running = true;
    }

    public void arret() {
        running = false;
    }

    public void run() {
        while (running) {
            
            fenetre.miseAJourTableCuire();
            fenetre.miseAJourTableRayon();
            fenetre.miseAJourTableVente();
			fenetre.miseAJourTableFrigo();
			try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }
}

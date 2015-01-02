
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiseAJourStockVenteThread extends Thread {
    InterfaceVente fenetreVente;
    boolean running;

    public MiseAJourStockVenteThread(InterfaceVente f) {
    	fenetreVente = f;
        running = true;
    }

    public void arret() {
        running = false;
    }

    public void run() {
        while (running) {
        	PopUp.afficherPeremption();
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fenetreVente.listeVeinoisserie(fenetreVente.onglet1);
			fenetreVente.listeBoisson(fenetreVente.onglet2);
			fenetreVente.completeResumeVente();
        }
    }
}

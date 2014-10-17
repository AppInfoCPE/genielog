
import java.util.ArrayList;

public class Thread_Fenetre extends Thread {
	Fenetre fenetre;
	boolean running;

	public Thread_Fenetre(Fenetre f) {
		fenetre = f;
		running = true;
	}

	public void arret() {
		running = false;
	}

	public void run() {
		while (running) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			ArrayList<String[]> results = DatabaseAccess.derbyExecuteQuery("SELECT VALUE FROM APP.TEST WHERE ID=1");

			if (results.size() != 0)
				fenetre.getLabel2().setText(results.get(0)[0]);

			/*
			 * for(int i = 0; i<results.size();i++){ for (int j = 0;
			 * j<results.get(i).length;j++){
			 * System.out.println(results.get(i)[j]); } }
			 */
		}
	}
}

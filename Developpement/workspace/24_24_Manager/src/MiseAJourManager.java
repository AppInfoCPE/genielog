/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class MiseAJourManager extends Thread {
    InterfaceManager fenetre;
    boolean running;

    public MiseAJourManager(InterfaceManager f) {
        fenetre = f;
        running = true;
    }

    public void arret() {
        running = false;
    }

    public void run() {
        while (running) {
            
            fenetre.miseAJourManager();
         
          
			try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */

public class MiseAJourStatThread extends Thread {
    InterfaceManager fenetre;
    boolean running;

    public MiseAJourStatThread(InterfaceManager f) {
        fenetre = f;
        running = true;
    }

    public void arret() {
        running = false;
    }

    public void run() {
        while (running) {
            
            fenetre.miseAJourTableStat1();
            fenetre.miseAJourTableStat2();
          
			try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }
}


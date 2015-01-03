import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;


public class GestionnaireFenetre extends WindowAdapter {
	private InterfaceVente iv;
	
	public GestionnaireFenetre(InterfaceVente iv) {
		// TODO Auto-generated constructor stub
		this.iv = iv;
	}

	public void windowClosing(WindowEvent e) {
		iv.mc.annulerVente(iv.numVente);
		iv.mc.supprimerVenteBDD(iv.numVente);
		//JOptionPane.showMessageDialog(null, iv.numVente);
		System.exit(0);
	}
}

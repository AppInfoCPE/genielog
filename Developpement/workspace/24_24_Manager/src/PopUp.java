import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class PopUp {

	public static int afficherConfirmation() {
		Object panel = UIManager.get("Panel.background");
		Object color = UIManager.get("Button.background");
		
		UIManager.put("OptionPane.background", new Color(241, 246, 190));
		UIManager.put("Panel.background", new Color(241, 246, 190));
		UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
		UIManager.put("OptionPane.messageAreaBorder", new EmptyBorder(20, 20, 20, 20));
		UIManager.put("OptionPane.okIcon", new ImageIcon("images/valider.png"));
		UIManager.put("OptionPane.okButtonText", "");
		UIManager.put("OptionPane.cancelIcon", new ImageIcon("images/annuler.png"));
		UIManager.put("OptionPane.cancelButtonText", "");		
		UIManager.put("Button.background", new Color(220, 150, 95));
		
		int res = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer votre action ?", "24/24 Manager", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
		
		UIManager.put("Button.background", color);
		UIManager.put("Panel.background", panel);
		
		return res;
	}

	public static void afficherPeremption(ArrayList<String> liste) {
		Object panel = UIManager.get("Panel.background");
		Object color = UIManager.get("Button.background");
		
		UIManager.put("OptionPane.background", new Color(241, 246, 190));
		UIManager.put("Panel.background", new Color(241, 246, 190));
		UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
		UIManager.put("OptionPane.messageAreaBorder", new EmptyBorder(20, 20, 20, 20));
		UIManager.put("OptionPane.okIcon", new ImageIcon("images/valider.png"));
		UIManager.put("OptionPane.okButtonText", "");	
		UIManager.put("Button.background", new Color(220, 150, 95));
		
		Object[] t = new Object[]{"Les produits suivant sont p�rim�s et doivent �tre jet�s :\n", liste.toArray()};
		
		JOptionPane.showMessageDialog(null, t, "24/24 Manager",  JOptionPane.WARNING_MESSAGE, null);
		
		UIManager.put("Button.background", color);
		UIManager.put("Panel.background", panel);
	}	

	public static void main(String[] args) {
		PopUp.afficherConfirmation();
		ArrayList<String> l = new ArrayList<String>();
		l.add("Coca : 2");
		l.add("Croissant : 8");		
		PopUp.afficherPeremption(l);
	}
}
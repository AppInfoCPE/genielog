import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Fenetre extends JFrame {
	private JFrame frame;

	private JPanel panel1;
	private JPanel panel2;

	private JTabbedPane tabbed;

	private JButton button1;
	private JLabel label1;
	private JButton button2;
	private JLabel label2;

	static int valeur1 = 0;
	
	Thread_Fenetre mt;

	public int getValeur1() {
		return valeur1;
	}

	public void setValeur1(int v) {
		valeur1 = v;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public Fenetre() {

		frame = new JFrame("Fenetre Test");
		frame.setSize(500, 500);
		frame.setVisible(true);

		panel1 = new JPanel();
		panel2 = new JPanel();

		label1 = new JLabel(Integer.toString(valeur1));
		button1 = new JButton("+");

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valeur1++;
				label1.setText(Integer.toString(valeur1));
				DatabaseAccess.derbyExecute("UPDATE APP.TEST SET VALUE="
						+ valeur1 + "WHERE ID=1");
			}
		});

		label2 = new JLabel(Integer.toString(valeur1));
		button2 = new JButton("-");
		/*
		 * button2.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { valeur2--;
		 * label2.setText(Integer.toString(valeur2)); } });
		 */

		panel1.add(label1);
		panel1.add(button1);
		panel2.add(label2);
		panel2.add(button2);

		tabbed = new JTabbedPane();
		tabbed.add("UPDATE", panel1);
		tabbed.add("VISUALIZE", panel2);

		frame.getContentPane().add(tabbed);

		mt = new Thread_Fenetre(this);

		mt.start();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				mt.arret();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
	}

	public JLabel getLabel2() {
		return label2;
	}

	public static void main(String[] args) {
		new Fenetre();
	}
}

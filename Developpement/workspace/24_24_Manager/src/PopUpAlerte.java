
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;


import javax.swing.JButton;


public class PopUpAlerte extends JFrame {

	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		PopUpAlerte frame = new PopUpAlerte();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public PopUpAlerte() {
		setTitle("24/24 Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(241, 246, 190));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblVoulezVousConfirmer = new JLabel("Limite de consommation atteinte.");
		lblVoulezVousConfirmer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVoulezVousConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoulezVousConfirmer.setBounds(10, 11, 464, 50);
		mainPanel.add(lblVoulezVousConfirmer);
		
	
		JButton B_ok = new JButton("Ok");
		B_ok.setBounds(385, 178, 89, 23);
		mainPanel.add(B_ok);
		
		JLabel lblIlFaudraAvoir = new JLabel("Il faudra avoir une liste de produit ici ...");
		lblIlFaudraAvoir.setHorizontalAlignment(SwingConstants.CENTER);
		lblIlFaudraAvoir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIlFaudraAvoir.setBounds(10, 84, 464, 50);
		mainPanel.add(lblIlFaudraAvoir);
	}

}

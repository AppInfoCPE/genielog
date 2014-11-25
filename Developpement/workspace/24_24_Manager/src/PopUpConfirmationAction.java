
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;


public class PopUpConfirmationAction extends JFrame {

	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PopUpConfirmationAction frame = new PopUpConfirmationAction();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public PopUpConfirmationAction() {
		setTitle("24/24 Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(241, 246, 190));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblVoulezVousConfirmer = new JLabel("Voulez vous confirmer votre action ?");
		lblVoulezVousConfirmer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVoulezVousConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoulezVousConfirmer.setBounds(10, 62, 464, 50);
		mainPanel.add(lblVoulezVousConfirmer);
		
		JButton B_oui = new JButton("Oui");
		B_oui.setBounds(131, 139, 89, 23);
		mainPanel.add(B_oui);
		
		JButton B_non = new JButton("Non");
		B_non.setBounds(258, 139, 89, 23);
		mainPanel.add(B_non);
	}

}



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InterfaceConnection extends JFrame {
	LogicielConnection lc;
	private JLabel labelErreur;
	private JLabel labelIdentifiant;
	private JLabel labelMotDePasse;
	private JLabel labelRole;
	private JTextField champIdentifiant;
	private JTextField champMotDePasse;
	private JComboBox comboBoxRole;
	private JButton boutonConnexion;
	private JLabel logo;
	private JPanel panel;
	private JPanel contentPane;

	public InterfaceConnection(LogicielConnection lc){
		this.lc = lc;

		setTitle("Fenetre Connexion");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labelErreur = new JLabel("");
		labelErreur.setForeground(Color.RED);
		labelIdentifiant = new JLabel("Identifiant");
		labelMotDePasse = new JLabel("Mot de Passe");
		labelRole = new JLabel("Role");
		champIdentifiant = new JTextField(20);
		champMotDePasse = new JPasswordField(20);
		comboBoxRole = new JComboBox();
		comboBoxRole.setModel(new DefaultComboBoxModel(new String[] {"Manager", "Cuisson", "Vendeur"}));
		boutonConnexion = new JButton("Connexion");
		logo = new JLabel(new ImageIcon("images/logo.jpg"));


		boutonConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (champIdentifiant.getText().length()!=0 && champMotDePasse.getText().length()!=0 && comboBoxRole.getSelectedItem().toString().length()!=0) {
					seConnecter();
				} else {
					labelErreur.setText("Erreur : Champ(s) non remplis");
				}
			}
		});

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(241, 246, 190));
		setContentPane(contentPane);

		
		labelErreur.setBounds(160, 20, 300, 14);
		contentPane.add(labelErreur);
		
		labelIdentifiant.setBounds(40, 50, 100, 14);
		labelIdentifiant.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(labelIdentifiant);
		champIdentifiant.setBounds(160, 45, 200, 25);
		contentPane.add(champIdentifiant);
		
		labelMotDePasse.setBounds(40, 80, 100, 14);
		labelMotDePasse.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(labelMotDePasse);
		champMotDePasse.setBounds(160, 75, 200, 25);
		contentPane.add(champMotDePasse);
		
		labelRole.setBounds(40, 110, 100, 14);
		labelRole.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(labelRole);
		comboBoxRole.setBounds(160, 105, 200, 25);
		contentPane.add(comboBoxRole);
		
		boutonConnexion.setBounds(160, 150, 100, 30);
		contentPane.add(boutonConnexion);	

		logo.setBounds(10, 200, 380, 150);
		logo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		contentPane.add(logo);

		setSize(410, 390);
		setResizable(false);
	}

	public void seConnecter(){
		Utilisateur actif = lc.seConnecter(champIdentifiant.getText(), champMotDePasse.getText(), comboBoxRole.getSelectedItem().toString());
		if (actif != null){
			switch (comboBoxRole.getSelectedItem().toString()) {
			case "Manager":
				//new InterfaceManager(new LogicielManager(actif));
				break;
			case "Cuisson":
				new InterfaceEmpCuisson(new LogicielEmpCuisson(actif));
				break;
			case "Vendeur":
				new InterfaceVente(new LogicielVendeur(),actif);
				break;
			default:
				break;
			}
			this.dispose();
		} else {
			labelErreur.setText("Erreur : Champ(s) non correct");
		}
	}
}

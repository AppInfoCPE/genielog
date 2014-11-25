

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
		logo = new JLabel(new ImageIcon("logo.jpg"));


		boutonConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (champIdentifiant.getText().length()!=0 && champMotDePasse.getText().length()!=0 && comboBoxRole.getSelectedItem().toString().length()!=0) {
					seConnecter();
				} else {
					labelErreur.setText("Erreur : Champ non remplis");
				}
			}
		});

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		panel = new JPanel();
		panel.setBackground(new Color(241, 246, 190));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		labelErreur.setBounds(130, 20, 300, 14);
		panel.add(labelErreur);
		
		labelIdentifiant.setBounds(40, 50, 100, 14);
		panel.add(labelIdentifiant);
		champIdentifiant.setBounds(130, 50, 200, 20);
		panel.add(champIdentifiant);
		
		labelMotDePasse.setBounds(40, 80, 100, 14);
		panel.add(labelMotDePasse);
		champMotDePasse.setBounds(130, 80, 200, 20);
		panel.add(champMotDePasse);
		
		labelRole.setBounds(40, 110, 100, 14);
		panel.add(labelRole);
		comboBoxRole.setBounds(130, 110, 200, 20);
		panel.add(comboBoxRole);
		
		boutonConnexion.setBounds(130, 150, 100, 20);
		panel.add(boutonConnexion);	

		logo.setBounds(0, 160, 405, 150);
		logo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		contentPane.add(logo, BorderLayout.SOUTH);

		setSize(390, 370);
		setResizable(false);
	}

	public void seConnecter(){
		Utilisateur actif = lc.seConnecter(champIdentifiant.getText(), champMotDePasse.getText(), comboBoxRole.getSelectedItem().toString());
		if (actif != null){
			switch (comboBoxRole.getSelectedItem().toString()) {
			case "Manager":
				new InterfaceManager(new LogicielManager());
				break;
			case "Cuisson":
				new InterfaceEmpCuisson(new LogicielEmpCuisson());
				break;
			case "Vendeur":
				new InterfaceVendeur(new LogicielVendeur());
				break;
			default:
				break;
			}
			this.dispose();
		} else {
			labelErreur.setText("Erreur : Champ non correct");
		}
	}
}

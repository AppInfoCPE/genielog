

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfaceConnection extends JFrame {
	LogicielConnection mc;
	private JLabel labelErreur;
	private JLabel labelIdentifiant;
	private JLabel labelMotDePasse;
	private JLabel labelRole;
	private JTextField champIdentifiant;
	private JTextField champMotDePasse;
	private JTextField champRole;
	private JButton boutonConnexion;
	private GroupLayout layout;

	public InterfaceConnection(LogicielConnection mc){
		this.mc = mc;

		setTitle("Fenetre Connexion");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labelErreur = new JLabel("");
		labelIdentifiant = new JLabel("Identifiant");
		labelMotDePasse = new JLabel("Mot de Passe");
		labelRole = new JLabel("Role");
		champIdentifiant = new JTextField(20);
		champMotDePasse = new JTextField(20);
		champRole = new JTextField(20);
		boutonConnexion = new JButton("Connexion");

		boutonConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (champIdentifiant.getText().length()!=0 && champMotDePasse.getText().length()!=0 && champRole.getText().length()!=0) {
					seConnecter();
				} else {
					labelErreur.setText("Erreur : Champ non remplis");
					pack();
				}
			}
		});

		layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(labelIdentifiant)
					.addComponent(labelMotDePasse)
					.addComponent(labelRole))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addGap(10, 10, 10))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(labelErreur)
					.addComponent(champIdentifiant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(champMotDePasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(champRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(boutonConnexion))
				.addContainerGap())
			);		

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(labelErreur))
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(labelIdentifiant)
					.addComponent(champIdentifiant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(10, 10, 10)					
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(labelMotDePasse)
					.addComponent(champMotDePasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(labelRole)
					.addComponent(champRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(10, 10, 10)
				.addComponent(boutonConnexion)
				.addContainerGap()
			));		

		/*panel = new JPanel();
		setContentPane(panel);
		panel.add(labelErreur);
		panel.add(labelIdentifiant);
		panel.add(champIdentifiant);
		panel.add(labelMotDePasse);
		panel.add(champMotDePasse);
		panel.add(labelRole);
		panel.add(champRole);
		panel.add(boutonConnexion);	*/

		//setSize(325, 175);
		//setResizable(false);

		pack();
	}

	public void seConnecter(){
		Utilisateur actif = mc.seConnecter(champIdentifiant.getText(), champMotDePasse.getText(), champRole.getText());
		if (actif != null){

			new InterfaceConnection(mc);
			this.dispose();
		} else {
			labelErreur.setText("Erreur : Champ non correct");
			pack();
		}
	}
}

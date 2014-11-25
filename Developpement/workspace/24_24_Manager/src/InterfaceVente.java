
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InterfaceVente extends JFrame {

	private JPanel contentPane;
	private Dimension dim = new Dimension(70, 70);
	private Dimension dim2 = new Dimension(100, 149);
	private Dimension dim3 = new Dimension(75, 75);
	private JTable table;
	private JTextField tfMontantDonne;
	private JTextField tfValeur;
	static LogicielVendeur mc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceVente frame = new InterfaceVente(mc);
					frame.setSize(1280, 720);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceVente(LogicielVendeur mc) {
		this.mc = mc;
		setTitle("24/24 Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 246, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComposant();
		initJTabbedPane();
		intTableVente();
		initResumVente();
	}
	
	

	// Lire la table vente
	private void intTableVente(){
		table = new JTable();
		Object[][] data = {   
			      {"Pain chocolat", "1", "1,05", "2,10","X"},
			      {"Croissant", "2", "0,95", "1,9","X"},
			    };

	    String  title[] = {"Produit", "Quantite", "Prix unite", "Prix total", "Supprimer"};

	    table = new JTable(data, title);
	    table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
	    table.getColumn("Supprimer").setCellEditor( new ButtonEditor(new JCheckBox()));
		table.setBounds(336, 98, 525, 235);
		contentPane.add(table);
		
	
	}
	
	private void initResumVente() {
		// TODO Auto-generated method stub
		JPanel panelResumVente = new JPanel();
		panelResumVente.setBounds(336, 333, 525, 85);
		panelResumVente.setBackground(new Color(201, 241, 250));
		
		JLabel LNbProduit=new JLabel();
		LNbProduit.setText("Nombre de produit :");
		LNbProduit.setBounds(387, 348, 140, 18);
		
		contentPane.add(LNbProduit);
		
		JTextField TFNbProduit=new JTextField();
		TFNbProduit.setBounds(530, 348, 50, 18);
		//TO-DO initialisé avec valeur total du tableau
		TFNbProduit.setText("0");
		TFNbProduit.setEditable(false);
		contentPane.add(TFNbProduit);
		
		JLabel LTotal=new JLabel();
		LTotal.setText("TOTAL :");
		LTotal.setBounds(700, 348, 70, 18);
		
		contentPane.add(LTotal);
		
		JTextField TFNTotal=new JTextField();
		TFNTotal.setBounds(800, 348, 50, 18);
		//TO-DO initialisé avec valeur total du tableau
		TFNTotal.setText("0");
		TFNTotal.setEditable(false);
		contentPane.add(TFNTotal);
		
		JLabel LTotalJournee=new JLabel();
		LTotalJournee.setText("Total des ventes de la journée :");
		LTotalJournee.setBounds(387, 378, 200, 18);
		contentPane.add(LTotalJournee);
		
		JTextField TFNTotalJournee=new JTextField();
		TFNTotalJournee.setBounds(800, 378, 50, 18);
		//TO-DO initialisé avec valeur total du tableau
		TFNTotalJournee.setText("0");
		TFNTotalJournee.setEditable(false);
		contentPane.add(TFNTotalJournee);
		
		
		contentPane.add(panelResumVente);
	}
		
	private JButton bEncaisser = new JButton("Encaisser");
	private void initComposant(){
		//Bouton Deconnection
		JButton bDeconnection = new JButton("Deconnexion");
		bDeconnection.setBounds(1130, 10, 120, 25);
		contentPane.add(bDeconnection);
		
		//Bouton help
		JButton bHelp = new JButton("?");
		bHelp.setBounds(1063, 10, 50, 25);
		contentPane.add(bHelp);
			
		//titre paiement
		JLabel lPaiement = new JLabel();
		lPaiement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPaiement.setText("Paiement");
		lPaiement.setBounds(875, 66, 120, 17);
		contentPane.add(lPaiement);	
		
		//Mode Paiement
		JPanel panelModePaiement = new JPanel();
		panelModePaiement.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelModePaiement.setBounds(875, 90, 375, 160);
		initModePaiement(panelModePaiement);
		
		//Affichage pave numerique
		JPanel panelChiffre = new JPanel();
		panelChiffre.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelChiffre.setBounds(875, 250, 375, 250);
		initChiffrePaiement(panelChiffre);
		
		//affichage donnees saisie / a rendre
		JPanel panelRendre = new JPanel();
		panelRendre.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRendre.setBounds(875, 500, 375, 160);
		
		JLabel LMontantDonne = new JLabel("Montant donn\u00E9 :");
		LMontantDonne.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LMontantDonne.setBounds(10, 32, 113, 17);
		panelRendre.add(LMontantDonne);
		
		JLabel LARendre = new JLabel("Montant \u00E0 rendre :");
		LARendre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LARendre.setBounds(10, 105, 126, 17);
		panelRendre.add(LARendre);
		
		tfMontantDonne = new JTextField();
		tfMontantDonne.setText("0");
		tfMontantDonne.setEditable(false);
		tfMontantDonne.setBounds(146, 32, 86, 20);
		panelRendre.add(tfMontantDonne);
		tfMontantDonne.setColumns(0);
		
		tfValeur = new JTextField();
		tfValeur.setText("0");
		tfValeur.setEditable(false);
		tfValeur.setColumns(0);
		tfValeur.setBounds(146, 105, 86, 20);
		panelRendre.add(tfValeur);
		
		JLabel leuro = new JLabel("\u20AC");
		leuro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leuro.setBounds(242, 32, 37, 17);
		panelRendre.add(leuro);
		
		JLabel label = new JLabel("\u20AC");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(242, 105, 37, 17);
		panelRendre.add(label);
		
		
		bEncaisser.setName("Encaisser");
		bEncaisser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bEncaisser.setBounds(117, 63, 140, 30);
		bEncaisser.setEnabled(false);
		bEncaisser.addActionListener(new encaisserListener());
		panelRendre.add(bEncaisser);
		
		contentPane.add(panelModePaiement);
		contentPane.add(panelChiffre);
		contentPane.add(panelRendre);
		panelRendre.setLayout(null);
	}
	private String[] tab_stringChiffre = {"1", "2", "3","<=","4","5","6","0","7","8","9",","};
	private JButton[] tab_button_Chiffre = new JButton[tab_stringChiffre.length];	
	private void initChiffrePaiement(JPanel panelChiffre) {
		// TODO Auto-generated method stub
							
		for(int i = 0; i < tab_stringChiffre.length; i++){
			tab_button_Chiffre[i] = new JButton(tab_stringChiffre[i]);
			tab_button_Chiffre[i].setName(tab_stringChiffre[i]);
			tab_button_Chiffre[i].setPreferredSize(dim3);
			tab_button_Chiffre[i].setEnabled(false);
			//actionlistener différents selon le bouton appuyer
			tab_button_Chiffre[i].addActionListener(new ChiffreListener());		
			panelChiffre.add(tab_button_Chiffre[i]);		    
		}
	}

	class ChiffreListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
		//on affiche le chiffre dans la texte box : montant donne
			String Cliquer = ((JButton)e.getSource()).getText();
			String Existant = tfMontantDonne.getText();
			
			if (Cliquer == "<="){
				tfMontantDonne.setText(Existant.substring(0,Existant.length()-1));
			}else{
				if (Existant.equals("0")){
					tfMontantDonne.setText("");
					tfMontantDonne.setText(Cliquer); 
				}else{
					tfMontantDonne.setText(Existant+Cliquer);					
				}
			}		    	  
		}
	}
	private String[] tab_stringMP = {"CB","Cheque","Espece"};
	private JButton[] tab_button_Mpaiement = new JButton[tab_stringMP.length];
	private void initModePaiement(JPanel panelModePaiement ) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < tab_stringMP.length; i++){
			tab_button_Mpaiement[i] = new JButton(tab_stringMP[i]);
			tab_button_Mpaiement[i].setName(tab_stringMP[i]);
			tab_button_Mpaiement[i].setPreferredSize(dim2);

			panelModePaiement.add(tab_button_Mpaiement[i]);
		    tab_button_Mpaiement[i].addActionListener(new AppuieBouton());
		}
	}
	
	private void initJTabbedPane(){
		//JTablePanel
		
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setBackground(new Color(218, 202, 251));
		
		//Veinoisserie
		
		JPanel onglet1 = new JPanel();
		onglet1.setBackground(new Color(218, 202, 251));
		onglet1.setPreferredSize(new Dimension(300, 80));
		onglets.addTab("Vienoisserie", onglet1);
		listeVeinoisserie(onglet1);
		
		//Boisson
		JPanel onglet2 = new JPanel();
		onglet2.setBackground(new Color(218, 202, 251));
		onglets.addTab("Boisson", onglet2);
		listeBoisson(onglet2);
		
		onglets.setBounds(22, 60, 300, 600);
		contentPane.add(onglets);
	}

	private void listeBoisson(JPanel onglet2) {
		// TODO Auto-generated method stub
		 String[] tab_string = {"CocaCola", "IceTea", "Oasis","Eau","Sprite","Pepsi", "JusDOrange"};
		//Un bouton par element a afficher
		 JButton[] tab_button = new JButton[tab_string.length];
		 for(int i = 0; i < tab_string.length; i++){
		     tab_button[i] = new JButton();
		     tab_button[i].setName(tab_string[i]);
		     tab_button[i].setPreferredSize(dim);	      
		     tab_button[i].setIcon(new ImageIcon("/Users/justine/Documents/CPE/workspace/genieLog/src/genieLog/"+tab_string[i]+".jpg"));
		     //Ajout des boutons sur le panel et ajout des listener
		     onglet2.add(tab_button[i]);
		     tab_button[i].addActionListener(new AppuieBoutonProduit());
		 }
	}
	class encaisserListener implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	    	String str = ((JButton)e.getSource()).getName();
	    	JOptionPane.showMessageDialog(null,str);
	    	
	    	for (int i=0;i<tab_button_Mpaiement.length;i++){	    		
	    			tab_button_Mpaiement[i].setEnabled(true);	    		
	    	}
	    	
	    	for (int i=0;i<tab_button_Chiffre.length;i++){
	    		tab_button_Chiffre[i].setEnabled(false);		
	    	}
	    	bEncaisser.setEnabled(false);
	    }
	}
	
	private void listeVeinoisserie(JPanel onglet1) {
		 // TODO Auto-generated method stub
		 String[] tab_string = {"Croissant", "PainAuChocolat", "CroissantAuxAmandes", "TartelettePralinee","PainAuLait","TarteleetteAuCitron"};
		//Un bouton par element a afficher
		 JButton[] tab_button = new JButton[tab_string.length];
		 for(int i = 0; i < tab_string.length; i++){
		     tab_button[i] = new JButton();
		     tab_button[i].setName(tab_string[i]);
		     tab_button[i].setPreferredSize(dim);	      
		     tab_button[i].setIcon(new ImageIcon("/Users/justine/Documents/CPE/workspace/genieLog/src/genieLog/"+tab_string[i]+".jpg"));
		     //Ajout des boutons sur le panel et ajout des listener
		     onglet1.add(tab_button[i]);
		     tab_button[i].addActionListener(new AppuieBoutonProduit());
		 }
	}
	
	//Listener utilise pour les produit
	//Permet de stocker les chiffres et de les afficher
	class AppuieBoutonProduit implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	    	String str = ((JButton)e.getSource()).getName();
	    	JOptionPane.showMessageDialog(null,str);
	    }
	}
	
	//Listener utilise pour les produit
		//Permet de stocker les chiffres et de les afficher
		class AppuieBouton implements ActionListener {
		    public void actionPerformed(ActionEvent e){
		    	String str = ((JButton)e.getSource()).getName();
		    	//JOptionPane.showMessageDialog(null,str);
		    	
		    	for (int i=0;i<tab_button_Mpaiement.length;i++){
		    		if (!str.equals(tab_button_Mpaiement[i].getName())){
		    			tab_button_Mpaiement[i].setEnabled(false);
		    		}
		    	}
		    	
		    	for (int i=0;i<tab_button_Chiffre.length;i++){
		    		tab_button_Chiffre[i].setEnabled(true);		
		    	}
		    	bEncaisser.setEnabled(true);
		    }
		}
}

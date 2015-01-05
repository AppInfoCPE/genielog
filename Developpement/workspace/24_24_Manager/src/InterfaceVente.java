
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.BorderFactory;
import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class InterfaceVente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel paneVente;
	private Dimension dim = new Dimension(70, 70);
	private Dimension dim2 = new Dimension(100, 149);
	private Dimension dim3 = new Dimension(75, 75);
	private JTable tableVente;
	private JTextField tfMontantDonne;
	private JTextField tfValeur;
	private JLabel lvente;
	LogicielVendeur mc;
	Utilisateur actif;
	private String[] tab_stringMP = {"CB","Cheque","Espece"};
	private JButton[] tab_button_Mpaiement = new JButton[tab_stringMP.length];
	private JTextField TFNTotal=new JTextField();
	private JButton bEncaisser = new JButton("Encaisser");
	private JButton btnChangerModePaiement = new JButton("Changer mode");
	private String[] tab_stringChiffre = {"1", "2", "3","<=","4","5","6","0","7","8","9","."};
	private JButton[] tab_button_Chiffre = new JButton[tab_stringChiffre.length];	
	private MiseAJourStockVenteThread mt;
	private Object[][] donneesEnVente;
	private JTabbedPane onglets;
	public Integer numVente;
	public JPanel onglet1;
	public JPanel onglet2;
	JTextField TFNbProduit=new JTextField();
	JButton BAnnulerVente=new JButton();
	JTextField TFNTotalJournee=new JTextField();
	public Vente venteEnCour;
	public String paiement;
	
	private GestionnaireFenetre gestionFenetre; 
	/**
	 * Create the frame.
	 */
	public InterfaceVente(LogicielVendeur mc,Utilisateur actif) {
		this.mc = mc;
		this.actif=actif;
		gestionFenetre= new GestionnaireFenetre(this);
		addWindowListener(gestionFenetre);
		setTitle("24/24 Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 246, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		setVisible(true);
		
		//Panel info
		JPanel panelHaut = new PanelInformation(this);
		panelHaut.setBounds(900, 10, 400, 30);
		contentPane.add(panelHaut);		
				
		//création d'une vente
		venteEnCour=new Vente(actif.getLogin());
		numVente=venteEnCour.getNumVente();
		
		initComposant();
		initJTabbedPane();
		initPaneVente();
		initTableVente();
		initResumVente();
		completeResumeVente();
		
		mt = new MiseAJourStockVenteThread(this);
		 mt.start();
			
	        addWindowListener(new WindowListener() {
				public void windowOpened(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
				public void windowClosing(WindowEvent e) {
	                mt.arret();
				}
				public void windowClosed(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
			});	
	}

	public void initPaneVente(){
		paneVente = new JPanel();
		//netoyage du panel avant raffraichissement du tableau gérer par le thread
		paneVente.removeAll();
		paneVente.setBounds(336, 90, 525, 243);
		paneVente.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		paneVente.setLayout(new BorderLayout());
		contentPane.add(paneVente);		
	}
	
	public void MAJTableVente(){
		//Recherche des produits ayant le statut en vente
		donneesEnVente = mc.rechercherProduitEnVente(numVente);
		TabModelVente modelTabVente = new TabModelVente(donneesEnVente);
		tableVente.setModel(modelTabVente);				
		tableVente.setDefaultRenderer(Object.class, new MyTableCellRenderer());		
		//ajouter bouton de suppression
		tableVente.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
		tableVente.getColumn("Supprimer").setCellEditor(new ButtonEditor(new JCheckBox(), this, donneesEnVente));
		tableVente.repaint();
					
		//activer les boutons d'encaissement
		for (int i=0;i<tab_button_Mpaiement.length;i++){	    		
			if(tableVente.getRowCount()>0){
				tab_button_Mpaiement[i].setEnabled(true);
				BAnnulerVente.setEnabled(true);
			}else{
				tab_button_Mpaiement[i].setEnabled(false);
				BAnnulerVente.setEnabled(false);
			}	    		
		}
	}
	
	public class TabModelVente extends AbstractTableModel {
		private final String title[] = {"Produit", "Quantite", "Prix unite", "Prix total", "Supprimer"};			
		Object donnees[][];
		
		public TabModelVente(Object donnees[][]) { 
		      this.donnees = donnees; 
		   }
		
		public int getColumnCount() {
			return title.length;
		}
		
		public String getColumnName(int columnIndex) {
			return title[columnIndex];
		}
	
		public int getRowCount() {
			return donnees.length; 
		}
	
		@Override
		public Object getValueAt(int param1, int param2) {
			 return donnees[param1][param2];
		}
	}

	public void initTableVente(){
		//Desactivation de l'edition d'un cellule lors d'un double click
		tableVente = new JTable(){		
			public boolean isCellEditable(int row, int column) {
				boolean temp = false;			
				if(column == 4){
					temp=true;
				}
				
				return temp;
			}			
		};
		tableVente.setName("tableVente");
		tableVente.setBackground(new Color(201, 241, 253));
		tableVente.setShowGrid(false);	
		MAJTableVente();	
	
		//ajout d'une scrollbare au tableau
		JScrollPane scrollPane = new JScrollPane(tableVente);
		scrollPane.getViewport().setBackground(new Color(201, 241, 253));

		paneVente.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initResumVente() {
		// TODO Auto-generated method stub
		JPanel panelResumVente = new JPanel();
		panelResumVente.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResumVente.setBounds(336, 333, 525, 150);
		panelResumVente.setBackground(new Color(201, 241, 250));	
		panelResumVente.setLayout(null);
		
		JLabel LNbProduit=new JLabel();
		LNbProduit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LNbProduit.setText("Nombre de produit :");
		LNbProduit.setBounds(24, 21, 134, 32);
		panelResumVente.add(LNbProduit);
		
		TFNbProduit.setHorizontalAlignment(SwingConstants.CENTER);
		TFNbProduit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TFNbProduit.setBounds(180, 23, 45, 30);
		//TO-DO initialisé avec valeur total du tableau
		TFNbProduit.setText("0");
		TFNbProduit.setEditable(false);
		panelResumVente.add(TFNbProduit);
		
		JLabel LTotal=new JLabel();
		LTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LTotal.setBounds(346, 21, 84, 32);
		LTotal.setText("Prix total :");
		//LTotal.setBounds(700, 348, 70, 18);		
		panelResumVente.add(LTotal);
		
		TFNTotal.setHorizontalAlignment(SwingConstants.CENTER);
		TFNTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));				
		TFNTotal.setBounds(428, 24, 45, 30);
		//TO-DO initialisé avec valeur total du tableau
		TFNTotal.setText("0");
		TFNTotal.setEditable(false);
		panelResumVente.add(TFNTotal);
		
		/*ajouter un bouton annuler vente en cour ici*/
		BAnnulerVente.setBackground(new Color(255, 99, 71));
		BAnnulerVente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BAnnulerVente.setBounds(162, 59, 200, 37);
		BAnnulerVente.setText("Annuler la vente");
		BAnnulerVente.setEnabled(false);
		BAnnulerVente.addActionListener(new supprimerVenteEnCour());
		panelResumVente.add(BAnnulerVente);
		
		JLabel LTotalJournee=new JLabel();
		LTotalJournee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LTotalJournee.setBounds(24, 107, 216, 32);
		LTotalJournee.setText("Total des ventes de la journee :");
		panelResumVente.add(LTotalJournee);
		
		TFNTotalJournee.setHorizontalAlignment(SwingConstants.CENTER);
		TFNTotalJournee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TFNTotalJournee.setBounds(236, 107, 111, 32);
		//TO-DO initialisÈ avec valeur total du tableau
		TFNTotalJournee.setText("0");
		TFNTotalJournee.setEditable(false);
		panelResumVente.add(TFNTotalJournee);
		
		
		JLabel leuro_1 = new JLabel("\u20AC");
		leuro_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leuro_1.setBounds(483, 29, 37, 17);
		panelResumVente.add(leuro_1);
		
		contentPane.add(panelResumVente);
		
		JLabel label = new JLabel("\u20AC");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(357, 118, 37, 17);
		panelResumVente.add(label);
	}
	
	class supprimerVenteEnCour implements ActionListener {
		public void actionPerformed(ActionEvent e){
			mc.annulerVente(numVente);			
			MAJTableVente();
			completeResumeVente();
			bEncaisser.setEnabled(false);
			btnChangerModePaiement.setEnabled(false);
			tfValeur.setText("");
			tfMontantDonne.setText("");
			for (int i=0;i<tab_button_Chiffre.length;i++){
				if(tab_button_Chiffre[i].isEnabled()){
					tab_button_Chiffre[i].setEnabled(false);
				}	
			}
		}
	}
		private void majTitreVente(){
			lvente.setText("Vente n∞ "+numVente);
		}
		private void initComposant(){		
		//titre vente
		lvente = new JLabel();
		lvente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		majTitreVente();
		lvente.setBounds(336, 66, 120, 17);
		contentPane.add(lvente);
		
			
		//titre paiement
		JLabel lPaiement = new JLabel();
		lPaiement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPaiement.setText("Paiement");
		lPaiement.setBounds(875, 66, 120, 17);
		contentPane.add(lPaiement);	
		
		//Mode Paiement
		JPanel panelModePaiement = new JPanel();
		panelModePaiement.setBackground(new Color(218, 202, 251));
		panelModePaiement.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelModePaiement.setBounds(875, 90, 375, 160);
		initModePaiement(panelModePaiement);
		
		//Affichage pave numerique
		JPanel panelChiffre = new JPanel();
		panelChiffre.setBackground(new Color(218, 202, 251));
		panelChiffre.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelChiffre.setBounds(875, 250, 375, 250);
		initChiffrePaiement(panelChiffre);
		
		//affichage logo de la societee
		JLabel Lsociete = new JLabel("test image");
		ImageIcon logo = new ImageIcon("images/logo.jpg");
		Lsociete.setIcon(logo);
		Lsociete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Lsociete.setBounds(400, 500, logo.getIconWidth(), logo.getIconHeight());
		contentPane.add(Lsociete);
		
		//affichage donnees saisie / a rendre
		JPanel panelRendre = new JPanel();
		panelRendre.setBackground(new Color(218, 202, 251));
		panelRendre.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRendre.setBounds(875, 500, 375, 160);
		
		JLabel LMontantDonne = new JLabel("Montant donn\u00E9 :");
		LMontantDonne.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LMontantDonne.setBounds(10, 32, 113, 17);
		panelRendre.add(LMontantDonne);
		
		JLabel LARendre = new JLabel("Montant \u00E0 rendre :");
		LARendre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LARendre.setBounds(10, 111, 126, 17);
		panelRendre.add(LARendre);
		
		tfValeur = new JTextField();
		tfValeur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfValeur.setText("0");
		tfValeur.setEditable(false);
		tfValeur.setColumns(0);
		tfValeur.setBounds(146, 105, 86, 32);
		panelRendre.add(tfValeur);
		
		JLabel leuro = new JLabel("\u20AC");
		leuro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leuro.setBounds(242, 32, 37, 17);
		panelRendre.add(leuro);
		
		JLabel label = new JLabel("\u20AC");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(242, 111, 37, 17);
		panelRendre.add(label);
				
		bEncaisser.setName("Encaisser");
		bEncaisser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bEncaisser.setBounds(205, 69, 140, 31);
		bEncaisser.setEnabled(false);
		bEncaisser.addActionListener(new encaisserListener());
		panelRendre.add(bEncaisser);
		
		contentPane.add(panelModePaiement);
		contentPane.add(panelChiffre);
		contentPane.add(panelRendre);
		panelRendre.setLayout(null);
				
		btnChangerModePaiement.setName("bchangerModePaiement");
		btnChangerModePaiement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChangerModePaiement.setEnabled(false);
		btnChangerModePaiement.setBounds(35, 69, 140, 31);
		btnChangerModePaiement.addActionListener(new changeModePaiement());
		panelRendre.add(btnChangerModePaiement);
		
		tfMontantDonne = new JTextField();
		tfMontantDonne.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMontantDonne.setBounds(146, 26, 86, 32);
		panelRendre.add(tfMontantDonne);
		tfMontantDonne.setText("0");
		tfMontantDonne.setEditable(false);
		tfMontantDonne.setColumns(0);
	}
	
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
			DecimalFormat df = new DecimalFormat("#.##");
			DecimalFormatSymbols dfs=new DecimalFormatSymbols();
			dfs.setDecimalSeparator('.');
			df.setDecimalFormatSymbols(dfs);
			//on affiche le chiffre dans la texte box : montant donne
			String Cliquer = ((JButton)e.getSource()).getText();
			String Existant = tfMontantDonne.getText();
			float montantDonnee=0, montantPrix=0, rendu=0, prixTotalVente = 0;
			
			if (Cliquer == "<="){
				if(Existant.length()==1){
					tfMontantDonne.setText("0");
				}else{
					tfMontantDonne.setText(Existant.substring(0,Existant.length()-1));
				}
			}else if (Cliquer == "."){
				if(! Existant.contains(".")){
						tfMontantDonne.setText(Existant+Cliquer);
				}
			}else{
				if (Existant.equals("0")){
					tfMontantDonne.setText("");
					tfMontantDonne.setText(Cliquer); 
				}else{
					tfMontantDonne.setText(Existant+Cliquer);					
				}
			}
			montantDonnee = Float.valueOf(tfMontantDonne.getText());
			//si espece selectionne on calcul le rendu
			if(tab_button_Mpaiement[2].isEnabled()){				
				montantPrix = Float.valueOf(TFNTotal.getText());
				rendu = montantDonnee-montantPrix;
			}
			else
				rendu=0;

			tfValeur.setText(String.valueOf(df.format(rendu)));
			prixTotalVente = Float.valueOf(TFNTotal.getText());
			if (montantDonnee >= prixTotalVente){
				bEncaisser.setEnabled(true);
			}else 
				bEncaisser.setEnabled(false);
		}
	}
	class changeModePaiement implements ActionListener {
		public void actionPerformed(ActionEvent e){
		//on desactive a nouveau les chiffres et le btn encaisser et celui ci et 
			//on reactive les bouton mode de paiement.
			for(int i = 0; i < tab_stringMP.length; i++){
				tab_button_Mpaiement[i].setEnabled(true);;
			}
			for(int i = 0; i < tab_stringChiffre.length; i++){
				tab_button_Chiffre[i].setEnabled(false);	    
			}
			tfMontantDonne.setText("0");
			tfValeur.setText("0");
			bEncaisser.setEnabled(false);
			btnChangerModePaiement.setEnabled(false);
		}
	}
	
	private void initModePaiement(JPanel panelModePaiement ) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < tab_stringMP.length; i++){
			tab_button_Mpaiement[i] = new JButton(tab_stringMP[i]);
			tab_button_Mpaiement[i].setName(tab_stringMP[i]);
			tab_button_Mpaiement[i].setPreferredSize(dim2);
			tab_button_Mpaiement[i].setEnabled(false);
			panelModePaiement.add(tab_button_Mpaiement[i]);
		    tab_button_Mpaiement[i].addActionListener(new AppuieBoutonModePaiement());
		}
	}
	
	private void initJTabbedPane(){
		//JTablePanel
		
		onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setBackground(new Color(218, 202, 251));

		//Veinoisserie		
		onglet1 = new JPanel();
		onglet1.setBorder(new LineBorder(new Color(0, 0, 0)));
		onglet1.setBackground(new Color(218, 202, 251));
		onglet1.setPreferredSize(new Dimension(300, 80));
		onglets.addTab("Vienoisserie", onglet1);
		listeVeinoisserie(onglet1);
		
		//Boisson
		onglet2 = new JPanel();
		onglet2.setBorder(new LineBorder(new Color(0, 0, 0)));
		onglet2.setBackground(new Color(218, 202, 251));
		onglets.addTab("Boisson", onglet2);
		listeBoisson(onglet2);
		
		onglets.setBounds(22, 67, 300, 600);
		contentPane.add(onglets);
	}

	
	class encaisserListener implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	    	mc.terminerVente(numVente, paiement);	    	
	    	/*for (int i=0;i<tab_button_Mpaiement.length;i++){	    		
	    			tab_button_Mpaiement[i].setEnabled(true);	    		
	    	}*/
	    	
	    	for (int i=0;i<tab_button_Chiffre.length;i++){
	    		tab_button_Chiffre[i].setEnabled(false);		
	    	}
	    	tfMontantDonne.setText("");
	    	tfValeur.setText("");
	    	bEncaisser.setEnabled(false);
	    	btnChangerModePaiement.setEnabled(false);
	    	
	    	MAJTableVente();
	    	completeResumeVente();
	    	venteEnCour = new Vente(actif.getLogin());
	    	numVente = venteEnCour.getNumVente();
	    	majTitreVente();
	    }
	}
	
	public void listeVeinoisserie(JPanel onglet1) {
		 // TODO Auto-generated method stub
		Object[][] donneesType = null;
		Object[][] donnees =null;
		donneesType = mc.listTypeProduitVeinoiserie();
		donnees = mc.listProduitVeinoiserie();
		onglet1.removeAll();
		JButton[] tab_button = new JButton[donneesType.length];
		for(int i = 0; i < donneesType.length; i++){
			tab_button[i] = new JButton();
		    tab_button[i].setName((String) donneesType[i][0]);
		    tab_button[i].setEnabled(false);
		    tab_button[i].setPreferredSize(dim);	  
		    tab_button[i].setIcon(new ImageIcon("images/"+(String) donneesType[i][0]+" nd.jpg"));
		
			//Ajout des boutons sur le panel et ajout des listener
			onglet1.add(tab_button[i]);
			for(int j = 0; j < donnees.length; j++){				
				if(donneesType[i][0].equals(donnees[j][0])){
					tab_button[i].addActionListener(new AjoutProduitVente());
					tab_button[i].setEnabled(true);
					tab_button[i].setIcon(new ImageIcon("images/"+(String) donneesType[i][0]+".jpg"));
					//System.out.println("PASSER");
				}
			}
		}
	}
	
	public void listeBoisson(JPanel onglet2) {
		 // TODO Auto-generated method stub
		
		Object[][] donneesType = null;
		donneesType = mc.listTypeProduitBoisson();
		Object[][] donnees =null;
		donnees = mc.listProduitBoison();
		onglet2.removeAll();
		  
		JButton[] tab_button = new JButton[donneesType.length];
		for(int i = 0; i < donneesType.length; i++){
			tab_button[i] = new JButton();
		    tab_button[i].setName((String) donneesType[i][0]);
		    tab_button[i].setPreferredSize(dim);	 
		    tab_button[i].setEnabled(false);
		    tab_button[i].setIcon(new ImageIcon("images/"+(String) donneesType[i][0]+" nd.jpg"));
		
			//Ajout des boutons sur le panel et ajout des listener
		    onglet2.add(tab_button[i]);
			for(int j = 0; j < donnees.length; j++){				
				if(donneesType[i][0].equals(donnees[j][0])){
					//changer est mettre nouvelle image
					tab_button[i].addActionListener(new AjoutProduitVente());
					tab_button[i].setEnabled(true);
					tab_button[i].setIcon(new ImageIcon("images/"+(String) donneesType[i][0]+".jpg"));
					//System.out.println("PASSER");
				}  
			}
		}
	}
	
	
	//Listener utilise pour les produit
	//Permet de stocker les chiffres et de les afficher
	class AjoutProduitVente implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	    	String nomProduit = ((JButton)e.getSource()).getName();
	    	mc.ajoutProduitVente(nomProduit,numVente);
	    	//actualisation de l'onglet actif uniquement
	    	if(onglets.getSelectedComponent().equals(onglet1)){
	    		listeVeinoisserie(onglet1);
	    		//System.out.println("onglet 1 actualiser");
	    	}
	    	if(onglets.getSelectedComponent().equals(onglet2)){
	    		listeBoisson(onglet2);
	    		//System.out.println("onglet 2 actualiser");
	    	}
	    	MAJTableVente();
	    	completeResumeVente();
	    }
	}
	
	//Permet de stocker les chiffres et de les afficher
	class AppuieBoutonModePaiement implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	    	paiement=((JButton)e.getSource()).getName();
	    	
	    	for (int i=0;i<tab_button_Mpaiement.length;i++){
	    		if (!paiement.equals(tab_button_Mpaiement[i].getName())){
	    			tab_button_Mpaiement[i].setEnabled(false);
	    		}
	    	}
	    	
	    	if (paiement != "Espece"){
	    		tfMontantDonne.setText(TFNTotal.getText());	
	    		bEncaisser.setEnabled(true);
	    	}
	    	
	    	for (int i=0;i<tab_button_Chiffre.length;i++){
	    		tab_button_Chiffre[i].setEnabled(true);		
	    	}
	    	
	    	btnChangerModePaiement.setEnabled(true);
	    }
	}
	
	public void completeResumeVente() {
		TFNbProduit.setText("");
		TFNTotal.setText("");
		if (donneesEnVente != null ){
			//le tableau contient des produits
			//calculer le nombre total de produit et le prix total
			int i=0,nbProduit=0; 
			float prixTotal=0,prixTotalJournee=0;
			DecimalFormat df = new DecimalFormat("#.##");
			DecimalFormatSymbols dfs=new DecimalFormatSymbols();
			dfs.setDecimalSeparator('.');
			df.setDecimalFormatSymbols(dfs);
			for (i=0;i<donneesEnVente.length;i++){
				nbProduit=nbProduit+Integer.valueOf(donneesEnVente[i][1].toString());
				TFNbProduit.setText(String.valueOf(nbProduit));
				prixTotal=prixTotal+Float.valueOf(donneesEnVente[i][3].toString());
				TFNTotal.setText(String.valueOf(df.format(prixTotal)));
			}
			prixTotalJournee=mc.rechercherTotalVenteJournee(actif);
			//System.out.println(prixTotalJournee);
			TFNTotalJournee.setText(String.valueOf(df.format(prixTotalJournee)));
		}		
	}
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Utilisateur ut=new Utilisateur("Jean", "jean", "manager", "Dupond", "Jean");
					InterfaceVente frame = new InterfaceVente(new LogicielVendeur(ut),ut);
					frame.setSize(1280, 720);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LogicielVendeur getLogicielVente() {
		// TODO Auto-generated method stub
		return this.mc;
	}
}

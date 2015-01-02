import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class InterfaceEmpCuisson extends JFrame {
	LogicielEmpCuisson lec;

	private JPanel contentPane;
	private JTable tableStockFrigo;
	private JTable tableStockVente;
	private JPanel panelCentre;
	private JTable tableCuire;
	private JTable tableRayon;
	private JTable tableFour;
	private MiseAJourStockThread mt;
	private ArrayList<Timer> listeTimer;

	public InterfaceEmpCuisson(LogicielEmpCuisson lec) {
		this.lec = lec;
		listeTimer = new ArrayList<Timer>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1280, 720));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Interface employé de cuisson");
		setVisible(true);
		initialisation();
		initialisationPanelHaut();
		initialisationPanelFrigo();
		initialisationPanelVente();		
		initialisationPanelCentre();
		initialisationThread();	
	}

	private void initialisation() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 246, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private void initialisationPanelHaut() {
		JPanel panelHaut = new PanelInformation(this);
		contentPane.add(panelHaut, BorderLayout.NORTH);		
	}

	private void initialisationPanelVente() {
		JPanel panelStockVente = new JPanel();
		panelStockVente.setBackground(new Color(218, 202, 251));
		panelStockVente.setPreferredSize(new Dimension(300, getHeight()-20));
		panelStockVente.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelStockVente.setLayout(new GridBagLayout());

		JLabel lblStockVente = new JLabel("Stock Vente");
		lblStockVente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStockVente.setForeground(Color.RED);
		panelStockVente.add(lblStockVente, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableStockVente = new JTable();
		tableStockVente.setEnabled(false);
		tableStockVente.setBackground(new Color(218, 202, 251));
		tableStockVente.setShowGrid(false);
		tableStockVente.setModel(new DefaultTableModel(new String[] {"Produit", "Quantité"}, 0));
		tableStockVente.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableStockVente.getColumnModel().getColumn(1).setPreferredWidth(75);
		JScrollPane scrollPane = new JScrollPane(tableStockVente);
		scrollPane.getViewport().setBackground(new Color(218, 202, 251));
		panelStockVente.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		contentPane.add(panelStockVente, BorderLayout.EAST);	
	}

	private void initialisationPanelFrigo() {
		JPanel panelStockFrigo = new JPanel();
		panelStockFrigo.setBackground(new Color(218, 202, 251));
		panelStockFrigo.setPreferredSize(new Dimension(300, getHeight()-20));
		panelStockFrigo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelStockFrigo.setLayout(new GridBagLayout());

		JLabel lblStockFrigo = new JLabel("Stock Frigo");
		lblStockFrigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStockFrigo.setForeground(Color.RED);		
		panelStockFrigo.add(lblStockFrigo, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableStockFrigo = new JTable();
		tableStockFrigo.setEnabled(false);
		tableStockFrigo.setBackground(new Color(218, 202, 251));
		tableStockFrigo.setShowGrid(false);
		tableStockFrigo.setModel(new DefaultTableModel(new String[] {"Produit", "Quantité"},0));
		tableStockFrigo.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableStockFrigo.getColumnModel().getColumn(1).setPreferredWidth(75);
		JScrollPane scrollPane = new JScrollPane(tableStockFrigo);
		scrollPane.getViewport().setBackground(new Color(218, 202, 251));
		panelStockFrigo.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(panelStockFrigo, BorderLayout.WEST);
	}

	private void initialisationPanelCentre() {
		panelCentre = new JPanel();
		panelCentre.setBackground(new Color(241, 246, 190));
		GridBagLayout layout = new GridBagLayout();
		panelCentre.setLayout(layout);		
		contentPane.add(panelCentre, BorderLayout.CENTER);

		initialisationPanelACuire();
		initialisationPanelEnRayon();
		initialisationPanelFour();
	}

	private void initialisationPanelFour() {
		JPanel panelFour = new JPanel();
		panelFour.setBackground(new Color(238, 238, 238));
		panelFour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelFour.setMinimumSize(new Dimension(500, getHeight()/2-20));
		panelFour.setLayout(new GridBagLayout());

		JLabel lblFour = new JLabel("Au Four");
		lblFour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFour.setForeground(Color.RED);
		panelFour.add(lblFour, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		tableFour = new JTable();
		tableFour.setShowGrid(false);
		tableFour.setBackground(new Color(238, 238, 238));
		tableFour.setModel(new DefaultTableModel(new String[] {"Produit", "Quantité", "Début cuisson", "Progression", "", ""}, 0){
			public boolean isCellEditable(int row, int col){
				if(col == 4 || col == 5)
					return true;
				return false; 
			}
		});
		tableFour.getColumnModel().getColumn(0).setPreferredWidth(125);
		tableFour.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableFour.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableFour.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableFour.getColumnModel().getColumn(3).setCellRenderer(new ProgressBarRenderer());
		tableFour.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableFour.getColumnModel().getColumn(4).setCellRenderer(new ButtonRendererFour());
		tableFour.getColumnModel().getColumn(4).setCellEditor(new ButtonEditorFour(new JCheckBox(), this));
		tableFour.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableFour.getColumnModel().getColumn(5).setCellRenderer(new ButtonRendererFour());
		tableFour.getColumnModel().getColumn(5).setCellEditor(new ButtonEditorFour(new JCheckBox(), this));		

		JScrollPane scrollPane = new JScrollPane(tableFour);
		scrollPane.getViewport().setBackground(new Color(238, 238, 238));	
		panelFour.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		panelCentre.add(panelFour, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,  new Insets(5, 5, 1, 5), 0, 0));		
		miseAJourTableFour();
	}

	private void initialisationPanelEnRayon() {
		JPanel panelRayon = new JPanel();
		panelRayon.setBackground(new Color(201, 241, 253));
		panelRayon.setMinimumSize(new Dimension(250, getHeight()/2-20));
		panelRayon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelRayon.setLayout(new GridBagLayout());

		JLabel lblRayon = new JLabel("Mettre en Rayon");
		lblRayon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRayon.setForeground(Color.RED);
		panelRayon.add(lblRayon, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableRayon = new JTable();
		tableRayon.setBackground(new Color(201, 241, 253));
		tableRayon.setShowGrid(false);
		tableRayon.setModel(new DefaultTableModel(new String[] {"Produit", "Quantité", "Date", "Péremption", ""}, 0){
			public boolean isCellEditable(int row, int col){
				if(col == 2 || col == 3 || col == 4)
					return true;
				return false; 
			}
		});
		tableRayon.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableRayon.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableRayon.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableRayon.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<Integer>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12})));
		tableRayon.getColumnModel().getColumn(3).setPreferredWidth(60);
		Integer [] listeAnnee = new Integer[10];
		for (int i = 0; i < listeAnnee.length; i++) {
			listeAnnee[i] = Calendar.getInstance().get(Calendar.YEAR) + i;
		}
		tableRayon.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JComboBox<Integer>(listeAnnee)));
		tableRayon.getColumnModel().getColumn(4).setMaxWidth(20);
		tableRayon.getColumnModel().getColumn(4).setCellRenderer(new CheckBoxRenderer());
		tableRayon.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()));

		JScrollPane scrollPane = new JScrollPane(tableRayon);
		scrollPane.getViewport().setBackground(new Color(201, 241, 253));
		panelRayon.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		JButton boutonValider_1 = new JButton("Valider");
		boutonValider_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < tableRayon.getRowCount(); i++) {
					if (tableRayon.getValueAt(i, 4).toString().equals("true")) {
						mettreEnRayon(tableRayon.getValueAt(i, 0).toString(), tableRayon.getValueAt(i, 1).toString(), tableRayon.getValueAt(i, 2).toString(), tableRayon.getValueAt(i, 3).toString());
						DefaultTableModel model = (DefaultTableModel) tableRayon.getModel();
						model.removeRow(i);
						i--;
					}
				}				
			}
		});
		panelRayon.add(boutonValider_1, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		panelCentre.add(panelRayon, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,  new Insets(0, 5, 5, 5), 0, 0));
	}

	private void initialisationPanelACuire() {
		JPanel panelCuire = new JPanel();
		panelCuire.setBackground(new Color(201, 241, 253));
		panelCuire.setMinimumSize(new Dimension(250, getHeight()/2-20));
		panelCuire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelCuire.setLayout(new GridBagLayout());

		JLabel lblCuire = new JLabel("A Cuire");
		lblCuire.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCuire.setForeground(Color.RED);
		panelCuire.add(lblCuire, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableCuire = new JTable();
		tableCuire.setBackground(new Color(201, 241, 253));
		tableCuire.setShowGrid(false);
		tableCuire.setModel(new DefaultTableModel(new String[] {"Produit", "Quantité", ""}, 0){
			public boolean isCellEditable(int row, int col){
				if(col == 2)
					return true;
				return false; 
			}
		});
		tableCuire.getColumnModel().getColumn(0).setPreferredWidth(175);
		tableCuire.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableCuire.getColumnModel().getColumn(2).setMaxWidth(20);
		tableCuire.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
		tableCuire.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));

		JScrollPane scrollPane = new JScrollPane(tableCuire);
		scrollPane.getViewport().setBackground(new Color(201, 241, 253));
		panelCuire.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		JButton boutonValider = new JButton("Valider");
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < tableCuire.getRowCount(); i++) {
					if (tableCuire.getValueAt(i, 2).toString().equals("true")) {
						mettreAuFour(tableCuire.getValueAt(i, 0).toString(), tableCuire.getValueAt(i, 1).toString());
						DefaultTableModel model = (DefaultTableModel) tableCuire.getModel();
						model.removeRow(i);
						i--;
					}
				}
			}
		});
		panelCuire.add(boutonValider, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		panelCentre.add(panelCuire, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,  new Insets(0, 5, 5, 5), 0, 0));
	}

	private void initialisationThread() {

		mt = new MiseAJourStockThread(this);
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

	public void miseAJourTableVente(){
		Object [][] donnees = lec.afficherProduitVente();
		DefaultTableModel model = (DefaultTableModel) tableStockVente.getModel();
		model.setRowCount(0);
		for (int i = 0; i < donnees.length; i++) {
			model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString()});
		}
	}

	public void miseAJourTableFrigo(){
		Object [][] donnees = lec.afficherProduitFrigo();
		DefaultTableModel model = (DefaultTableModel) tableStockFrigo.getModel();
		model.setRowCount(0);
		for (int i = 0; i < donnees.length; i++) {
			model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString()});
		}		
	}

	public void miseAJourTableCuire(){
		Object [][] donnees = lec.afficherProduitACuire();
		DefaultTableModel model = (DefaultTableModel) tableCuire.getModel();

		for (int i = 0; i < donnees.length; i++) {
			int row = -1;
			for (int j = 0; j < model.getRowCount(); j++) {
				if (model.getValueAt(j, 0).equals(donnees[i][0].toString()))
					row = j;
			}
			if (row == -1) model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString(), Boolean.FALSE});
			else model.setValueAt(donnees[i][1].toString(), row, 1);
		}
	}

	public void miseAJourTableRayon(){
		Object [][] donnees = lec.afficherProduitAMettreRayon();
		DefaultTableModel model = (DefaultTableModel) tableRayon.getModel();
		for (int i = 0; i < donnees.length; i++) {
			int row = -1;
			for (int j = 0; j < model.getRowCount(); j++) {
				if (model.getValueAt(j, 0).equals(donnees[i][0].toString()))
					row = j;
			}
			if (row == -1) model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString(), "1", Calendar.getInstance().get(Calendar.YEAR), Boolean.FALSE});
			else model.setValueAt(donnees[i][1].toString(), row, 1);
		}
	}

	public void miseAJourTableFour(){
		for (Timer t : listeTimer) {
			 t.stop();
		}
		listeTimer.clear();
		Object [][] donnees = lec.afficherProduitFour();
		DefaultTableModel model = (DefaultTableModel) tableFour.getModel();
		model.setRowCount(0);
		for (int i = 0; i < donnees.length; i++) {

			model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString(), donnees[i][2].toString(), 0, "Valider", "Jeter"});
			Timer t = new Timer(1000, new UpdateProgressBar(model, i, 3, donnees[i][2].toString(), lec.recupererTempsCuisson(donnees[i][0].toString())));
			listeTimer.add(t);
			t.start();
		}
	}

	public void validerCuisson(int row) {
		int res = PopUp.afficherConfirmation();
		if (res == 0) {
			lec.validerCuisson(tableFour.getValueAt(row, 0).toString(), tableFour.getValueAt(row, 1).toString(), tableFour.getValueAt(row, 2).toString());
			DefaultTableModel model = (DefaultTableModel) tableFour.getModel();
			model.removeRow(row);
		}
	}

	public void rejeterCuisson(int row) {
		int res = PopUp.afficherConfirmation();
		if (res == 0) {		
			lec.rejeterCuisson(tableFour.getValueAt(row, 0).toString(), tableFour.getValueAt(row, 1).toString(), tableFour.getValueAt(row, 2).toString());
			DefaultTableModel model = (DefaultTableModel) tableFour.getModel();
			model.removeRow(row);
		}
	}

	private void mettreAuFour(String typeProduit, String nombre) {
		lec.mettreAuFour(typeProduit, nombre);
		miseAJourTableFour();
	}	

	private void mettreEnRayon(String typeProduit, String nombre, String mois, String annee) {
		lec.mettreEnRayon(typeProduit, nombre, mois, annee);
	}

	public LogicielEmpCuisson getLogicielEmpCuisson() {
		return this.lec;
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException ex) {
				} catch (IllegalAccessException ex) {
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
				}				
				try {
					new InterfaceEmpCuisson(new LogicielEmpCuisson(new Utilisateur("dezf", "fezf", "Employé de Cuisson", "Briot", "Julien")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class CheckBoxRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			JCheckBox cb = new JCheckBox();
			cb.setSelected(((Boolean) value).booleanValue());
			return cb;
		}
	}	

	public class ProgressBarRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			JProgressBar bar = new JProgressBar();
			bar.setMaximum(10000);
			bar.setValue((int)value);
			return bar;
		}
	}
	
	public class UpdateProgressBar implements ActionListener{
		private int row, col, debut, duree;
		private DefaultTableModel model;
		
		public UpdateProgressBar(DefaultTableModel m, int r, int c, String de, int du) {
			model = m;
			row = r;
			col = c;
			int debutMinute = Integer.parseInt(de.substring(3, 5));
			int debutSeconde = Integer.parseInt(de.substring(6, 8));
			int actuelMinute = Calendar.getInstance().get(Calendar.MINUTE);
			int actuelSeconde = Calendar.getInstance().get(Calendar.SECOND);
			debut = (((actuelMinute-debutMinute)%60+60)%60)*60+(((actuelSeconde-debutSeconde)%60+60)%60);
			duree = du;

			if (debut != 0 && duree != 0 && ((duree*60.0)/debut != 0)){
				model.setValueAt((int)(10000/((duree*60.0)/debut)), row, col);
			} else if (debut != 0 && ((duree*60.0)/debut == 0)){
				model.setValueAt(10000, row, col);
			} 
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.setValueAt(((int)model.getValueAt(row, col)+(10000/(duree*60))), row, col);
				if((int)model.getValueAt(row, col)>10000){
					((Timer)e.getSource()).stop();
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
				((Timer)e.getSource()).stop();
			}
		}
		
	}
}

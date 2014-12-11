import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.util.Date;

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

	private JLabel utilisateurInfo;

	private JLabel dateCourante;

	
	public InterfaceEmpCuisson(LogicielEmpCuisson lec) {
		this.lec = lec;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1200, 500));
		setTitle("Interface employé de cuisson");

		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 246, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/************************************************************************************************************************/
		/********************************************** PANEL HAUT ************************************************************/
		/************************************************************************************************************************/
		JPanel panelHaut = new PanelInformation(this);
		contentPane.add(panelHaut, BorderLayout.NORTH);

		/************************************************************************************************************************/
		/********************************************** PANEL FRIGO ************************************************************/
		/************************************************************************************************************************/

		JPanel panelStockFrigo = new JPanel();
		panelStockFrigo.setBackground(new Color(218, 202, 251));
		panelStockFrigo.setPreferredSize(new Dimension(225, getHeight()-20));
		panelStockFrigo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelStockFrigo.setLayout(new GridBagLayout());

		JLabel lblStockFrigo = new JLabel("Stock Frigo");
		lblStockFrigo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblStockFrigo.setForeground(Color.RED);		
		panelStockFrigo.add(lblStockFrigo, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableStockFrigo = new JTable();
		tableStockFrigo.setEnabled(false);
		tableStockFrigo.setBackground(new Color(218, 202, 251));
		tableStockFrigo.setShowGrid(false);
		tableStockFrigo.setModel(new DefaultTableModel(new String[] {"Produit :", "Quantité :"},0));
		tableStockFrigo.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableStockFrigo.getColumnModel().getColumn(1).setPreferredWidth(75);
		JScrollPane scrollPane = new JScrollPane(tableStockFrigo);
		scrollPane.getViewport().setBackground(new Color(218, 202, 251));
		panelStockFrigo.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(panelStockFrigo, BorderLayout.WEST);

		/************************************************************************************************************************/
		/********************************************** PANEL CENTRE ************************************************************/
		/************************************************************************************************************************/

		panelCentre = new JPanel();
		panelCentre.setBackground(new Color(241, 246, 190));
		GridBagLayout layout = new GridBagLayout();
		panelCentre.setLayout(layout);		
		contentPane.add(panelCentre, BorderLayout.CENTER);

		/************************************************************************************************************************/
		/********************************************** PANEL CUIRE ************************************************************/
		/************************************************************************************************************************/

		JPanel panelCuire = new JPanel();
		panelCuire.setBackground(new Color(201, 241, 253));
		panelCuire.setMinimumSize(new Dimension(250, getHeight()/2-20));
		panelCuire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelCuire.setLayout(new GridBagLayout());

		JLabel lblCuire = new JLabel("A Cuire");
		lblCuire.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCuire.setForeground(Color.RED);
		panelCuire.add(lblCuire, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableCuire = new JTable();
		//tableCuire.setEnabled(false);
		tableCuire.setBackground(new Color(201, 241, 253));
		tableCuire.setShowGrid(false);
		tableCuire.setModel(new DefaultTableModel(new String[] {"Produit :", "Quantité :", ""}, 0));
		tableCuire.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableCuire.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableCuire.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableCuire.getColumnModel().getColumn(2).setCellRenderer(new MyCellRenderer());
		tableCuire.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
		scrollPane = new JScrollPane(tableCuire);
		scrollPane.getViewport().setBackground(new Color(201, 241, 253));
		panelCuire.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		
		JButton boutonValider = new JButton("Valider");
		panelCuire.add(boutonValider, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		panelCentre.add(panelCuire, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,  new Insets(0, 5, 5, 5), 0, 0));

		/************************************************************************************************************************/
		/********************************************** PANEL RAYON ************************************************************/
		/************************************************************************************************************************/

		JPanel panelRayon = new JPanel();
		panelRayon.setBackground(new Color(201, 241, 253));
		panelRayon.setMinimumSize(new Dimension(250, getHeight()/2-20));
		panelRayon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelRayon.setLayout(new GridBagLayout());

		JLabel lblRayon = new JLabel("Mettre en Rayon");
		lblRayon.setFont(new Font("Arial", Font.PLAIN, 18));
		lblRayon.setForeground(Color.RED);
		panelRayon.add(lblRayon, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableRayon = new JTable();
		//tableRayon.setEnabled(false);
		tableRayon.setBackground(new Color(201, 241, 253));
		tableRayon.setShowGrid(false);
		tableRayon.setModel(new DefaultTableModel(new String[] {"Produit :", "Quantité :", ""}, 0));
		tableRayon.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableRayon.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableRayon.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableRayon.getColumnModel().getColumn(2).setCellRenderer(new MyCellRenderer());
		tableRayon.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));

		scrollPane = new JScrollPane(tableRayon);
		scrollPane.getViewport().setBackground(new Color(201, 241, 253));
		panelRayon.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		boutonValider = new JButton("Valider");
		panelRayon.add(boutonValider, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		panelCentre.add(panelRayon, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,  new Insets(0, 5, 5, 5), 0, 0));


		/************************************************************************************************************************/
		/********************************************** PANEL FOUR ************************************************************/
		/************************************************************************************************************************/

		JPanel panelFour = new JPanel();
		panelFour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelFour.setMinimumSize(new Dimension(500, getHeight()/2-20));
		panelFour.setLayout(new GridBagLayout());

		JLabel lblFour = new JLabel("Au Four");
		lblFour.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFour.setForeground(Color.RED);
		panelFour.add(lblFour, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableFour = new JTable();
		//tableCuire.setEnabled(false);
		tableFour.setShowGrid(false);
		tableFour.setBackground(panelFour.getBackground());
		tableFour.setModel(new DefaultTableModel(new String[] {"Produit :", "Quantité :", "", ""}, 0));
		tableFour.getColumnModel().getColumn(0).setPreferredWidth(300);
		tableFour.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableFour.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableFour.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		tableFour.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorFour(new JCheckBox(), this));
		tableFour.getColumnModel().getColumn(3).setPreferredWidth(25);
		tableFour.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		tableFour.getColumnModel().getColumn(3).setCellEditor(new ButtonEditorFour(new JCheckBox(), this));		
		
		scrollPane = new JScrollPane(tableFour);
		panelFour.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		
		panelCentre.add(panelFour, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,  new Insets(5, 5, 0, 5), 0, 0));		

		/************************************************************************************************************************/
		/********************************************** PANEL VENTE ************************************************************/
		/************************************************************************************************************************/		

		JPanel panelStockVente = new JPanel();
		panelStockVente.setBackground(new Color(218, 202, 251));
		panelStockVente.setPreferredSize(new Dimension(225, getHeight()-20));
		panelStockVente.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelStockVente.setLayout(new GridBagLayout());

		JLabel lblStockVente = new JLabel("Stock Vente");
		lblStockVente.setFont(new Font("Arial", Font.PLAIN, 18));
		lblStockVente.setForeground(Color.RED);
		panelStockVente.add(lblStockVente, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		tableStockVente = new JTable();
		tableStockVente.setEnabled(false);
		tableStockVente.setBackground(new Color(218, 202, 251));
		tableStockVente.setShowGrid(false);
		tableStockVente.setModel(new DefaultTableModel(new String[] {"Produit :", "Quantité :"}, 0));
		tableStockVente.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableStockVente.getColumnModel().getColumn(1).setPreferredWidth(75);
		scrollPane = new JScrollPane(tableStockVente);
		scrollPane.getViewport().setBackground(new Color(218, 202, 251));
		panelStockVente.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		contentPane.add(panelStockVente, BorderLayout.EAST);

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
		
		setVisible(true);
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
			Boolean present = false;
			for (int j = 0; j < model.getRowCount(); j++) {
				if (model.getValueAt(j, 0).equals(donnees[i][0].toString()))
					present = true;
			}
			if (!present) model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString(), Boolean.FALSE});
		}
	}
	
	public void miseAJourTableRayon(){
		Object [][] donnees = lec.afficherProduitAMettreRayon();
		DefaultTableModel model = (DefaultTableModel) tableRayon.getModel();
		for (int i = 0; i < donnees.length; i++) {
			Boolean present = false;
			for (int j = 0; j < model.getRowCount(); j++) {
				if (model.getValueAt(j, 0).equals(donnees[i][0].toString()))
					present = true;
			}
			if (!present) model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString(), Boolean.FALSE});
		}
	}
	
	public void miseAJourTableFour(){
		Object [][] donnees = lec.afficherProduitFour();
		DefaultTableModel model = (DefaultTableModel) tableFour.getModel();
		for (int i = 0; i < donnees.length; i++) {
			model.addRow(new Object[] {donnees[i][0].toString(), donnees[i][1].toString(), "v", "x"});
		}
	}
	
	public class MyCellRenderer extends DefaultTableCellRenderer {
		 
        private static final long serialVersionUID = 1L;

        public Component getTableCellRendererComponent(JTable table,
        		Object value, boolean isSelected, boolean hasFocus, int row,
        		int column) {
        	Component cell = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

        	if (value instanceof Boolean) {
        		JCheckBox cb = new JCheckBox();
        		cb.setSelected(((Boolean) value).booleanValue());
        		cb.setBackground(new Color(201, 241, 253));
        		return cb;
        	}
        	return cell;
        }
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceEmpCuisson frame = new InterfaceEmpCuisson(new LogicielEmpCuisson(new Utilisateur("dezf", "fezf", "Employé de Cuisson", "Briot", "Julien")));
					//frame.setVisible(true);
					frame.miseAJourTableCuire();
					frame.miseAJourTableRayon();
					frame.miseAJourTableFour();
					frame.miseAJourTableVente();
					frame.miseAJourTableFrigo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void validerCuisson(int row) {
		int res = PopUp.afficherConfirmation();
		if (res == 0) {
			LogicielEmpCuisson.validerCuisson(tableFour.getValueAt(row, 0).toString(), tableFour.getValueAt(row, 1).toString());
			DefaultTableModel model = (DefaultTableModel) tableFour.getModel();
			model.removeRow(row);
		}
	}

	public void rejeterCuisson(int row) {
		int res = PopUp.afficherConfirmation();
		if (res == 0) {		
			LogicielEmpCuisson.rejeterCuisson(tableFour.getValueAt(row, 0).toString(), tableFour.getValueAt(row, 1).toString());
			DefaultTableModel model = (DefaultTableModel) tableFour.getModel();
			model.removeRow(row);
		}
	}
	
	public LogicielEmpCuisson getLogicielEmpCuisson() {
		return this.lec;
	}
}

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class PanelInformation extends JPanel{
	private InterfaceEmpCuisson iec;
	private InterfaceVente iv;
//	private InterfaceManager im;
	private JLabel utilisateurInfo;
	private JLabel dateCourante;
	private AbstractButton boutonDeco;

	public PanelInformation(InterfaceEmpCuisson intEmpCui){
		this.iec = intEmpCui;
		
		setBackground(new Color(241, 246, 190));
		setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
		
		utilisateurInfo = new JLabel(iec.getLogicielEmpCuisson().getPrenomNomUtilisateur()+"     |   ");
		add(utilisateurInfo);
		
		initialisation();	
		
		boutonDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfaceConnection(new LogicielConnection());
				iec.dispose();
			}
		});	
	}
	public PanelInformation(InterfaceVente intVente){
		this.iv = intVente;

		setBackground(new Color(241, 246, 190));
		//setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
		
		utilisateurInfo = new JLabel(iv.getLogicielVente().getPrenomNomUtilisateur()+"     |   ");
		add(utilisateurInfo);

		initialisation();	
		
		boutonDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfaceConnection(new LogicielConnection());
				iv.dispose();
			}
		});	
	}
	public PanelInformation(InterfaceManager intManager){
		this.im = intManager;
		
		setBackground(new Color(241, 246, 190));
		setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
	
		utilisateurInfo = new JLabel(im.getLogicielManager().getPrenomNomUtilisateur()+"     |   ");
		add(utilisateurInfo);
		
		initialisation();	
		
		boutonDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfaceConnection(new LogicielConnection());
				im.dispose();
			}
		});	
	}
	
	public void initialisation(){
		dateCourante = new JLabel(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(new Date())+"      ");
		add(dateCourante);
		JButton boutonAide = new JButton(new ImageIcon("images/aide.png"));
		boutonAide.setContentAreaFilled(false);
		boutonAide.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(boutonAide);
		boutonDeco = new JButton(new ImageIcon("images/deconnexion.png"));
		boutonDeco.setContentAreaFilled(false);
		boutonDeco.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(boutonDeco);

		boutonAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OuvrirPdf.ouvrirAide();
			}
		});	
		

        miseAJourDateThread tdate = new miseAJourDateThread(this);
        tdate.start();
	}
	public void miseAJourDate() {
		dateCourante.setText(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(new Date())+"      ");		
	}
}

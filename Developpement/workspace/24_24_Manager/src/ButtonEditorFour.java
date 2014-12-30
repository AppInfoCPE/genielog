import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

class ButtonEditorFour extends DefaultCellEditor {
	protected JButton button;

	private String label;
	private boolean isPushed;
	private int r;
	private int c;
	private InterfaceEmpCuisson interfaceEmpCuisson;

	public ButtonEditorFour(JCheckBox checkBox, InterfaceEmpCuisson iec) {
		super(checkBox);
		interfaceEmpCuisson = iec;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c==3){
					interfaceEmpCuisson.validerCuisson(r);
				} else if (c==4){
					interfaceEmpCuisson.rejeterCuisson(r);
				}
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		r = row;
		c = column;
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			// 
			// 
			JOptionPane.showMessageDialog(button, label + ": Ouch!");
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
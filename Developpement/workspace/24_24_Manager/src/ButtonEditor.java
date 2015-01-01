
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;
	  private String label;
	  private int c;

	  private int r;
	  private boolean isPushed;
	  private InterfaceVente interfaceVente;
	  private Object[][] donneesEnVente;
	  public ButtonEditor(JCheckBox checkBox, InterfaceVente iv,Object[][]  TabVente) {
			super(checkBox);
			interfaceVente = iv;
			donneesEnVente=TabVente;
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (c==4 ){
						//r=ligne 
						//JOptionPane.showMessageDialog(button, label + ": Ouch!");
						interfaceVente.mc.annulerPrduitVente(interfaceVente.numVente, (String) donneesEnVente[r][0]);
						interfaceVente.MAJTableVente();
						interfaceVente.completeResumeVente();
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
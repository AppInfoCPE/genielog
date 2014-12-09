package IHM;


import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 * Pour ton table, rajoute : nomDeVotreTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
 * 
 */
class MyTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		if (row % 2==0)
			cell.setBackground(Color.decode("#DACAFB"));
		else
			cell.setBackground(Color.decode("#DFEDD6"));
		return cell;
	}
}
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


class ButtonRendererFour extends JButton implements TableCellRenderer {

	public ButtonRendererFour() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
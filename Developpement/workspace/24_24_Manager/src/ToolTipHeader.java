/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author PC
 */
 class ToolTipHeader extends JTableHeader {
    String[] toolTips;

    public ToolTipHeader(TableColumnModel model) {
      super(model);
    }

    public String getToolTipText(MouseEvent e) {
      int col = columnAtPoint(e.getPoint());
      int modelCol = getTable().convertColumnIndexToModel(col);
      String retStr;
      try {
        retStr = toolTips[modelCol];
      } catch (NullPointerException ex) {
        retStr = "";
      } catch (ArrayIndexOutOfBoundsException ex) {
        retStr = "";
      }
      if (retStr.length() < 1) {
        retStr = super.getToolTipText(e);
      }
      return retStr;
    }

    public void setToolTipStrings(String[] toolTips) {
      this.toolTips = toolTips;
    }
  }




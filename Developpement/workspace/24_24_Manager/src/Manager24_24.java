import java.awt.Color;

import javax.swing.UIManager;



public class Manager24_24 {
	
	public static void main(String[] args) {
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
        
		LogicielConnection lc = new LogicielConnection();
	
		InterfaceConnection ic = new InterfaceConnection(lc);
	}
}

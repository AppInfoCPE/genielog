

public class Manager24_24 {
	
	public static void main(String[] args) {
		LogicielConnection lc = new LogicielConnection();
		// ICI c'est simplement le main, il faut r�fl�chier � mettre un niveau suppl�mentaire, 
		// donc un Controlleur pere des autres en gros pour faciliter le passage entre les diff�rentes fenetres
		InterfaceConnection ic = new InterfaceConnection(lc);
	}
}

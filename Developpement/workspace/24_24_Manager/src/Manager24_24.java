

public class Manager24_24 {
	
	public static void main(String[] args) {
		LogicielConnection lc = new LogicielConnection();
		// ICI c'est simplement le main, il faut réfléchier à mettre un niveau supplémentaire, 
		// donc un Controlleur pere des autres en gros pour faciliter le passage entre les différentes fenetres
		InterfaceConnection ic = new InterfaceConnection(lc);
	}
}

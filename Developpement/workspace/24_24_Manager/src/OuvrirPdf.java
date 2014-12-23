import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class OuvrirPdf {
	private OuvrirPdf(){

	}
	public static void ouvrirAide(){
		Desktop d = Desktop.getDesktop();
		try {
			d.open(new File("aide.pdf"));
		} catch (IOException e1) {
			System.out.println("Erreur ouverture aide format PDF.\n");
		}
	}
}


public class HeurePointe {
private  String id;
static String id_bis="0"; 
private String debut;
private String fin;

	HeurePointe(String debut, String fin){
		
		 int i_bis = Integer.parseInt(id_bis);
	 	 int i = i_bis;
	 	 setId(Integer.toString(i));
	 	 i_bis ++; 
	     id_bis=Integer.toString(i_bis);
		
		this.setDebut(debut);
		this.setFin(fin);
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDebut() {
		return debut;
	}
	
	public void setDebut(String debut) {
		this.debut = debut;
	}
	
	public String getFin() {
		return fin;
	}
	
	public void setFin(String fin) {
		this.fin = fin;
	}
}

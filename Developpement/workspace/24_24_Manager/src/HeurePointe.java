public class HeurePointe {
private  int id;
static int id_bis=0; 
private String debut;
private String fin;

	public HeurePointe(String debut, String fin){
		
		 
	 	/*int i = id_bis;
	 	 id_bis ++; 
	        */
		
		this.setDebut(debut);
		this.setFin(fin);
		
	}
        
       public HeurePointe(HeurePointe hp){
            this.debut=hp.getDebut();
            this.fin=hp.getFin();
        }
       public HeurePointe() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
        
        public String toString(){
         return this.getDebut()+ " - " + this.getFin();
        }
}

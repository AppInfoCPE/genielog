package IHM;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

//je fais un model Table pour bien gerer Jtable car nos jtable sont dynamique (ajouter/supprimer les lignes)
public class modelTableConfig extends AbstractTableModel{
    
    private Object[][] data;
    private String[] title;

    //Constructeur
    public modelTableConfig(Object[][] data, String[] title){
      this.data = data;
      this.title = title;
    }

    //Retourne le nombre de colonnes
    public int getColumnCount() {
      return this.title.length;
    }

    //Retourne le nombre de lignes
    public int getRowCount() {
      return this.data.length;
    }

    //Retourne la valeur à l'emplacement spécifié
    public Object getValueAt(int row, int col) {
      return this.data[row][col];
    }  
    public String getColumnName(int col) {
     return this.title[col];
    }
    //Retourne la classe de la donnée de la colonne
    public Class getColumnClass(int col){
  //On retourne le type de la cellule à la colonne demandée
  //On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
  //On choisit donc la première ligne
        return this.data[0][col].getClass();
    }
    //Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
    //Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
//Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
     //Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
	public boolean isCellEditable(int row, int col){
	//On appelle la méthode getValueAt qui retourne la valeur d'une cellule
	//Et on effectue un traitement spécifique si c'est un JButton
	if(getValueAt(0, col) instanceof JButton)
	return false;
	return true; 
	}
	public void setValueAt(Object value, int row, int col) {
	data[row][col] = value;
	fireTableCellUpdated(row, col);
	}
	public void removeRow(int position){
	   
	  int indice = 0, indice2 = 0;
	  int nbRow = this.getRowCount()-1;
	  int nbCol = this.getColumnCount();
	  Object temp[][] = new Object[nbRow][nbCol];
	   
	  for(Object[] value : this.data){
	     if(indice != position){
	        temp[indice2++] = value;
	     }
	     System.out.println("Indice = " + indice);
	     indice++;
	  }
	  this.data = temp;
	  temp = null;
	  //Cette méthode permet d'avertir le tableau que les données
	  //ont été modifiées, ce qui permet une mise à jour complète du tableau
	  this.fireTableDataChanged();
	}
	
	//Permet d'ajouter une ligne dans le tableau
	public void addRow(Object[] data){
	  int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();
	   
	  Object temp[][] = this.data;
	  this.data = new Object[nbRow+1][nbCol];
	   
	  for(Object[] value : temp)
	     this.data[indice++] = value;
	   
	      
	  this.data[indice] = data;
	  temp = null;
	  //Cette méthode permet d'avertir le tableau que les données
	  //ont été modifiées, ce qui permet une mise à jour complète du tableau
	  this.fireTableDataChanged();
	}


}




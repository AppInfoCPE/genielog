import java.util.Date;


public class Produit {
  private String id;
  private Date datePeremtion;
  private String status;
  
  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDatePeremtion() {
		return datePeremtion;
	}
	public void setDatePeremtion(Date datePeremtion) {
		this.datePeremtion = datePeremtion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

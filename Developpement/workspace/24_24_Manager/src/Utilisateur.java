public class Utilisateur {

	private String identifiant;
	private String motDePasse;
	private String role;
	private String nom;
	private String prenom;
	
	public Utilisateur(String login, String motDePasse, String role, String nom, String prenom) {
        this.identifiant = login;
        this.motDePasse = motDePasse;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
    }
	
	public Utilisateur(String login, String motDePasse, String role) {
        this.identifiant = login;
        this.motDePasse = motDePasse;
        this.role = role;
        this.nom = "";
        this.prenom = "";
    }	
	
	
    public String getLogin() {
		return identifiant;
	}

    public void setLogin(String login) {
		this.identifiant = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifiant == null) ? 0 : identifiant.hashCode());
        result = prime * result
                + ((motDePasse == null) ? 0 : motDePasse.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utilisateur other = (Utilisateur) obj;
        if (identifiant == null) {
            if (other.identifiant != null)
                return false;
        } else if (!identifiant.equals(other.identifiant))
            return false;
        if (motDePasse == null) {
            if (other.motDePasse != null)
                return false;
        } else if (!motDePasse.equals(other.motDePasse))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (role.equals("Manager"))
            return true;
        else if (!role.equals(other.role))
            return false;
        return true;
    }
}

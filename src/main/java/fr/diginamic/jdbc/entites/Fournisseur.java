package fr.diginamic.jdbc.entites;

public class Fournisseur {
	private Integer id;
	private String nom;

	public Fournisseur() {

	}

	public Fournisseur(String nom) {
		super();
		this.nom = nom;
	}

	public Fournisseur(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}

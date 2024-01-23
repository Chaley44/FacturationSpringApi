package com.codeinfini.facturation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String description;
	private float prixUnitaire;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produit", fetch = FetchType.LAZY)
//	List<LigneFacture> ligneFactures;
	
	public Produit() {
		
	}
	
	public Produit(Long id, String description, float prixUnitaire) {
		super();
		this.id = id;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", prixUnitaire=" + prixUnitaire
				+ "]";
	}

}

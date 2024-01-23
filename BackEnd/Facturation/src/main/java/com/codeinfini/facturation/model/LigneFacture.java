package com.codeinfini.facturation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LigneFacture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantite;
	private float totalHt;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "facture_id")
	private Facture facture;
	
	@ManyToOne()
	@JoinColumn(name = "produit_id")
	private Produit produit;
	
	public LigneFacture() {}
	
	public LigneFacture(Long id, int quantite) {
		super();
		this.id = id;
		this.quantite = quantite;	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public float getTotalHt() {
		float prix =produit.getPrixUnitaire();
		totalHt = prix * getQuantite();
		return totalHt;
	}

	public Facture getFacture() {
		return facture;
	}

	
	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public void setTotalHt(float totalHt) {
		this.totalHt = totalHt;
	}

	@Override
	public String toString() {
		return "LigneFacture [id=" + id + ", quantite=" + quantite + ", totalHt=" + totalHt + ", facture=" + facture
				+ ", produit=" + produit + "]";
	}
}

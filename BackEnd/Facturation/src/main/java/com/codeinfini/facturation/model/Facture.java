package com.codeinfini.facturation.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Facture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float montantTotalHt;
	private float montantTTC;
	private final float TVA =0.2f;
	private float totalTva;
	private LocalDate dateFacture;
	
//	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "facture", fetch = FetchType.LAZY)
	private List<LigneFacture> ligneFactures;
	
	@ManyToOne()
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Facture() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public float getTVA() {
		return TVA;
	}
	public float getMontantTotalHt() {
		for(LigneFacture ligneFac: ligneFactures) {
			montantTotalHt += ligneFac.getTotalHt();
		}
		return montantTotalHt;
	}
	
	public float getMontantTTC() {
		montantTTC = getMontantTotalHt() + getTotalTva();
		return montantTTC;
	}
	
	public float getTotalTva() {
		totalTva= getMontantTotalHt() * TVA;
		return totalTva;
	}
	
	
	public LocalDate getDateFacture() {
		return dateFacture;
	}
	public void setDateFacture(LocalDate dateFacture) {
		this.dateFacture = dateFacture;
	}

	
	public List<LigneFacture> getLigneFactures() {
		return ligneFactures;
	}

	
	public void setLigneFactures(List<LigneFacture> ligneFactures) {
		this.ligneFactures = ligneFactures;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setMontantTotalHt(float montantTotalHt) {
		this.montantTotalHt = montantTotalHt;
	}

	public void setMontantTTC(float montantTTC) {
		this.montantTTC = montantTTC;
	}

	public void setTotalTva(float totalTva) {
		this.totalTva = totalTva;
	}

}

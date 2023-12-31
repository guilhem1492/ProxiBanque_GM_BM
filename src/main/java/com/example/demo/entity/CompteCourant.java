package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CompteCourant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private String numCompte;
	private double solde;
	private String date;
	private double autorisationDecouvert = 1000.0;

	@JsonIgnore
	@OneToOne(mappedBy = "compteCourant")
	private Client client;

	public CompteCourant() {
	}

	public CompteCourant(String numCompte, double solde, String date) {
		this.numCompte = numCompte;
		this.solde = solde;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getAutorisationDecouvert() {
		return autorisationDecouvert;
	}

	public void setAutorisationDecouvert(double autorisationDecouvert) {
		this.autorisationDecouvert = autorisationDecouvert;
	}

	@Override
	public String toString() {
		return "CompteCourant [id=" + id + ", numCompte=" + numCompte + ", solde=" + solde + ", date=" + date
				+ ", autorisationDecouvert=" + autorisationDecouvert + ", client=" + client + "]";
	}

}

package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@NotEmpty(message = "Le nom est nécessaire.")
	private String nom;

	@NotEmpty(message = "Le prénom est nécessaire.")
	private String prenom;

	private String adresse;

	@NotNull
	private int codePostal;

	@NotEmpty
	private String ville;

	private String telephone;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "conseiller_id")
	private Conseiller conseiller;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "compteCourant_id", unique = true)
	private CompteCourant compteCourant;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "compteEpargne_id", unique = true)
	private CompteEpargne compteEpargne;

	public Client() {
	}

	public Client(String nom, String prenom, String adresse, int codePostal, String ville, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		this.compteEpargne = compteEpargne;
	}

//	public void virementComptesCourants(int montant, Client client) {
//		CompteCourant compteRecepteur = client.getCompteCourant();
//		double soldeEmetteur = this.compteCourant.getSolde();
//		if (montant < soldeEmetteur) {
//			double nouveauSoldeEmetteur = soldeEmetteur - montant;
//			this.compteCourant.setSolde(nouveauSoldeEmetteur);
//			double nouveauSoldeRecepteur = compteRecepteur.getSolde() + montant;
//			compteRecepteur.setSolde(nouveauSoldeRecepteur);
//		} else {
//			System.out.println("solde insuffisant");
//		}
//	}
//
//	public void virementCourantEpargne(int montant) {
//		double soldeEmetteur = this.compteCourant.getSolde();
//		if (montant < soldeEmetteur) {
//			double nouveauSoldeEmetteur = soldeEmetteur - montant;
//			this.compteCourant.setSolde(nouveauSoldeEmetteur);
//			double nouveauSoldeRecepteur = this.compteEpargne.getSolde() + montant;
//			this.compteEpargne.setSolde(nouveauSoldeRecepteur);
//		} else {
//			System.out.println("solde insuffisant");
//		}
//	}
//
//	public void virementEpargneCourant(int montant) {
//
//		double soldeEmetteur = this.compteEpargne.getSolde();
//		if (montant < soldeEmetteur) {
//			double nouveauSoldeEmetteur = soldeEmetteur - montant;
//			this.compteEpargne.setSolde(nouveauSoldeEmetteur);
//			double nouveauSoldeRecepteur = this.compteCourant.getSolde() + montant;
//			this.compteCourant.setSolde(nouveauSoldeRecepteur);
//		} else {
//			System.out.println("solde insuffisant");
//		}
//	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal="

				+ codePostal + ", ville=" + ville + ", telephone=" + telephone + ", conseiller=" + conseiller
				+ ", compteCourant=" + compteCourant + ", compteEpargne=" + compteEpargne + "]";
	}

}

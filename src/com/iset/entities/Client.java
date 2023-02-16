package com.iset.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cin;

	private String adresse;

	private String email;

	private String nom;

	private String prenom;

	private String tel;


	//bi-directional many-to-one association to Compte
	@OneToMany(mappedBy="client")
	private List<Compte> comptes;

	public Client() {
	}

	public int getCin() {
		return this.cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	public List<Compte> getComptes() {
		return this.comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public Compte addCompte(Compte compte) {
		getComptes().add(compte);
		compte.setClient(this);

		return compte;
	}

	public Compte removeCompte(Compte compte) {
		getComptes().remove(compte);
		compte.setClient(null);

		return compte;
	}

}
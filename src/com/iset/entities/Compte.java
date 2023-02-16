package com.iset.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the comptes database table.
 * 
 */
@Entity
@Table(name="comptes")
@NamedQuery(name="Compte.findAll", query="SELECT c FROM Compte c")
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_COMPTE")
	private int numCompte;

	private float solde;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="NUM_CLIENT")
	private Client client;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="compte")
	private List<Transaction> transactions;

	public Compte() {
	}

	public int getNumCompte() {
		return this.numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public float getSolde() {
		return this.solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setCompte(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setCompte(null);

		return transaction;
	}

}
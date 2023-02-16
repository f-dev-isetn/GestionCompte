package com.iset.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_TR")
	private int numTr;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_TR")
	private Date dateTr;
	 
	private float montant;

	private String type;

	//bi-directional many-to-one association to Compte
	@ManyToOne
	@JoinColumn(name="NUM_COMPTE")
	private Compte compte;

	public Transaction() {
	}

	public int getNumTr() {
		return this.numTr;
	}

	public void setNumTr(int numTr) {
		this.numTr = numTr;
	}

	public Date getDateTr() {
		return this.dateTr;
	}

	public void setDateTr(Date dateTr) {
		this.dateTr = dateTr;
	}

	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Compte getCompte() {
		return this.compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
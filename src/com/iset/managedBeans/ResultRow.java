package com.iset.managedBeans;

import java.io.Serializable;

public class ResultRow implements Serializable {
	

	private int numCompte;
	private float solde;
	private static final long serialVersionUID = 1L;

	
	public ResultRow(int numCompte, float solde) {
		this.numCompte = numCompte;
		this.solde = solde;
	}
	
	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

}

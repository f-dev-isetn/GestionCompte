package com.iset.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import com.iset.dao.CompteDAO;
import com.iset.dao.TransactionDAO;
import com.iset.entities.Compte;
import com.iset.entities.Transaction;

import javax.faces.bean.ViewScoped;


@ManagedBean (name="transactionMB")
@ViewScoped 
public class TransactionMB {
	private Transaction transaction=new Transaction();
	private Transaction selectedTransaction=new Transaction();
	private List<Transaction> listeTransaction;
	private List<Compte> listeCompte;
	TransactionDAO cltDao = new TransactionDAO();
	CompteDAO compteDAO= new CompteDAO();
	
	
	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	public Transaction getSelectedTransaction() {
		return selectedTransaction;
	}


	public void setSelectedTransaction(Transaction selectedTransaction) {
		this.selectedTransaction = selectedTransaction;
	}


	public List<Transaction> getListeTransaction() {
		return listeTransaction;
	}


	public void setListeTransaction(List<Transaction> listeTransaction) {
		this.listeTransaction = listeTransaction;
	}


	public List<Compte> getListeCompte() {
		return listeCompte;
	}


	public void setListeCompte(List<Compte> listeCompte) {
		this.listeCompte = listeCompte;
	}


	public TransactionMB() {
		// TODO Auto-generated constructor stub
	} 
		

	public String  add() {
		boolean ok;
		ok = compteDAO.debiter(this.transaction.getCompte(), this.transaction.getMontant(),this.transaction.getType());
		if(ok) {
			cltDao.ajouter(this.transaction);
			return "ajouter_Transaction.xhtml?faces-redirect=true";
		}else {
			return "ajouter_Transaction.xhtml?faces-redirect=true";
		}
		
		
	}
	
	//Initialiser le "DataTable" avec la liste des clients
		public void initListeCompte() 
		{
			listeCompte = compteDAO.listerTous();
			listeTransaction = cltDao.listerTous();
		}

}

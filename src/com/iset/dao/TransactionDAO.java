package com.iset.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.iset.entities.Compte;
import com.iset.entities.Transaction;
import com.iset.util.JPAutil;

public class TransactionDAO {

	private EntityManager entityManager=JPAutil.getEntityManager("UPGestionCompte");
	 
	//m�thode ajouter d'une entit� �  la bd
		 public   void ajouter(Object c)
		{
		 	EntityTransaction tx = entityManager.getTransaction();
		 	tx.begin();
		 	entityManager.persist(c);
		 	tx.commit();	  
		}
		 
		 //m�thode modifier d'une entit� �  partir de la bd
		 public   void modifier(Transaction c)
			{
			 	EntityTransaction tx = entityManager.getTransaction();
			 	tx.begin();
			 	entityManager.merge(c);
			 	tx.commit();
			 	  
			}
		 
		 //m�thode Supprimer d'une entit� �  partir de la bd
		 public  void supprimer(Transaction c)
		{ 
			EntityTransaction tx = entityManager.getTransaction();
		    tx.begin();
		    c=entityManager.merge(c); // important
		    entityManager.remove(c);
		    tx.commit();  
		}
		 
		 //m�thode Consulter d'une entit� �  partir de la bd
		 public  Transaction consulter(Object id)
		{
		 	return entityManager.find(Transaction.class, id);
			}
		 
		 //m�thode pour lister tous les objets�  partir de la bd
		 public List<Transaction> listerTous() {
		
			List<Transaction> clients =entityManager.createQuery( "select c from Transaction c").getResultList();
			 return clients;
			 }
		 
}

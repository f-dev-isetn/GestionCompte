package com.iset.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.iset.entities.Compte;
import com.iset.entities.Transaction;
import com.iset.util.JPAutil;

public class TransactionDAO {

	private EntityManager entityManager=JPAutil.getEntityManager("UPGestionCompte");
	 
	//méthode ajouter d'une entité à  la bd
		 public   void ajouter(Object c)
		{
		 	EntityTransaction tx = entityManager.getTransaction();
		 	tx.begin();
		 	entityManager.persist(c);
		 	tx.commit();	  
		}
		 
		 //méthode modifier d'une entité à  partir de la bd
		 public   void modifier(Transaction c)
			{
			 	EntityTransaction tx = entityManager.getTransaction();
			 	tx.begin();
			 	entityManager.merge(c);
			 	tx.commit();
			 	  
			}
		 
		 //méthode Supprimer d'une entité à  partir de la bd
		 public  void supprimer(Transaction c)
		{ 
			EntityTransaction tx = entityManager.getTransaction();
		    tx.begin();
		    c=entityManager.merge(c); // important
		    entityManager.remove(c);
		    tx.commit();  
		}
		 
		 //méthode Consulter d'une entité à  partir de la bd
		 public  Transaction consulter(Object id)
		{
		 	return entityManager.find(Transaction.class, id);
			}
		 
		 //méthode pour lister tous les objetsà  partir de la bd
		 public List<Transaction> listerTous() {
		
			List<Transaction> clients =entityManager.createQuery( "select c from Transaction c").getResultList();
			 return clients;
			 }
		 
}

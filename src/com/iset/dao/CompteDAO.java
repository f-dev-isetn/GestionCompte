package com.iset.dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import com.iset.entities.Compte;
import com.iset.entities.Transaction;
import com.iset.managedBeans.ResultRow;
import com.iset.util.JPAutil;

public class CompteDAO {
	private EntityManager entityManager = JPAutil.getEntityManager("UPGestionCompte");

	// méthode ajouter d'une entité à la bd
	public void ajouter(Object c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(c);
		tx.commit();

	}

	// méthode modifier d'une entité à partir de la bd
	public void modifier(Compte c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(c);
		tx.commit();

	}

	// méthode Supprimer d'une entité à partir de la bd
	public void supprimer(Compte c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		c = entityManager.merge(c); // important
		entityManager.remove(c);
		tx.commit();
	}

	// méthode Consulter d'une entité à partir de la bd
	public Compte consulter(Object id) {
		return entityManager.find(Compte.class, id);
	}

	// méthode pour lister tous les objetsà partir de la bd
	public List<Compte> listerTous() {

		List<Compte> clients = entityManager.createQuery("select c from Compte c").getResultList();
		return clients;
	}

	public boolean debiter(Compte compte, float montant, String type) {
		CompteDAO compteDAO = new CompteDAO();
		if (type.equals("débit")) {
			float result = compte.getSolde() + montant;
			compte.setSolde(result);
			compteDAO.modifier(compte);
			return true;
		} else {
			if (compte.getSolde() >= montant) {
				float result = compte.getSolde() - montant;
				compte.setSolde(result);
				compteDAO.modifier(compte);
				return true;
			} else {
				JOptionPane jop1 = new JOptionPane();
				jop1.showMessageDialog(null, "Oops !", "Votre solde ne pas insuffisant", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

	}

	public List<Object[]> listerGraph() {
		return entityManager.createQuery("select c.numCompte, c.solde from Compte c").getResultList();
	}

}

package com.iset.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import com.iset.dao.ClientDao;
import com.iset.dao.CompteDAO;
import com.iset.entities.Client;
import com.iset.entities.Compte;

@ManagedBean(name = "compteMB")
@ViewScoped
public class CompteMB {
	private Compte compte = new Compte();
	private Compte selectedCompte = new Compte();
	private List<Compte> listeComptes = new ArrayList<Compte>();
	private List<Client> lisetClient = new ArrayList<Client>();
	CompteDAO cltDao = new CompteDAO();
	ClientDao clientDao = new ClientDao();

	public CompteMB() {

	}

	public String add() {
		cltDao.ajouter(this.compte);
		return "ajouter_compte.xhtml?faces-redirect=true";

	}

	public String modifier() {
		cltDao.modifier(this.selectedCompte);
		return "ajouter.xhtml?faces-redirect=true";

	}

	public String supprimer() {
		cltDao.supprimer(selectedCompte);
		return "ajouter.xhtml?faces-redirect=true";

	}

//Initialiser le "DataTable" avec la liste des clients
	public void initListeClients() {
		listeComptes = cltDao.listerTous();
		lisetClient = clientDao.listerTous();
	}

	public List<Client> getLisetClient() {
		return lisetClient;
	}

	public void setLisetClient(List<Client> lisetClient) {
		this.lisetClient = lisetClient;
	}

	// Récupérer le code client à modifier et initialiser le formulaire avec ses
	// informations
	public void initForm() {
		int code;
		code = Integer
				.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("code"));

		Compte clt = new Compte();
		clt = cltDao.consulter(code);

		if (clt != null)
			this.selectedCompte = clt;

	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Compte getSelectedCompte() {
		return selectedCompte;
	}

	public void setSelectedCompte(Compte selectedCompte) {
		this.selectedCompte = selectedCompte;
	}

	public List<Compte> getListeComptes() {
		return listeComptes;
	}

	public void setListeComptes(List<Compte> listeComptes) {
		this.listeComptes = listeComptes;
	}

}

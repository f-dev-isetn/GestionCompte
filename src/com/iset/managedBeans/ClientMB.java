package com.iset.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


import javax.faces.bean.ViewScoped;
import com.iset.dao.ClientDao;
import com.iset.entities.Client;

@ManagedBean(name = "clientMB")
@ViewScoped
public class ClientMB {
	private Client client = new Client();
	private Client selectedClient = new Client();
	private List<Client> listeClients;
	
	ClientDao cltDao = new ClientDao();

	public ClientMB() {

	}

	public void msgRetour(String mes) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mes, " test   ");
		ctx.addMessage(null, msg);

	}
	
	public String add() {

		cltDao.ajouter(this.client);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Check saved!", null));
		
		try {
			

		} catch (Exception e) {

			throw e;

		}

		return "ajouter.xhtml?faces-redirect=true";

	}

	public String modifier() {
		cltDao.modifier(this.selectedClient);
		return "ajouter.xhtml?faces-redirect=true";

	}

	public String supprimer() {
		cltDao.supprimer(selectedClient);
		return "ajouter.xhtml?faces-redirect=true";

	}

	// Initialiser le "DataTable" avec la liste des clients
	public void initListeClients() {
		listeClients = cltDao.listerTous();
		

	}

	// Récupérer le code client à modifier et initialiser le formulaire avec ses
	// informations
	public void initForm() {
		int code;
		code = Integer
				.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("code"));

		Client clt = new Client();
		clt = cltDao.consulter(code);

		if (clt != null)
			this.selectedClient = clt;

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

}

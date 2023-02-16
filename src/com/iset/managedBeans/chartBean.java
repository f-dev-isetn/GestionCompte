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

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.iset.entities.Compte;

import com.iset.dao.ClientDao;

@ManagedBean
@ViewScoped
public class chartBean {

	private BarChartModel barModel;

	private List<Compte> comptes = new ArrayList<Compte>();;

	private CompteDAO cptDao = new CompteDAO();

	private ArrayList<ResultRow> resultRows = new ArrayList<ResultRow>();

	@PostConstruct
	public void init() {
		initResultRows();

		try {
			createBarModel();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	private ArrayList<ResultRow> initResultRows() {

		/*
		 * try (Connection connection = DriverManager.getConnection(
		 * "jdbc:mysql://localhost:3306/gestioncompte?serverTimezone=UTC&useSSL=false",
		 * "root", "root")) { String sql = "SELECT num_Compte, solde FROM comptes";
		 * PreparedStatement statement = connection.prepareStatement(sql); ResultSet
		 * resultSet = statement.executeQuery();
		 */
			/*
			 * comptes = (List<Compte>) cptDao.listerGraph(); for (Compte cp : comptes) {
			 * System.out.println("- - - " + cp.getSolde()); }
			 */

			List<Object[]> list = (List<Object[]>) cptDao.listerGraph();
			// List<Compte> comptes = (List<Compte>) cptDao.listerGraph();
			List<Compte> mylist = new ArrayList<>();
			if (list != null) {
				for (Object[] ob : list) {

					Compte db = new Compte();// or you use setter method for class member initialize.
					db.setNumCompte((int) (ob[0]));
					db.setSolde((float) (ob[1]));
					mylist.add(db);
					resultRows.add(new ResultRow((int) (ob[0]), (float) (ob[1])));
				}
			}
			/*
			 * for (Compte compte : mylist) { System.out.println("- - - -" +
			 * compte.getNumCompte() + "-----" + compte.getSolde()); resultRows.add(new
			 * ResultRow(compte.getNumCompte(), compte.getSolde())); }
			 */
/*
			while (resultSet.next()) {
				resultRows.add(new ResultRow(resultSet.getInt("num_Compte"), resultSet.getFloat("solde")));
			}
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return resultRows;
	}

	private void createBarModel() throws NoSuchFieldException, SecurityException {
		barModel = new BarChartModel();
		barModel.setTitle("Account Balances");
		barModel.setLegendPosition("ne");
		barModel.setAnimate(true);
		barModel.setShowDatatip(true);
		barModel.setShowPointLabels(true);

		ChartSeries accounts = new ChartSeries();
		accounts.setLabel("Account");

		for (ResultRow resultRow : resultRows) {

			accounts.set(resultRow.getNumCompte(), resultRow.getSolde());

		}

		barModel.addSeries(accounts);
	}

}

package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivell {
	private Long codiNivell;
	private String nomNivellCA;
	private String nomNivellES;
	private String nomNivellEN;
	private Connection dbConnection;
	
	public Nivell(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean LoadItem(Long codiNivell) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from Nivell where nivell.codinivell = '" + codiNivell + "'");
			if(ors.next()) {
				fillObject(ors);
				ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
			//TODO a veure que fem
			try {
				ors.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	private void fillObject(ResultSet ors) {
		try {
			this.codiNivell = ors.getLong("codinivell");
			this.nomNivellCA = ors.getString("nomnivellca");
			this.nomNivellES = ors.getString("nomnivelles");
			this.nomNivellEN = ors.getString("nomnivellen");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}

	public Long getCodiNivell() {
		return codiNivell;
	}

	public void setCodiNivell(Long codiNivell) {
		this.codiNivell = codiNivell;
	}

	public String getNomNivellCA() {
		return nomNivellCA;
	}

	public void setNomNivellCA(String nomNivellCA) {
		this.nomNivellCA = nomNivellCA;
	}

	public String getNomNivellES() {
		return nomNivellES;
	}

	public void setNomNivellES(String nomNivellES) {
		this.nomNivellES = nomNivellES;
	}

	public String getNomNivellEN() {
		return nomNivellEN;
	}

	public void setNomNivellEN(String nomNivellEN) {
		this.nomNivellEN = nomNivellEN;
	}
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO nivell(nomnivellca, nomnivelles, nomnivellen) VALUES(?, ?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setString(1, getNomNivellCA());
	        pst.setString(2, getNomNivellES());
	        pst.setString(3, getNomNivellEN());
	        
	        pst.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}

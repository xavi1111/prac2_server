package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TipoLocal {
	private Long codiTipoLocal;
	private String nomTipoLocalCA;
	private String nomTipoLocalES;
	private String nomTipoLocalEN;
	private Connection dbConnection;

	public TipoLocal(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}	
	
	public Boolean LoadItem(Long codiTipoLocal) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from TipoLocal where tipolocal.coditipolocal = '" + codiTipoLocal + "'");
			if(ors.next()) {
				fillObject(ors);
				ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
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
			this.codiTipoLocal = ors.getLong("coditipolocal");
			this.nomTipoLocalCA = ors.getString("nomtipolocalca");
			this.nomTipoLocalES = ors.getString("nomtipolocales");
			this.nomTipoLocalEN = ors.getString("nomtipolocalen");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}

	public Long getCodiTipoLocal() {
		return codiTipoLocal;
	}

	public void setCodiTipoLocal(Long codiTipoLocal) {
		this.codiTipoLocal = codiTipoLocal;
	}

	public String getNomTipoLocalCA() {
		return nomTipoLocalCA;
	}

	public void setNomTipoLocalCA(String nomTipoLocalCA) {
		this.nomTipoLocalCA = nomTipoLocalCA;
	}

	public String getNomTipoLocalES() {
		return nomTipoLocalES;
	}

	public void setNomTipoLocalES(String nomTipoLocalES) {
		this.nomTipoLocalES = nomTipoLocalES;
	}

	public String getNomTipoLocalEN() {
		return nomTipoLocalEN;
	}

	public void setNomTipoLocalEN(String nomTipoLocalEN) {
		this.nomTipoLocalEN = nomTipoLocalEN;
	}
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO tipolocal(nomtipolocalca, nomtipolocales, nomtipolocalen) VALUES(?, ?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setString(1, getNomTipoLocalCA());
	        pst.setString(2, getNomTipoLocalES());
	        pst.setString(3, getNomTipoLocalEN());
	        pst.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}

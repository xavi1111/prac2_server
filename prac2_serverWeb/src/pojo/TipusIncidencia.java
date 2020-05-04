package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class TipusIncidencia {
	
	private Integer codiTipusIncidencia;
	private String descripcio;
	
	private Connection dbConnection;
	
	public TipusIncidencia(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public boolean loadItem(String descripcio) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("SELECT * FROM tipusIncidencia WHERE tipusincidencia.descripcio = '" + descripcio + "'");
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
	
	public boolean loadItem() {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("SELECT * FROM tipusIncidencia WHERE tipusincidencia.codiTipusIncidencia = '" + codiTipusIncidencia + "'");
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
			this.setCodiTipusIncidencia(ors.getInt("codiTipusIncidencia"));
			this.setDescripcio(ors.getString("descripcio"));
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}

	public Integer getCodiTipusIncidencia() {
		return codiTipusIncidencia;
	}

	public void setCodiTipusIncidencia(Integer codiTipusIncidencia) {
		this.codiTipusIncidencia = codiTipusIncidencia;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO tipusIncidencia(descripcio) VALUES(?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setString(1, getDescripcio());
	        pst.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}

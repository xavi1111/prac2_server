package pojo;

import java.sql.Connection;
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
	
	public boolean loadItem(Long codiTipusIncidencia) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from tipusIncidencia where tipusincidencia.codiTipusIncidencia = '" + codiTipusIncidencia + "'");
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
}

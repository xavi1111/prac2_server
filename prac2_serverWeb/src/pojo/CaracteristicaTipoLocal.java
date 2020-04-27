package pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CaracteristicaTipoLocal {
	private Long codicaracteristicatipolocal;
	private Long codicaracteristica;
	private Long coditipolocal;
	private Connection dbConnection;

	public CaracteristicaTipoLocal (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean LoadItem(Long codiNivell) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from caracteristicatipolocal where caracteristicatipolocal.codicaracteristicatipolocal = '" + codicaracteristicatipolocal + "'");
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
			this.codicaracteristicatipolocal = ors.getLong("codicaracteristicatipolocal");
			this.codicaracteristica = ors.getLong("codicaracteristica");
			this.coditipolocal = ors.getLong("coditipolocal");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}
	
	public void setCodiCaracteristicaTipoLocal (Long codicaracteristicatipolocal) {
		this.codicaracteristicatipolocal = codicaracteristicatipolocal;
	}
	
	public Long getCodiCaracteristicaTipoLocal() {
		return this.codicaracteristicatipolocal;
	}
	
	public void setCodiCaracteristica (Long codicaracteristica) {
		this.codicaracteristica = codicaracteristica;
	}
	
	public Long getCodiCaracteristica() {
		return this.codicaracteristica;
	}
	
	public void setCodiTipoLocal (Long coditipolocal) {
		this.coditipolocal = coditipolocal;
	}
	
	public Long getCodiTipoLocal() {
		return this.coditipolocal;
	}
	
	
	

}

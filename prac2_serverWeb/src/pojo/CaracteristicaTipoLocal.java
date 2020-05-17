package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicaTipoLocal {
	private Long codicaracteristicatipolocal;
	private Long codicaracteristica;
	private Long coditipolocal;
	private Connection dbConnection;

	public CaracteristicaTipoLocal (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean LoadItem(Long codicaracteristica, Long coditipolocal) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("SELECT * FROM caracteristicatipolocal WHERE caracteristicatipolocal.codicaracteristica = '" + codicaracteristica + "' AND caracteristicatipolocal.coditipolocal = '" + coditipolocal + "'");
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

	public Boolean LoadItem() {
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
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO caracteristicatipolocal(codicaracteristica, coditipolocal) VALUES(?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setLong(1, getCodiCaracteristica());
	        pst.setLong(2, getCodiTipoLocal());
	        pst.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public List<CaracteristicaTipoLocal> getList(Long codiCaracteristicaTipoLocal, Long codiCaracteristica, Long codiTipoLocal) throws SQLException {
		List<CaracteristicaTipoLocal> list = new ArrayList<CaracteristicaTipoLocal>();
		ResultSet ors = null;
		try {
			String query = "select * from public.get_caracteristica_tipo_local(?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setLong(1, codiCaracteristicaTipoLocal);
			pst.setLong(2, codiCaracteristica);
			pst.setLong(3, codiTipoLocal);
	        ors = pst.executeQuery();
	        while(ors.next()) {
	        	CaracteristicaTipoLocal caracteristicaTipoLocalAux = new CaracteristicaTipoLocal(dbConnection);
	        	caracteristicaTipoLocalAux.fillObject(ors);
	        	list.add(caracteristicaTipoLocalAux);
	        }
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	

}

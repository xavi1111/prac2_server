package pojo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Local {
	private Long codiLocal;
	private Long codiTipoLocal;
	private Long codiCarrer;
	private String nomCarrer;
	private String nomVia;
	private Long numero;
	private String nomLocal;
	private String observacions;
	private String verificat;
	private Connection dbConnection;
	

	public Local (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean LoadItem(Long codiNivell) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from local where local.codilocal = '" + codiLocal + "'");
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
			this.codiLocal = ors.getLong("codilocal");
			this.codiTipoLocal = ors.getLong("coditipoLocal");
			this.codiCarrer = ors.getLong("codicarrer");
			this.nomCarrer = ors.getString("nomcarrer");
			this.nomVia = ors.getString("nomvia");
			this.numero = ors.getLong("numero");
			this.nomVia = ors.getString("nomlocal");
			this.nomVia = ors.getString("observacions");
			this.nomVia = ors.getString("verificat");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}


	public Long getCodiLocal() {
		return codiLocal;
	}

	public void setCodiLocal(Long codiLocal) {
		this.codiLocal = codiLocal;
	}

	public Long getCodiTipoLocal() {
		return codiTipoLocal;
	}

	public void setCodiTipoLocal(Long codiTipoLocal) {
		this.codiTipoLocal = codiTipoLocal;
	}
	
	public Long getCodiCarrer() {
		return codiCarrer;
	}

	public void setCodiCarrer(Long codiCarrer) {
		this.codiCarrer = codiCarrer;
	}

	public String getNomCarrer() {
		return nomCarrer;
	}

	public void setNomCarrer(String nomCarrer) {
		this.nomCarrer = nomCarrer;
	}

	public String getNomVia() {
		return nomVia;
	}

	public void setNomVia(String nomVia) {
		this.nomVia = nomVia;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNomLocal() {
		return nomLocal;
	}

	public void setNomLocal(String nomLocal) {
		this.nomLocal = nomLocal;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public String getVerificat() {
		return verificat;
	}

	public void setVerificat(String verificat) {
		this.verificat = verificat;
	}
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO local(coditipolocal, codicarrer, nomcarrer, nomvia, numero, nomlocal, observacions, verificat) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setLong(1, getCodiTipoLocal());
	        pst.setLong(2, getCodiCarrer());
	        pst.setString(3, getNomCarrer());
	        pst.setString(4, getNomVia());
	        pst.setLong(5, getNumero());
	        pst.setString(6, getNomLocal());
	        pst.setString(7, getObservacions());
	        pst.setString(8, getVerificat());
	        pst.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}

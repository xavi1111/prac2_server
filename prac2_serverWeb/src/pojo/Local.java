package pojo;

import java.sql.Statement;
import java.sql.Connection;
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
			this.codiLocal = ors.getLong("codiLocal");
			this.codiTipoLocal = ors.getLong("codiTipoLocal");
			this.codiCarrer = ors.getLong("codiCarrer");
			this.nomCarrer = ors.getString("nomCarrer");
			this.nomVia = ors.getString("nomVia");
			this.numero = ors.getLong("numero");
			this.nomVia = ors.getString("nomLocal");
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
}

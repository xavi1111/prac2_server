package pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Caracteristica {
	
	private Long codiCaracteristica;
	private String nomCaracteristicaCA;
	private String nomCaracteristicaES;
	private String nomCaracteristicaEN;
	private Integer tipo;
	private Long codiNivell;
	
	private Connection dbConnection;
	
	public Caracteristica(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean loadItem(Long codiCaracteristica) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from accessibilitat where caracteristica.codicaracteristica = '" + codiCaracteristica + "'");
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
			this.codiCaracteristica = ors.getLong("codiCaracteristica");
			this.nomCaracteristicaCA = ors.getString("nomCaracteristicaCA");
			this.nomCaracteristicaES = ors.getString("nomCaracteristicaES");
			this.nomCaracteristicaEN = ors.getString("nomCaracteristicaEN");
			this.tipo = ors.getInt("tipo");
			this.codiNivell = ors.getLong("codiNivell");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}
	
	public Long getCodiCaracteristica() {
		return codiCaracteristica;
	}
	
	public void setCodiCaracteristica(Long codiCaracteristica) {
		this.codiCaracteristica = codiCaracteristica;
	}

	public String getNomCaracteristicaCA() {
		return nomCaracteristicaCA;
	}

	public void setNomCaracteristicaCA(String nomCaracteristicaCA) {
		this.nomCaracteristicaCA = nomCaracteristicaCA;
	}

	public String getNomCaracteristicaES() {
		return nomCaracteristicaES;
	}

	public void setNomCaracteristicaES(String nomCaracteristicaES) {
		this.nomCaracteristicaES = nomCaracteristicaES;
	}

	public String getNomCaracteristicaEN() {
		return nomCaracteristicaEN;
	}

	public void setNomCaracteristicaEN(String nomCaracteristicaEN) {
		this.nomCaracteristicaEN = nomCaracteristicaEN;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Long getCodiNivell() {
		return codiNivell;
	}

	public void setCodiNivell(Long codiNivell) {
		this.codiNivell = codiNivell;
	}
}

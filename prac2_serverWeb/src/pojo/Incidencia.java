package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Incidencia {
	
	private Long idIncidencia;
	private Timestamp dataHora; 
	private Integer codiTipusIncidencia;
	private Long idRegistre;
	private String nomTaula;
	
	private Connection dbConnection;
	
	public Incidencia(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public boolean loadItem(Long idRegistre, Integer codiTipusIncidencia, Timestamp dataHora, String nomTaula) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("SELECT * FROM incidencia WHERE incidencia.idRegistre = '" + idRegistre + "'"
					+ "AND incidencia.codiTipusIncidencia = '" + codiTipusIncidencia + "'"
					+ "AND incidencia.dataHora = '" + dataHora + "'"
					+ "AND incidencia.nomTaula = '" + nomTaula + "'");
			if(ors.next()) {
				fillObject(ors);
				if(ors!=null)
					ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
			//TODO a veure que fem
			try {
				if(ors!=null)
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
			ors = statement.executeQuery("SELECT * FROM incidencia WHERE incidencia.idIncidencia = '" + idIncidencia + "'");
			if(ors.next()) {
				fillObject(ors);
				if(ors!=null)
					ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
			//TODO a veure que fem
			try {
				if(ors!=null)
					ors.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	private void fillObject(ResultSet ors) {
		try {
			this.setIdIncidencia(ors.getLong("idIncidencia"));
			this.setDataHora(ors.getTimestamp("dataHora"));
			this.setCodiTipusIncidencia(ors.getInt("codiTipusIncidencia"));
			this.setIdRegistre(ors.getLong("idRegistre"));
			this.setNomTaula(ors.getString("nomTaula"));
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}
	
	public Long getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public Integer getCodiTipusIncidencia() {
		return codiTipusIncidencia;
	}
	
	public void setCodiTipusIncidencia(Integer codiTipusIncidencia) {
		this.codiTipusIncidencia = codiTipusIncidencia;
	}
	
	public Long getIdRegistre() {
		return idRegistre;
	}

	public void setIdRegistre(Long idRegistre) {
		this.idRegistre = idRegistre;
	}

	public String getNomTaula() {
		return nomTaula;
	}

	public void setNomTaula(String nomTaula) {
		this.nomTaula = nomTaula;
	}
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO log.incidencia(dataHora, codiTipusIncidencia, idRegistre, nomTaula) VALUES(?, ?, ?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setTimestamp(1, getDataHora());
	        pst.setLong(2, getCodiTipusIncidencia());
	        pst.setLong(3, getIdRegistre());
	        pst.setString(4, getNomTaula());
	        pst.executeQuery();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

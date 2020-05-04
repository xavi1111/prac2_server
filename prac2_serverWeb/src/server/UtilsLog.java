package server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import pojo.Incidencia;
import pojo.Local;

public class UtilsLog {
	
	private Connection dbConnection;
	
	public UtilsLog(Connection dbLogConnection) {
		this.dbConnection = dbLogConnection;
	}
	
	public void registrarLog(Long primaryKey, Integer tipusIncidencia, String nomTaula) throws SQLException {
		Incidencia dbLog = new Incidencia(dbConnection);
		dbLog.setCodiTipusIncidencia(tipusIncidencia);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		dbLog.setDataHora(timestamp);
		dbLog.setIdRegistre(primaryKey);
		dbLog.setNomTaula(nomTaula);
		dbLog.addItem();
	}
}

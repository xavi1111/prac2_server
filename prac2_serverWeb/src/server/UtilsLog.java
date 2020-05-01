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
	
	public void registrarLinea(Local local, Integer tipusIncidencia) throws SQLException {
			Incidencia dbLog = new Incidencia(dbConnection);
			dbLog.setCodiTipusIncidencia(tipusIncidencia);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			dbLog.setDataHora(timestamp);
			dbLog.setIdRegistre(local.getCodiLocal());
			dbLog.setNomTaula("Local");
			dbLog.addItem();
	}
}

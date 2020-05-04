package server;

import java.sql.Connection;
import java.sql.SQLException;

import pojo.*;

import server.*;

public class UtilsLocal {
	
	private Connection dbConnection;
	private Connection dbLogConnection;
	
	private Integer alta = 1;
	private Integer modif = 2;
	private Integer baixa = 3;
	private String nomTaula = "Local";
	
	public UtilsLocal(Connection dbConnection, Connection dbLogConnection) {
		this.dbConnection = dbConnection;
		this.dbLogConnection = dbLogConnection;
	}
	
	public Local altaLocal(Local local) throws SQLException {
		UtilsLog log = new UtilsLog(dbLogConnection);
		
		if(local.loadItem(local.getCodiTipoLocal(), local.getNomLocal()))
			return null;
		else
		{
			local.addItem();
			local.loadItem();
			log.registrarLog(local.getCodiLocal(), alta, nomTaula);
			return local;
		}
	}
	
	public Local modificacioLocal(Local local) throws SQLException {
		UtilsLog log = new UtilsLog(dbLogConnection);
		
		if(!local.loadItem())
			return null;
		else {
			local.update();
			local.loadItem();
			log.registrarLog(local.getCodiLocal(), modif, nomTaula);
			return local;
		}
	}
	
	public boolean baixaLocal(Local local) throws SQLException {
		UtilsLog log = new UtilsLog(dbLogConnection);
		
		if(!local.loadItem())
			return false;
		else {
			local.delete();
			log.registrarLog(null, baixa, nomTaula);
			return true;
		}
	}
}

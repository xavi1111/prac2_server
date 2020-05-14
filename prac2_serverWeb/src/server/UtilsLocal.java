package server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	
	public Local altaLocal(Local local) throws Exception {
		UtilsLog log = new UtilsLog(dbLogConnection);
		
		if(local.loadItem(local.getCodiTipoLocal(), local.getNomLocal()))
			return null;
		else
		{
			if(!local.addItem())
				throw new Exception();
			local.loadItem();
			log.registrarLog(local.getCodiLocal(), alta, nomTaula);
			return local;
		}
	}
	
	public Local modificacioLocal(Local local) throws SQLException {
		UtilsLog log = new UtilsLog(dbLogConnection);
		local.update();
		local.loadItem();
		log.registrarLog(local.getCodiLocal(), modif, nomTaula);
		return local;
		
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
	
	/*public List<Local> getListLocals(Long codiLocal, Long codiTipoLocal, Long codiCarrer, String nomCarrer, String nomVia, Long numero, String nomLocal, String observacions, String verificat) throws SQLException{
		Local local = new Local(dbConnection);
		//TODO tractament de dades i llista si es nescessari
		try {
			return local.getList(codiLocal, codiTipoLocal, codiCarrer, nomCarrer, nomVia, numero, nomLocal, observacions, verificat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}*/
}

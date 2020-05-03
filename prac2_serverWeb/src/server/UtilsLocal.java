package server;

import java.sql.Connection;

import pojo.*;

public class UtilsLocal {
	
	private Connection dbConnection;
	private Connection dbLogConnection;
	
	private Long alta = 1L;
	private Long modif = 2L;
	private Long baixa = 3L;
	
	public UtilsLocal(Connection dbConnection, Connection dbLogConnection) {
		this.dbConnection = dbConnection;
		this.dbLogConnection = dbLogConnection;
	}
	
	public boolean altaLocal(Local local) {
		if(local.loadItem())
			return false;
		else
		{
			local.addItem();
			//TODO: Alta registre log.
			return true;
		}
	}
	
	public boolean modificacioLocal(Local local) {
		if(!local.loadItem())
			return false;
		else {
			local.update();
			//TODO: Alta registre log.
			return true;
		}
	}
	
	public boolean baixaLocal(Local local) {
		if(!local.loadItem())
			return false;
		else {
			local.delete();
			//TODO: Alta registre log.
			return true;
		}
	}
}

package server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pojo.CaracteristicaTipoLocal;

public class UtilsCaracteristicaTipoLocal {
	private Connection dbConnection;
	private Connection dbLogConnection;
	
	public UtilsCaracteristicaTipoLocal(Connection dbConnection, Connection dbLogConnection) {
		this.dbConnection = dbConnection;
		this.dbLogConnection = dbLogConnection;
	}
	public List<CaracteristicaTipoLocal> getListCaracteristicaTipoLocal(Long codiCaracteristicaTipoLocal, Long codiCaracteristica, Long codiTipoLocal) throws Exception{
		CaracteristicaTipoLocal caracteristicaTipoLocal = new CaracteristicaTipoLocal(dbConnection);
		try {
			return caracteristicaTipoLocal.getList(codiCaracteristicaTipoLocal, codiCaracteristica, codiTipoLocal);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pojo.Accessibilitat;

public class UtilsAccessibilitat {
	private Connection dbConnection;
	private Connection dbLogConnection;
	
	public UtilsAccessibilitat(Connection dbConnection, Connection dbLogConnection) {
		this.dbConnection = dbConnection;
		this.dbLogConnection = dbLogConnection;
	}
	public ResultSet getListAccessibilitat(Long codiAccessibilitat, Long codiLocal, Long codiCaracteristica, String verificat) throws SQLException{
		Accessibilitat accessibilitat = new Accessibilitat(dbConnection);
		//TODO tractament de dades i llista si es nescessari
		try {
			return accessibilitat.getList(codiAccessibilitat, codiLocal, codiCaracteristica, verificat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}

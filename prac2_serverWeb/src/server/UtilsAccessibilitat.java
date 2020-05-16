package server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pojo.Accessibilitat;

public class UtilsAccessibilitat {
Connection dbConnection;

	public List<Accessibilitat> getListAccessibilitat(Long codiAccessibilitat, Long codiLocal, Long codiCaracteristica, Long valor, String verificat) throws SQLException{
		Accessibilitat accessibilitat = new Accessibilitat(dbConnection);
		//TODO tractament de dades i llista si es nescessari
		try {
			return accessibilitat.getList(codiAccessibilitat, codiLocal, codiCaracteristica, valor, verificat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}

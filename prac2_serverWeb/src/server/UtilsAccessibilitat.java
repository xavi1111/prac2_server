package server;

import java.sql.Connection;
import java.util.List;

import pojo.Accessibilitat;

public class UtilsAccessibilitat {
Connection dbConnection;

	public List<Accessibilitat> getListAccessibilitat(Long codiAccessibilitat, Long codiLocal, Long codiCaracteristica, Long valor, String verificat){
		Accessibilitat accessibilitat = new Accessibilitat(dbConnection);
		//TODO tractament de dades i llista si es nescessari
		return accessibilitat.getList(codiAccessibilitat, codiLocal, codiCaracteristica, valor, verificat);
	}
}

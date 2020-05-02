package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Accessibilitat {
	
	private Long codiAccessibilitat;
	private Long codiLocal;
	private Long codiCaracteristica;
	private Long valor;
	private String verificat;
	
	private Connection dbConnection;
	
	public Accessibilitat(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean loadItem(Long codiAccessibilitat) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("select * from accessibilitat where accessibilitat.codiaccessibilitat = '" + codiAccessibilitat + "'");
			if(ors.next()) {
				fillObject(ors);
				ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
			//TODO a veure que fem
			try {
				ors.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	private void fillObject(ResultSet ors) {
		try {
			this.codiAccessibilitat = ors.getLong("codiaccessibilitat");
			this.codiLocal = ors.getLong("codilocal");
			this.codiCaracteristica = ors.getLong("codicaracteristica");
			this.valor = ors.getLong("valor");
			this.verificat = ors.getString("verificat");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}
	
	public Long getCodiAccessibilitat() {
		return codiAccessibilitat;
	}
	
	public void setCodiAccessibilitat(Long codiAccessibilitat) {
		this.codiAccessibilitat = codiAccessibilitat;
	}

	public Long getCodiLocal() {
		return codiLocal;
	}

	public void setCodiLocal(Long codiLocal) {
		this.codiLocal = codiLocal;
	}

	public Long getCodiCaracteristica() {
		return codiCaracteristica;
	}

	public void setCodiCaracteristica(Long codiCaracteristica) {
		this.codiCaracteristica = codiCaracteristica;
	}
	
	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getVerificat() {
		return verificat;
	}

	public void setVerificat(String verificat) {
		this.verificat = verificat;
	}	
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO accessibilitat(codilocal, codicaracteristica, valor, verificat) VALUES(?, ?, ?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setLong(1, getCodiLocal());
	        pst.setLong(2, getCodiCaracteristica());
	        pst.setLong(3, getValor());
	        pst.setString(4, getVerificat());
	        pst.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}

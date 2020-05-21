package pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessibilitatLocal {

	private Long codiAccessibilitat;
	private Long codiLocal;
	private Long codiCaracteristica;
	private Long valor;
	private String verificat;
	private String nomCaracteristicaCa;
	private String nomCaracteristicaEs;
	private String nomCaracteristicaEn;
	private Integer tipusCaracteristica;
	
	private Connection dbConnection;
	
	public AccessibilitatLocal(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public void fillObject(ResultSet ors) throws Exception {
		try {
			this.setCodiAccessibilitat(ors.getLong("codiaccessibilitat"));
			this.setCodiLocal(ors.getLong("codilocal"));
			this.setCodiCaracteristica(ors.getLong("codicaracteristica"));
			this.setValor(ors.getLong("valor"));
			this.setVerificat(ors.getString("verificat"));
			this.setNomCaracteristicaCa(ors.getString("nomcaracteristicaca"));
			this.setNomCaracteristicaEs(ors.getString("nomcaracteristicaes"));
			this.setNomCaracteristicaEn(ors.getString("nomcaracteristicaen"));
			this.setTipusCaracteristica(ors.getInt("tipusCaracteristica"));
		}catch(Exception e) {
			throw e;
		}
	}

	public Long getCodiLocal() {
		return codiLocal;
	}

	public void setCodiLocal(Long codiLocal) {
		this.codiLocal = codiLocal;
	}

	public Long getCodiAccessibilitat() {
		return codiAccessibilitat;
	}

	public void setCodiAccessibilitat(Long codiAccessibilitat) {
		this.codiAccessibilitat = codiAccessibilitat;
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

	public String getNomCaracteristicaCa() {
		return nomCaracteristicaCa;
	}

	public void setNomCaracteristicaCa(String nomCaracteristicaCa) {
		this.nomCaracteristicaCa = nomCaracteristicaCa;
	}

	public String getNomCaracteristicaEs() {
		return nomCaracteristicaEs;
	}

	public void setNomCaracteristicaEs(String nomCaracteristicaEs) {
		this.nomCaracteristicaEs = nomCaracteristicaEs;
	}

	public String getNomCaracteristicaEn() {
		return nomCaracteristicaEn;
	}

	public void setNomCaracteristicaEn(String nomCaracteristicaEn) {
		this.nomCaracteristicaEn = nomCaracteristicaEn;
	}

	public Integer getTipusCaracteristica() {
		return tipusCaracteristica;
	}

	public void setTipusCaracteristica(Integer tipusCaracteristica) {
		this.tipusCaracteristica = tipusCaracteristica;
	}
}

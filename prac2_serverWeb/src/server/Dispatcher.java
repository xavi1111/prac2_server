package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import com.sun.rowset.WebRowSetImpl;

import pojo.Accessibilitat;
import pojo.AccessibilitatLocal;
import pojo.CaracteristicaTipoLocal;
import pojo.Local;

@WebService
public class Dispatcher {
	Connection eAccessibleConnection = null;
	Connection incidenciaConnection = null;
	
	public Dispatcher() throws Exception{
		try	{
			InitialContext cxt = new InitialContext();
			if ( cxt != null )
			{
				DataSource dsEAccessible = (DataSource) cxt.lookup( "java:jboss/PostgreSQL/eAccessible");
				if ( dsEAccessible == null ) throw new Exception();
				else
				{
					eAccessibleConnection = dsEAccessible.getConnection();
				}
				DataSource dsIncidencia = (DataSource) cxt.lookup( "java:jboss/PostgreSQL/incidencia");
				if ( dsIncidencia == null ) throw new Exception();
				else
				{
					incidenciaConnection = dsEAccessible.getConnection();
				}
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Local altaLocal(Long codiTipoLocal, Long codiCarrer, String nomCarrer, String nomVia, Long numero, String nomLocal, String observacions, String verificat) throws Exception{
		Local newLocal = new Local(eAccessibleConnection);
		UtilsLocal utilsLocal = new UtilsLocal(eAccessibleConnection, incidenciaConnection);

		try {
			newLocal.setCodiTipoLocal(codiTipoLocal);
			newLocal.setCodiCarrer(codiCarrer);
			newLocal.setNomCarrer(nomCarrer);
			newLocal.setNomVia(nomVia);
			newLocal.setNumero(numero);
			newLocal.setNomLocal(nomLocal);
			newLocal.setObservacions(observacions);
			newLocal.setVerificat(verificat);
			Local resultLocal = utilsLocal.altaLocal(newLocal);
			if(resultLocal != null) {
				return resultLocal;
			} else {
				return resultLocal;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@WebMethod
	public boolean baixaLocal(Long codiLocal)throws Exception{
		Local newLocal = new Local(eAccessibleConnection);
		UtilsLocal utilsLocal = new UtilsLocal(eAccessibleConnection, incidenciaConnection);
		try {
			newLocal.setCodiLocal(codiLocal);
			if(utilsLocal.baixaLocal(newLocal)) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Local updateLocal(Long codiLocal, Long codiTipoLocal, Long codiCarrer, String nomCarrer, String nomVia, Long numero, String nomLocal, String observacions, String verificat)throws Exception{
		Local modifLocal = new Local(eAccessibleConnection);
		UtilsLocal utilsLocal = new UtilsLocal(eAccessibleConnection, incidenciaConnection);
		try {
			modifLocal.setCodiLocal(codiLocal);
			modifLocal.loadItem();
			modifLocal.setCodiTipoLocal(codiTipoLocal);
			modifLocal.setCodiCarrer(codiCarrer);
			modifLocal.setNomCarrer(nomCarrer);
			modifLocal.setNomVia(nomVia);
			modifLocal.setNumero(numero);
			modifLocal.setNomLocal(nomLocal);
			modifLocal.setObservacions(observacions);
			modifLocal.setVerificat(verificat);
			Local resultLocal = utilsLocal.modificacioLocal(modifLocal);
			if(resultLocal != null) {
				return resultLocal;
			} else {
				return resultLocal;
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public ArrayList<Local> getListLocal(Long codiLocal, Long codiTipoLocal, Long codiCarrer, String nomCarrer, String nomVia, Long numero, String nomLocal, String observacions, String verificat, Long codicaracteristica)throws Exception{
		UtilsLocal utilsLocal = new UtilsLocal(eAccessibleConnection, incidenciaConnection);
		try {
			ArrayList<Local> locals = utilsLocal.getListLocals(codiLocal, codiTipoLocal, codiCarrer, nomCarrer, nomVia, numero, nomLocal, observacions, verificat, codicaracteristica);
			return locals;
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Local verificarLocal(Long codiLocal)throws Exception{
		Local local = new Local(eAccessibleConnection);
		UtilsLocal utilsLocal = new UtilsLocal(eAccessibleConnection, incidenciaConnection);
		try {
			local.setCodiLocal(codiLocal);
			local.loadItem();
			local.setVerificat("S");
			Local resultLocal = utilsLocal.modificacioLocal(local);
			if(resultLocal != null) {
				return resultLocal;
			} else {
				return resultLocal;
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Accessibilitat crearAccessibilitat(Long codiLocal, Long codiCaracteristica, Long valor, String verificat)throws Exception{
		Accessibilitat accessibilitat = new Accessibilitat(eAccessibleConnection);
		try {
			accessibilitat.setCodiLocal(codiLocal);
			accessibilitat.setCodiCaracteristica(codiCaracteristica);
			accessibilitat.setValor(valor);
			accessibilitat.setVerificat(verificat);
			accessibilitat.addItem();
			return accessibilitat;
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public ArrayList<AccessibilitatLocal> getListAccessibilitatLocal(Long codiAccessibilitat, Long codiLocal, Long codiCaracteristica,String verificat)throws Exception{
		UtilsAccessibilitat utilsAccessibilitat = new UtilsAccessibilitat(eAccessibleConnection, incidenciaConnection);
		ArrayList<AccessibilitatLocal> accessibilitatLocal= new ArrayList<AccessibilitatLocal>(); 
		ResultSet ors = null;
		
		try {
			ors = utilsAccessibilitat.getListAccessibilitat(codiAccessibilitat, codiLocal, codiCaracteristica, verificat);
			while(ors.next()) {
				AccessibilitatLocal accessibilitatLocalAux = new AccessibilitatLocal(eAccessibleConnection);
				accessibilitatLocalAux.fillObject(ors);
				accessibilitatLocal.add(accessibilitatLocalAux);
			}
			return accessibilitatLocal;
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public List<CaracteristicaTipoLocal> getListCaracteristicaTipoLocal(Long codiCaracteristicaTipoLocal, Long codiCaracteristica, Long codiTipoLocal)throws Exception{
		UtilsCaracteristicaTipoLocal utilsCaracteristicaTipoLocal = new UtilsCaracteristicaTipoLocal(eAccessibleConnection, incidenciaConnection);
		try {
			return utilsCaracteristicaTipoLocal.getListCaracteristicaTipoLocal(codiCaracteristicaTipoLocal, codiCaracteristica, codiTipoLocal);			
		}catch(Exception e) {
			throw e;
		}
	}
}

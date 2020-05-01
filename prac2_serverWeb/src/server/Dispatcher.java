package server;

import java.sql.Connection;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	public Local altaLocal() throws Exception{
		Local newLocal = null;
		try {
			
			return newLocal;
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Local baixaLocal()throws Exception{
		Local newLocal = null;
		try {
			
			return newLocal;
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Local updateLocal()throws Exception{
		Local newLocal = null;
		try {
			
			return newLocal;
		}catch(Exception e) {
			throw e;
		}
	}
	
	@WebMethod
	public Local verificarLocal()throws Exception{
		Local newLocal = null;
		try {
			return newLocal;
		}catch(Exception e) {
			throw e;
		}
	}
}

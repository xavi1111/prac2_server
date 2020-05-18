package pojo;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Local {
	private Long codiLocal=0L;
	private Long codiTipoLocal=0L;
	private Long codiCarrer=0L;
	private String nomCarrer="";
	private String nomVia="";
	private Long numero=0L;
	private String nomLocal="";
	private String observacions="";
	private String verificat="";
	private Connection dbConnection;
	

	public Local (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Boolean loadItem(Long codiTipoLocal, String nomLocal) {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("SELECT * FROM eaccessible.local WHERE local.coditipolocal = '" + codiTipoLocal + "'"
							+ "AND local.nomlocal = '" + nomLocal + "'");
			if(ors.next()) {
				fillObject(ors);
				ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
			//TODO a veure que fem
			try {
				if (ors!=null)
					ors.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	public Boolean loadItem() {
		ResultSet ors = null;
		try {
			Statement statement = dbConnection.createStatement();
			ors = statement.executeQuery("SELECT * FROM eaccessible.local WHERE local.codilocal = '" + codiLocal + "'");
			if(ors.next()) {
				fillObject(ors);
				ors.close();
				return true;
			}else
				return false;
		}catch(Exception e) {
			//TODO a veure que fem
			try {
				if (ors!=null)
					ors.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	private void fillObject(ResultSet ors) {
		try {
			this.codiLocal = ors.getLong(1);
			this.codiTipoLocal = ors.getLong("coditipoLocal");
			this.codiCarrer = ors.getLong("codicarrer");
			this.nomCarrer = ors.getString("nomcarrer");
			this.nomVia = ors.getString("nomvia");
			this.numero = ors.getLong("numero");
			this.nomLocal = ors.getString("nomlocal");
			this.observacions = ors.getString("observacions");
			this.verificat = ors.getString("verificat");
		}catch(Exception e) {
			//TODO a veure que fem
		}
	}


	public Long getCodiLocal() {
		return codiLocal;
	}

	public void setCodiLocal(Long codiLocal) {
		this.codiLocal = codiLocal;
	}

	public Long getCodiTipoLocal() {
		return codiTipoLocal;
	}

	public void setCodiTipoLocal(Long codiTipoLocal) {
		this.codiTipoLocal = codiTipoLocal;
	}
	
	public Long getCodiCarrer() {
		return codiCarrer;
	}

	public void setCodiCarrer(Long codiCarrer) {
		this.codiCarrer = codiCarrer;
	}

	public String getNomCarrer() {
		return nomCarrer;
	}

	public void setNomCarrer(String nomCarrer) {
		this.nomCarrer = nomCarrer;
	}

	public String getNomVia() {
		return nomVia;
	}

	public void setNomVia(String nomVia) {
		this.nomVia = nomVia;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNomLocal() {
		return nomLocal;
	}

	public void setNomLocal(String nomLocal) {
		this.nomLocal = nomLocal;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public String getVerificat() {
		return verificat;
	}

	public void setVerificat(String verificat) {
		this.verificat = verificat;
	}
	
	public boolean addItem() {
		try {
			String query = "INSERT INTO eaccessible.local(coditipolocal, codicarrer, nomcarrer, nomvia, numero, nomlocal, observacions, verificat) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
	        pst.setLong(1, getCodiTipoLocal());
	        pst.setLong(2, getCodiCarrer());
	        pst.setString(3, getNomCarrer());
	        pst.setString(4, getNomVia());
	        pst.setLong(5, getNumero());
	        pst.setString(6, getNomLocal());
	        pst.setString(7, getObservacions());
	        pst.setString(8, getVerificat());
	        pst.executeUpdate();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update() {
		try {
			String query = "UPDATE eaccessible.local  "
					+ "SET codicarrer=?, nomcarrer=?, nomvia=?, numero=?, observacions=?, verificat=?"
					+ "WHERE codilocal=?";
			PreparedStatement pst = dbConnection.prepareStatement(query);
		    pst.setLong(1, getCodiCarrer());
		    pst.setString(2, getNomCarrer());
	        pst.setString(3, getNomVia());
	        pst.setLong(4, getNumero());
	        pst.setString(5, getObservacions());
	        pst.setString(6, getVerificat());
	        pst.setLong(7, getCodiLocal());
	        pst.execute();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete() {
		try {
			String query = "DELETE from eaccessible.local WHERE local.codilocal=?";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setLong(1, getCodiLocal());
			pst.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<Local> getList(Long codiLocal, Long codiTipoLocal, Long codiCarrer, String nomCarrer,
			String nomVia, Long numero, String nomLocal, String observacions, String verificat, Long codiCaracteristica) throws SQLException {
		ResultSet ors = null;
		ArrayList<Local> locals = new ArrayList<Local>();
		String query = "select * from eaccessible.get_locals(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = dbConnection.prepareStatement(query);
		pst.setLong(1, codiLocal);
		pst.setLong(2, codiTipoLocal);
		pst.setLong(3, codiCarrer);
	    pst.setString(4, nomCarrer);
        pst.setString(5, nomVia);
        pst.setLong(6, numero);
        pst.setString(7, nomLocal);
        pst.setString(8, observacions);
        pst.setString(9, verificat);
        pst.setLong(10, codiCaracteristica);
        
        ors = pst.executeQuery();
        while(ors.next()) {
        	Local localAux = new Local(dbConnection);
        	localAux.fillObject(ors);
        	locals.add(localAux);
        }
		return locals;
	}
}

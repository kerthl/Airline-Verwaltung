package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import bll.*;

public class Database {

	private static String cN = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@10.0.6.111:1521:ora11g";
	//212.152.179.117
	//10.0.6.111
	private static String benutzer = "d4a29";
	private static String passwort = "d4a";

	private static Database db = null;
	private Connection con = null;

	private LinkedList<Airline> listAirline = new LinkedList<Airline>();
	private LinkedList<Angebot> listAngebote = new LinkedList<Angebot>();
	private LinkedList<Flug> listFluege = new LinkedList<Flug>();
	private LinkedList<Flughafen> listFlughaefen = new LinkedList<Flughafen>();
	private LinkedList<Flugzeug> listFlugzeuge = new LinkedList<Flugzeug>();
	private LinkedList<Passagier> listPassagiere = new LinkedList<Passagier>();
	private LinkedList<Pilot> listPiloten = new LinkedList<Pilot>();

	public static Database getInstance() {
		if (db == null) {
			try {
				db = new Database();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;
	}

	private Database() throws SQLException, ClassNotFoundException {
		Class.forName(cN);
		con = DriverManager.getConnection(url, benutzer, passwort);
	}
	
	public LinkedList<Pilot> getPiloten() {
		Statement stmt = null;
		this.listPiloten.clear();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from pilot");
			while (rs.next()) {
				Airline a = this.getAirlineByID(rs.getInt(2));
				this.listPiloten.add(new Pilot(rs.getInt(1), a, rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.listPiloten;
	}

	public LinkedList<Flug> getFluege() {
		Statement stmt = null;
		this.listFluege.clear();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from flug");
			while (rs.next()) {
				String angebotID = rs.getString(1);
				Angebot a = this.getAngebotByID(angebotID);
				Pilot p1 = this.getPilotById(rs.getInt(3));
				Pilot p2 = this.getPilotById(rs.getInt(4));
				Flugzeug f = this.getFlugzeugByID(rs.getInt(5));
				String s  = rs.getString(2);
				listFluege.add(new Flug(a, s, p1, p2, f));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listFluege;
	}
	
	public boolean updateFlug(Flug f) {
		boolean result = false;
		String updateString = null;
		java.sql.PreparedStatement pstmt = null;
		updateString = "UPDATE flug SET idCpt = ?, idFO = ?, idFlugzeug = ?  WHERE idFlugAngebot = ? AND datum = ?";

		try {
			pstmt = con.prepareStatement(updateString);
			pstmt.setInt(1, f.getCaptain().getId());
			pstmt.setInt(2, f.getFirstOfficer().getId());
			pstmt.setInt(3, f.getFlugzeug().getId());
			pstmt.setString(4, f.getAngebot().getFlugNummer());
			pstmt.setString(5, f.getDatum());
			result = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateFlugzeug(Flugzeug f) {
		boolean result = false;
		String updateString = null;
		java.sql.PreparedStatement pstmt = null;
		updateString = "UPDATE flugzeug SET idAirline = ?, bezeichnung = ?, maxSitze = ?, baujahr = ?, kennzeichen = ?, anzahlReihen = ?, anzahlSitzeProR = ? WHERE id = ? ";

		try {
			pstmt = con.prepareStatement(updateString);
			pstmt.setInt(1, f.getAirline().getId());
			pstmt.setString(2, f.getBezeichnung());
			pstmt.setInt(3, f.getMaxSitze());
			pstmt.setInt(4, f.getBaujahr());
			pstmt.setString(5, f.getKennzeichen());
			pstmt.setInt(6, f.getAnzahlReihen());
			pstmt.setInt(7, f.getAnzahlSitzeProReihe());
			pstmt.setInt(8, f.getId());
			
			result = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean addFlug(Flug f) {
		java.sql.PreparedStatement pstmt = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement("INSERT INTO flug VALUES (?,?,?,?,?)");
			pstmt.setString(1, f.getAngebot().getFlugNummer());
			pstmt.setString(2, f.getDatum());
			pstmt.setInt(3, f.getCaptain().getId());
			pstmt.setInt(4, f.getFirstOfficer().getId());
			pstmt.setInt(5, f.getFlugzeug().getId());
			result = pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
	
	public LinkedList<Flugzeug> getFlugzeuge() {
		Statement stmt = null;
		this.listFlugzeuge.clear();
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from flugzeug");
			while (rs.next()) {
				int id = rs.getInt("id");
				Airline airline = this.getAirlineById(rs.getInt("idAirline"));
				String bezeichnung = rs.getString("bezeichnung");
				int baujahr = rs.getInt("baujahr");
				int maxSitze = rs.getInt("maxSitze");
				String kennzeichen = rs.getString("kennzeichen");
				int anzahlReihen = rs.getInt("anzahlReihen");
				int anzSPR = rs.getInt("anzahlSitzeProR");
				
				listFlugzeuge.add(new Flugzeug(id, airline, bezeichnung, maxSitze, baujahr, kennzeichen, anzahlReihen, anzSPR));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listFlugzeuge;
	}

	public Airline getAirlineById(int idA) {
		java.sql.PreparedStatement pstmt = null;
		Airline result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from airline WHERE id = ?");
			pstmt.setInt(1, idA);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				Flughafen heimat = getFlughafenById(rs.getInt("idHeimatFH"));
				String bezeichnung = rs.getString("bezeichnung");		
				
				result = new Airline(id,heimat, bezeichnung);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}
	
	public LinkedList<Airline> getAirlines() {
		Statement stmt = null;
		this.listAirline.clear();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from airline");
			while (rs.next()) {

				int id = rs.getInt("id");
				Flughafen heimat = getFlughafenById(rs.getInt("idHeimatFH"));
				String bezeichnung = rs.getString("bezeichnung");		
	
				listAirline.add(new Airline(id,heimat, bezeichnung));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listAirline;
	}

	
	public Flughafen getFlughafenById(int idFH) {
		java.sql.PreparedStatement pstmt = null;
		Flughafen result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from flughafen WHERE id = ?");
			pstmt.setInt(1, idFH);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String bezeichnung = rs.getString("bezeichnung");
				String code = rs.getString("code");
				String ort = rs.getString("ort");
				
				result = new Flughafen(id, bezeichnung, code, ort);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}
	
	public Flug getFlugByID(String id) {
		java.sql.PreparedStatement pstmt = null;
		Flug result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from flug WHERE id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String angebotID = rs.getString(1);
				Angebot a = this.getAngebotByID(angebotID);
				Pilot p1 = this.getPilotById(rs.getInt(3));
				Pilot p2 = this.getPilotById(rs.getInt(4));
				Flugzeug f = this.getFlugzeugByID(rs.getInt(5));
				String s = rs.getString(2);
				result = new Flug(a, s, p1, p2, f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	public LinkedList<Angebot> getAngebote() {
		Statement stmt = null;
		this.listAngebote.clear();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from angebot");
			while (rs.next()) {

				Airline airline = this.getAirlineByID(rs.getInt(2));
				Flughafen f1 = this.getFlughafenByID(rs.getInt(3));
				Flughafen f2 = this.getFlughafenByID(rs.getInt(4));
				listAngebote.add(new Angebot(rs.getString(1), airline, f1, f2, rs.getString(5), rs.getString(6)));

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listAngebote;
	}

	private Angebot getAngebotByID(String angebotID) {
		java.sql.PreparedStatement pstmt = null;
		Angebot result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from Angebot WHERE flugNr = ?");
			pstmt.setString(1, angebotID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Airline airline = this.getAirlineByID(rs.getInt(2));
				Flughafen f1 = this.getFlughafenByID(rs.getInt(3));
				Flughafen f2 = this.getFlughafenByID(rs.getInt(4));
				result = new Angebot(rs.getString(1), airline, f1, f2, rs.getString(5), rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	public Flugzeug getFlugzeugByID(int fzID) {
		java.sql.PreparedStatement pstmt = null;
		Flugzeug result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from flugzeug WHERE id = ?");
			pstmt.setInt(1, fzID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				Airline airline = this.getAirlineById(rs.getInt("idAirline"));
				String bezeichnung = rs.getString("bezeichnung");
				int baujahr = rs.getInt("baujahr");
				int maxSitze = rs.getInt("maxSitze");
				String kennzeichen = rs.getString("kennzeichen");
				int anzahlReihen = rs.getInt("anzahlReihen");
				int anzSPR = rs.getInt("anzahlSitzeProR");	
				
				result = new Flugzeug(id, airline, bezeichnung, maxSitze, baujahr, kennzeichen, anzahlReihen, anzSPR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}
	
	public void setFlugzeug(Flugzeug flugzeug) throws Exception {
		try {
		String query = "insert into flugzeug values (sq_idFZ.nextVal, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = this.con.prepareStatement(query);
        preparedStmt.setInt(1, flugzeug.getAirline().getId());
        preparedStmt.setString(2, flugzeug.getBezeichnung());
        preparedStmt.setInt(3, flugzeug.getMaxSitze());
        preparedStmt.setInt(4, flugzeug.getBaujahr());
        preparedStmt.setString(5, flugzeug.getKennzeichen());
        preparedStmt.setInt(6, flugzeug.getAnzahlReihen());
        preparedStmt.setInt(7, flugzeug.getAnzahlSitzeProReihe());
        
        preparedStmt.execute();
		} catch (Exception ex) {
			System.err.print("------------ error: " + ex);
			throw new Exception("error when inserting flugzeug in db: " + ex.getMessage());
		}
	}
	

	private Pilot getPilotById(int pilotID) {
		java.sql.PreparedStatement pstmt = null;
		Pilot result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from pilot WHERE id = ?");
			pstmt.setInt(1, pilotID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Airline a = this.getAirlineByID(rs.getInt(2));
				result = new Pilot(rs.getInt(1), a, rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	private Airline getAirlineByID(int airlioneId) {
		java.sql.PreparedStatement pstmt = null;
		Airline result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from airline WHERE id = ?");
			pstmt.setInt(1, airlioneId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int flughafenID = rs.getInt(1);
				Flughafen f = this.getFlughafenByID(flughafenID);
				result = new Airline(f, rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	private Flughafen getFlughafenByID(int flughafenID) {
		java.sql.PreparedStatement pstmt = null;
		Flughafen result = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("Select * from flughafen WHERE id = ?");
			pstmt.setInt(1, flughafenID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Flughafen(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	public void deleteFlugzeug(int id) throws Exception {
		try {
		String query = "delete from flugzeug where Id = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);

        preparedStmt.execute();
		} catch (Exception ex) {
			System.err.print("------------ error: " + ex);
			throw new Exception("error when deleting flugzeug from db: " + ex.getMessage());
		}
	}
	
	private int getNextAirlineID() {
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {

			stmt = con.createStatement();
			rs = stmt.executeQuery("Select MAX(id) from airline");
			if (rs.next())
				result = rs.getInt(1) + 1;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void setAngebot(Angebot Angebot) throws Exception {
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement("INSERT INTO angebot VALUES (?,?,?,?,?,?)");
			pstmt.setString(1, Angebot.getFlugNummer());
			pstmt.setInt(2, Angebot.getAirline().getId());
			pstmt.setInt(3, Angebot.getFlughafenAb().getId());
			pstmt.setInt(4, Angebot.getFlughafenAn().getId());
			pstmt.setString(5, Angebot.getAbflugszeit());
			pstmt.setString(6, Angebot.getAnkunftszeit());
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
		} 
	}

	public void updateAngebot(Angebot a) {
		String updateString = null;
		updateString = "UPDATE Angebot SET flughafenAb = ?, flughafenAn = ?, ankunftszeit = ?, abflugszeit = ? WHERE flugNummer = ?";

		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(updateString);
			pstmt.setString(1, a.getFlughafenAb().toString());
			pstmt.setString(2, a.getFlughafenAn().toString());
			pstmt.setString(3, a.getAbflugszeit());
			pstmt.setString(4, a.getAnkunftszeit());
			pstmt.setString(5, a.getFlugNummer());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAngebot(Integer _id) {
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement("DELETE FROM Angebot WHERE flugNummer=?");
			pstmt.setInt(1, _id);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}

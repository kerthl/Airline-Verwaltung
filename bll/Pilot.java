package bll;

public class Pilot {
	private int id;
	private String vorname;
	private String nachname;
	private String lizenz;

	public Pilot(int id, String vorname, String nachname, String lizenz) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.lizenz = lizenz;
	}

	public Pilot(String vorname, String nachname, String lizenz) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.lizenz = lizenz;
	}

	public Pilot() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getLizenz() {
		return lizenz;
	}

	public void setLizenz(String lizenz) {
		this.lizenz = lizenz;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", lizenz=" + lizenz + "]";
	}

}

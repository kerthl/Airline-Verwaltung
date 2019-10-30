package bll;

import java.util.Date;

public class Passagier {
	private int id;
	private String vorname;
	private String nachname;
	private Date gebDatum;
	private String strasse;
	private String ort;
	private int plz;

	public Passagier(int id, String vorname, String nachname, Date gebDatum, String strasse, String ort, int plz) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.gebDatum = gebDatum;
		this.strasse = strasse;
		this.ort = ort;
		this.plz = plz;
	}

	public Passagier() {
		
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

	public Date getGebDatum() {
		return gebDatum;
	}

	public void setGebDatum(Date gebDatum) {
		this.gebDatum = gebDatum;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	@Override
	public String toString() {
		return "Passagier [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", gebDatum=" + gebDatum
				+ ", strasse=" + strasse + ", ort=" + ort + ", plz=" + plz + "]";
	}

}

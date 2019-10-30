package bll;

public class Flugzeug {
	private int id;
	private Airline airline;
	private String bezeichnung;
	private int maxSitze;
	private int baujahr;
	private String kennzeichen;
	private int anzahlReihen;
	private int anzahlSitzeProReihe;

	public Flugzeug() {
	
	}

	public Flugzeug(int id, Airline airline, String bezeichnung, int maxSitze, int baujahr, String kennzeichen,
			int anzahlReihen, int anzahlSitzeProReihe) {
		super();
		this.id = id;
		this.airline = airline;
		this.bezeichnung = bezeichnung;
		this.maxSitze = maxSitze;
		this.baujahr = baujahr;
		this.kennzeichen = kennzeichen;
		this.anzahlReihen = anzahlReihen;
		this.anzahlSitzeProReihe = anzahlSitzeProReihe;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(int baujahr) {
		this.baujahr = baujahr;
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public int getAnzahlReihen() {
		return anzahlReihen;
	}

	public void setAnzahlReihen(int anzahlReihen) {
		this.anzahlReihen = anzahlReihen;
	}

	public int getAnzahlSitzeProReihe() {
		return anzahlSitzeProReihe;
	}

	public void setAnzahlSitzeProReihe(int anzahlSitzeProReihe) {
		this.anzahlSitzeProReihe = anzahlSitzeProReihe;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public int getMaxSitze() {
		return maxSitze;
	}

	public void setMaxSitze(int maxSitze) {
		this.maxSitze = maxSitze;
	}

	@Override
	public String toString() {
		return "Flugzeug [id=" + id + ", airline=" + airline + ", bezeichnung=" + bezeichnung + ", baujahr=" + baujahr
				+ ", maxSitze=" + maxSitze + ", kennzeichen=" + kennzeichen + ", anzahlReihen=" + anzahlReihen
				+ ", anzahlSitzeProReihe=" + anzahlSitzeProReihe + "]";
	}
}

package bll;

public class Flugzeug {
	private int id;
	private String bezeichnung;
	private int baujahr;
	private String kennzeichen;
	private int anzahlReihen;
	private int anzahlSitzeProReihe;

	public Flugzeug(int id, String bezeichnung, int baujahr, String kennzeichen, int anzahlReihen,
			int anzahlSitzeProReihe) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.baujahr = baujahr;
		this.kennzeichen = kennzeichen;
		this.anzahlReihen = anzahlReihen;
		this.anzahlSitzeProReihe = anzahlSitzeProReihe;
	}

	public Flugzeug() {
		super();
	}

	public Flugzeug(String bezeichnung, int baujahr, String kennzeichen, int anzahlReihen, int anzahlSitzeProReihe) {
		super();
		this.bezeichnung = bezeichnung;
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

	@Override
	public String toString() {
		return "Flugzeug [id=" + id + ", bezeichnung=" + bezeichnung + ", baujahr=" + baujahr + ", kennzeichen="
				+ kennzeichen + ", anzahlReihen=" + anzahlReihen + ", anzahlSitzeProReihe=" + anzahlSitzeProReihe + "]";
	}

}

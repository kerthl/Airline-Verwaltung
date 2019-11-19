package bll;

public class Flug {
	private Angebot angebot;
	private String datum;
	private Pilot captain;
	private Pilot firstOfficer;
	private Flugzeug flugzeug;

	public Flug(Angebot angebot, String datum, Pilot captain, Pilot firstOfficer, Flugzeug flugzeug) {
		super();
		this.angebot = angebot;
		this.datum = datum;
		this.captain = captain;
		this.firstOfficer = firstOfficer;
		this.flugzeug = flugzeug;
	}

	public Flug() {

	}

	public String getAngebotInfo() {
		return "Nummer: " + this.angebot.getFlugNummer() + " " + this.getAngebot().getFlughafenAb().getBezeichnung()
				+ " => " + this.getAngebot().getFlughafenAn().getBezeichnung();

	}

	public String getCptInfo() {
		return this.captain.getVorname() + " " + this.captain.getNachname() + " Lizenz:" + this.captain.getLizenz();
	}

	public String getFoInfo() {
		return this.firstOfficer.getVorname() + " " + this.firstOfficer.getNachname() + " Lizenz:"
				+ this.firstOfficer.getLizenz();
	}

	public Angebot getAngebot() {
		return angebot;
	}

	public String getFlugzeugInfo() {
		return this.flugzeug.getBezeichnung() + " " + this.flugzeug.getKennzeichen() + " "
				+ this.flugzeug.getMaxSitze();
	}

	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Pilot getCaptain() {
		return captain;
	}

	public void setCaptain(Pilot captain) {
		this.captain = captain;
	}

	public Pilot getFirstOfficer() {
		return firstOfficer;
	}

	public void setFirstOfficer(Pilot firstOfficer) {
		this.firstOfficer = firstOfficer;
	}

	public Flugzeug getFlugzeug() {
		return flugzeug;
	}

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	@Override
	public String toString() {
		return "Flug [angebot=" + angebot + ", datum=" + datum + ", captain=" + captain + ", firstOfficer="
				+ firstOfficer + ", flugzeug=" + flugzeug + "]";
	}
}

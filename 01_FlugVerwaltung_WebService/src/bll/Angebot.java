package bll;

public class Angebot {
	private String flugNummer;
	private Airline airline;
	private Flughafen flughafenAb;
	private Flughafen flughafenAn;
	private String zeiten;

	public Angebot() {
		super();
	}

	public Angebot(String id, Airline airline, Flughafen flughafenAb, Flughafen flughafenAn, String zeiten) {
		super();
		this.flugNummer = id;
		this.airline = airline;
		this.flughafenAb = flughafenAb;
		this.flughafenAn = flughafenAn;
		this.zeiten = zeiten;
	}

	public Angebot(Airline airline, Flughafen flughafenAb, Flughafen flughafenAn, String zeiten) {
		super();
		this.airline = airline;
		this.flughafenAb = flughafenAb;
		this.flughafenAn = flughafenAn;
		this.zeiten = zeiten;
	}

	public String getId() {
		return flugNummer;
	}

	public void setId(String id) {
		this.flugNummer = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Flughafen getFlughafenAb() {
		return flughafenAb;
	}

	public void setFlughafenAb(Flughafen flughafenAb) {
		this.flughafenAb = flughafenAb;
	}

	public Flughafen getFlughafenAn() {
		return flughafenAn;
	}

	public void setFlughafenAn(Flughafen flughafenAn) {
		this.flughafenAn = flughafenAn;
	}

	public String getZeiten() {
		return zeiten;
	}

	public void setZeiten(String zeiten) {
		this.zeiten = zeiten;
	}
	
	public String getFlugNummer() {
		return flugNummer;
	}

	public void setFlugNummer(String flugNummer) {
		this.flugNummer = flugNummer;
	}

	@Override
	public String toString() {
		return flugNummer  + flughafenAb + "- "
				+ flughafenAn + ", Zeiten=" + zeiten;
	}

}

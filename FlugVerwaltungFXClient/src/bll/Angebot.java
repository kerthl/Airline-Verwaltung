package bll;

public class Angebot {
	private String flugNummer;
	private Airline airline;
	private Flughafen flughafenAb;
	private Flughafen flughafenAn;
	private String abflugsZeit;
	private String ankunftsZeit;
	
	public Angebot(String flugNummer, Airline airline, Flughafen flughafenAb, Flughafen flughafenAn, String abflugsZeit,
			String ankunftsZeit) {
		super();
		this.flugNummer = flugNummer;
		this.airline = airline;
		this.flughafenAb = flughafenAb;
		this.flughafenAn = flughafenAn;
		this.abflugsZeit = abflugsZeit;
		this.ankunftsZeit = ankunftsZeit;
	}

	public Angebot() {
		
	}
	
	public String getFlugNummer() {
		return flugNummer;
	}

	public void setFlugNummer(String flugNummer) {
		this.flugNummer = flugNummer;
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

	public String getAbflugsZeit() {
		return abflugsZeit;
	}

	public void setAbflugsZeit(String abflugsZeit) {
		this.abflugsZeit = abflugsZeit;
	}

	public String getAnkunftsZeit() {
		return ankunftsZeit;
	}

	public void setAnkunftsZeit(String ankunftsZeit) {
		this.ankunftsZeit = ankunftsZeit;
	}

	@Override
	public String toString() {
		return "Angebot [flugNummer=" + flugNummer + ", airline=" + airline + ", flughafenAb=" + flughafenAb
				+ ", flughafenAn=" + flughafenAn + ", abflugsZeit=" + abflugsZeit + ", ankunftsZeit=" + ankunftsZeit
				+ "]";
	}
}

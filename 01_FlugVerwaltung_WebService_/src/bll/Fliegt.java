package bll;

public class Fliegt {
	private Passagier passagier;
	private String flugNr;
	private String datum;
	private String Sitzplatz;
	private int preis;
	
	public Fliegt(Passagier passagier, String flugNr, String datum, String sitzplatz, int preis) {
		super();
		this.passagier = passagier;
		this.flugNr = flugNr;
		this.datum = datum;
		Sitzplatz = sitzplatz;
		this.preis = preis;
	}

	public Passagier getPassagier() {
		return passagier;
	}

	public void setPassagier(Passagier passagier) {
		this.passagier = passagier;
	}

	public String getFlugNr() {
		return flugNr;
	}

	public void setFlugNr(String flugNr) {
		this.flugNr = flugNr;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getSitzplatz() {
		return Sitzplatz;
	}

	public void setSitzplatz(String sitzplatz) {
		Sitzplatz = sitzplatz;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	
	

}

package bll;

import java.util.Date;

public class Flug {
	private int idFlugangebot;
	private Date datum;
	private Pilot captain;
	private Flugzeug flugzeug;

	
	public Flug(int idFlugangebot, Date datum, Pilot idCaptain, Flugzeug idFlugzeug) {
		super();
		this.idFlugangebot = idFlugangebot;
		this.datum = datum;
		this.captain = idCaptain;
		this.flugzeug = idFlugzeug;
	}

	public int getIdFlugangebot() {
		return idFlugangebot;
	}

	public void setIdFlugangebot(int idFlugangebot) {
		this.idFlugangebot = idFlugangebot;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Pilot getIdCaptain() {
		return captain;
	}

	public void setIdCaptain(Pilot idCaptain) {
		this.captain = idCaptain;
	}

	public Flugzeug getIdFlugzeug() {
		return flugzeug;
	}

	public void setIdFlugzeug(Flugzeug idFlugzeug) {
		this.flugzeug = idFlugzeug;
	}

	
	@Override
	public String toString() {
		return "Flug [idFlugangebot=" + idFlugangebot + ", datum=" + datum + ", idCaptain=" + captain
				+ ", idFlugzeug=" + flugzeug + ", flugnummer=";
	}

}

package bll;

import java.util.Date;

public class Flug {
	private Angebot flugangbeot;
	private Date datum;
	private Pilot captain;
	private Pilot firstOFFICER;
	private Flugzeug flugzeug;

	public Flug() {
		super();
	}

	public Flug(Angebot flugangbeot, Date datum, Pilot captain, Pilot firstOFFICER, Flugzeug flugzeug) {
		super();
		this.flugangbeot = flugangbeot;
		this.datum = datum;
		this.captain = captain;
		this.firstOFFICER = firstOFFICER;
		this.flugzeug = flugzeug;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Pilot getCaptain() {
		return captain;
	}

	public void setCaptain(Pilot captain) {
		this.captain = captain;
	}

	public Pilot getFirstOFFICER() {
		return firstOFFICER;
	}

	public void setFirstOFFICER(Pilot firstOFFICER) {
		this.firstOFFICER = firstOFFICER;
	}

	public Angebot getFlugangbeot() {
		return flugangbeot;
	}

	public void setFlugangbeot(Angebot flugangbeot) {
		this.flugangbeot = flugangbeot;
	}

	public Flugzeug getFlugzeug() {
		return flugzeug;
	}

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	@Override
	public String toString() {
		return "Flug [flugangbeot=" + flugangbeot + ", datum=" + datum + ", captain=" + captain + ", firstOFFICER="
				+ firstOFFICER + ", flugzeug=" + flugzeug + "]";
	}

}

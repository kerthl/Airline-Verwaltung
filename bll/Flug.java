package bll;

import java.util.Date;

public class Flug {
	private int idFlugangebot;
	private Date datum;
	private int idCaptain;
	private int idFlugzeug;
	private int flugnummer;

	public Flug(int idFlugangebot, Date datum, int idCaptain, int idFlugzeug, int flugnummer) {
		super();
		this.idFlugangebot = idFlugangebot;
		this.datum = datum;
		this.idCaptain = idCaptain;
		this.idFlugzeug = idFlugzeug;
		this.flugnummer = flugnummer;
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

	public int getIdCaptain() {
		return idCaptain;
	}

	public void setIdCaptain(int idCaptain) {
		this.idCaptain = idCaptain;
	}

	public int getIdFlugzeug() {
		return idFlugzeug;
	}

	public void setIdFlugzeug(int idFlugzeug) {
		this.idFlugzeug = idFlugzeug;
	}

	public int getFlugnummer() {
		return flugnummer;
	}

	public void setFlugnummer(int flugnummer) {
		this.flugnummer = flugnummer;
	}

	@Override
	public String toString() {
		return "Flug [idFlugangebot=" + idFlugangebot + ", datum=" + datum + ", idCaptain=" + idCaptain
				+ ", idFlugzeug=" + idFlugzeug + ", flugnummer=" + flugnummer + "]";
	}

}

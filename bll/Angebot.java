package bll;

public class Angebot {
	private int id;
	private int idAirline;
	private int idFlughafenAb;
	private int idFlughafenAn;
	private String zeiten;

	public Angebot(int id, int idAirline, int idFlughafenAb, int idFlughafenAn, String zeiten) {
		super();
		this.id = id;
		this.idAirline = idAirline;
		this.idFlughafenAb = idFlughafenAb;
		this.idFlughafenAn = idFlughafenAn;
		this.zeiten = zeiten;
	}

	public Angebot() {
		super();
	}

	public Angebot(int idAirline, int idFlughafenAb, int idFlughafenAn, String zeiten) {
		super();
		this.idAirline = idAirline;
		this.idFlughafenAb = idFlughafenAb;
		this.idFlughafenAn = idFlughafenAn;
		this.zeiten = zeiten;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAirline() {
		return idAirline;
	}

	public void setIdAirline(int idAirline) {
		this.idAirline = idAirline;
	}

	public int getIdFlughafenAb() {
		return idFlughafenAb;
	}

	public void setIdFlughafenAb(int idFlughafenAb) {
		this.idFlughafenAb = idFlughafenAb;
	}

	public int getIdFlughafenAn() {
		return idFlughafenAn;
	}

	public void setIdFlughafenAn(int idFlughafenAn) {
		this.idFlughafenAn = idFlughafenAn;
	}

	public String getZeiten() {
		return zeiten;
	}

	public void setZeiten(String zeiten) {
		this.zeiten = zeiten;
	}

	@Override
	public String toString() {
		return "Angebot [id=" + id + ", idAirline=" + idAirline + ", idFlughafenAb=" + idFlughafenAb
				+ ", idFlughafenAn=" + idFlughafenAn + ", zeiten=" + zeiten + "]";
	}

}

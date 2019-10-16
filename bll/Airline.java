package bll;

public class Airline {
	private int id;
	private int idHeimatFH;
	private String bezeichnung;

	public Airline(int id, int idHeimatFH, String bezeichnung) {
		super();
		this.id = id;
		this.idHeimatFH = idHeimatFH;
		this.bezeichnung = bezeichnung;
	}

	public Airline() {
		super();
	}

	public Airline(int idHeimatFH, String bezeichnung) {
		super();
		this.idHeimatFH = idHeimatFH;
		this.bezeichnung = bezeichnung;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdHeimatFH() {
		return idHeimatFH;
	}

	public void setIdHeimatFH(int idHeimatFH) {
		this.idHeimatFH = idHeimatFH;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return "Airline [id=" + id + ", idHeimatFH=" + idHeimatFH + ", bezeichnung=" + bezeichnung + "]";
	}

}

package bll;

public class Airline {
	private int id;
	private Flughafen heimatFlughafen;
	private String bezeichnung;

	public Airline() {
		super();
	}

	public Airline(Flughafen heimatFlughafen, String bezeichnung) {
		super();
		this.heimatFlughafen = heimatFlughafen;
		this.bezeichnung = bezeichnung;
	}

	public Airline(int id, Flughafen heimatFlughafen, String bezeichnung) {
		super();
		this.id = id;
		this.heimatFlughafen = heimatFlughafen;
		this.bezeichnung = bezeichnung;
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

	public Flughafen getHeimatFlughafen() {
		return heimatFlughafen;
	}

	public void setHeimatFlughafen(Flughafen heimatFlughafen) {
		this.heimatFlughafen = heimatFlughafen;
	}

	@Override
	public String toString() {
		return "Airline [id=" + id + ", heimatFlughafen=" + heimatFlughafen + ", bezeichnung=" + bezeichnung + "]";
	}

}

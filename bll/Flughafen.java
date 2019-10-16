package bll;

public class Flughafen {
	private int id;
	private String bezeichnung;
	private int code;
	private String ort;

	public Flughafen(int id, String bezeichnung, int code, String ort) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.code = code;
		this.ort = ort;
	}

	public Flughafen(String bezeichnung, int code, String ort) {
		super();
		this.bezeichnung = bezeichnung;
		this.code = code;
		this.ort = ort;
	}

	public Flughafen() {
		super();
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Override
	public String toString() {
		return "Flughafen [id=" + id + ", bezeichnung=" + bezeichnung + ", code=" + code + ", ort=" + ort + "]";
	}

}

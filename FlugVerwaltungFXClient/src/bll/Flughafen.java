package bll;

public class Flughafen {
	private int id;
	private String bezeichnung;
	private String code;
	private String ort;
	private Double breitengrad;
	private Double laengengrad;

	public Flughafen(int id, String bezeichnung, String code, String ort, Double bg, Double lg) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.code = code;
		this.ort = ort;
		this.breitengrad = bg;
		this.laengengrad = lg;
	}

	public Flughafen(String bezeichnung, String code, String ort, Double bg, Double lg) {
		super();
		this.bezeichnung = bezeichnung;
		this.code = code;
		this.ort = ort;
		this.breitengrad = bg;
		this.laengengrad = lg;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public Double getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(Double breitengrad) {
		this.breitengrad = breitengrad;
	}

	public Double getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(Double laengengrad) {
		this.laengengrad = laengengrad;
	}

	@Override
	public String toString() {
		return "Code=" + code + " Ort=" + ort;
	}

}

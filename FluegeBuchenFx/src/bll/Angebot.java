package bll;

public class Angebot {
		private String flugNummer;
		private Airline airline;
		private Flughafen flughafenAb;
		private Flughafen flughafenAn;
		private String ankunftszeit;
		private String abflugszeit;

		public Angebot() {
			super();
		}

		public Angebot(String id, Airline airline, Flughafen flughafenAb, Flughafen flughafenAn, String ankunftszeit, String abflugszeit) {
			super();
			this.flugNummer = id;
			this.airline = airline;
			this.flughafenAb = flughafenAb;
			this.flughafenAn = flughafenAn;
			this.ankunftszeit = ankunftszeit;
			this.abflugszeit = abflugszeit;
		}

		public Angebot(Airline airline, Flughafen flughafenAb, Flughafen flughafenAn, String ankunftszeit, String abflugszeit) {
			super();
			this.airline = airline;
			this.flughafenAb = flughafenAb;
			this.flughafenAn = flughafenAn;
			this.ankunftszeit = ankunftszeit;
			this.abflugszeit = abflugszeit;
		}

		public String getFlugNummer() {
			return flugNummer;
		}

		public void setFlugNummer(String id) {
			this.flugNummer = id;
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


		public String getAnkunftszeit() {
			return ankunftszeit;
		}

		public void setAnkunftszeit(String ankunftszeit) {
			this.ankunftszeit = ankunftszeit;
		}

		public String getAbflugszeit() {
			return abflugszeit;
		}

		public void setAbflugszeit(String abflugszeit) {
			this.abflugszeit = abflugszeit;
		}

		@Override
		public String toString() {
			return this.flugNummer;
		}
}

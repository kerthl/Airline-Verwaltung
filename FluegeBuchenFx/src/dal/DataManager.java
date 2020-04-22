package dal;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bll.Fliegt;
import bll.Flug;
import bll.Passagier;	

public class DataManager {
	Client client = ClientBuilder.newClient();

	String resource = "http://localhost:8080/";
	WebTarget webTarget = client.target(resource);
	WebTarget webTargetFlugListe = webTarget.path("01_FlugVerwaltung_WebService/Verwaltung/FlugListe");
	WebTarget webTargetAnzahlBuchungen = webTarget.path("01_FlugVerwaltung_WebService/Verwaltung/AnzahlBuchungen/");
	WebTarget webTargetSetPassagier = webTarget.path("01_FlugVerwaltung_WebService/Verwaltung/Passagier/");
	WebTarget webTargetSetFliegt = webTarget.path("01_FlugVerwaltung_WebService/Verwaltung/Fliegt/");

	public DataManager() {
		
	}
	
	public ArrayList<Flug> getAllFluege() throws Exception {
		List<Flug> fluegeAsList = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		invocationBuilder = webTargetFlugListe.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			
		fluegeAsList = response.readEntity(new GenericType<List<Flug>>() {
		});
		
			
		return (ArrayList) fluegeAsList;
	}
	
	public int getAnzahlFlugBuchungen(String flgNr) {		
		int anzahl = 0;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		invocationBuilder = webTargetAnzahlBuchungen.path(flgNr).request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.accept(MediaType.TEXT_PLAIN_TYPE).get();
			
		anzahl = response.readEntity(new GenericType<Integer>() {
		});
		
		return anzahl;
	}
	
	public void setPassagier(Passagier p) {
		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = this.webTargetSetPassagier.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.post(Entity.entity(p, MediaType.APPLICATION_JSON));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setFliegt(Fliegt f) {
		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = this.webTargetSetFliegt.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.post(Entity.entity(f, MediaType.APPLICATION_JSON));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

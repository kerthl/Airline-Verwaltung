package dal;

import com.google.gson.JsonSyntaxException;
import bll.*;
import util.PropertyManager;

import java.io.FileNotFoundException;
import java.io.IOException;
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

public class DataManager {

	private static DataManager db = null;
	private static PropertyManager pm = null;
	Client client = ClientBuilder.newClient();
	// TODO: auslagern in Config
	String resource;
	WebTarget webTarget;
	WebTarget webTargetAngeboteList;
	WebTarget webTargetAngeboteDetail;
	WebTarget webTargetPilotenList;
	WebTarget webTargetFlugzeugList;
	WebTarget webTargetFlugList;
	WebTarget webTargetFlugDetail;
	WebTarget webTargetAirlineList;
	WebTarget webTargetFlugzeugDetail;
	WebTarget webTargetFlughafenListe;

	public static DataManager getInstance() {
		if (db == null) {
			db = new DataManager();
		}
		return db;
	}

	private DataManager() {
		try {
			pm = PropertyManager.getInstance();
			webTarget = client.target(pm.readProperty("resource"));
			webTargetPilotenList = webTarget.path(pm.readProperty("webTargetPilotenList"));
			webTargetAngeboteDetail = webTarget.path(pm.readProperty("webTargetAngeboteDetail"));
			webTargetFlugzeugList = webTarget.path(pm.readProperty("webTargetFlugzeugList"));
			webTargetFlugList = webTarget.path(pm.readProperty("webTargetFlugList"));
			webTargetFlugDetail = webTarget.path(pm.readProperty("webTargetFlugDetail"));
			webTargetAirlineList = webTarget.path(pm.readProperty("webTargetAirlineList"));
			webTargetFlugzeugDetail = webTarget.path(pm.readProperty("webTargetFlugzeugDetail"));
			webTargetFlughafenListe = webTarget.path(pm.readProperty("webTargetFlughafenListe"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Flug> getFluege() throws Exception {
		String retFluegeAsJson = null;
		ArrayList<Flug> fluegeAsList = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {

			invocationBuilder = webTargetFlugList.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			fluegeAsList = response.readEntity(new GenericType<ArrayList<Flug>>() {
			});

		} catch (JsonSyntaxException ex) {
			throw new Exception(retFluegeAsJson);
		} finally {

		}

		return fluegeAsList;
	}

	public ArrayList<Angebot> getAngebote() throws Exception {
		String retAngeboteAsJson = null;
		ArrayList<Angebot> angeboteAsList = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {

			invocationBuilder = webTargetAngeboteList.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			angeboteAsList = response.readEntity(new GenericType<ArrayList<Angebot>>() {
			});

		} catch (JsonSyntaxException ex) {
			throw new Exception(retAngeboteAsJson);
		} finally {

		}

		return angeboteAsList;
	}
	
	public boolean addAngebot(Angebot angebot) {
		Invocation.Builder invocationBuilder = this.webTargetAngeboteDetail.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(angebot, MediaType.APPLICATION_JSON));

		if (response.getStatus() == 201) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Flugzeug> getFlugzeuge() throws Exception {

		String retFlugzeugeAsJson = null;
		ArrayList<Flugzeug> fluegzeugeAsList = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = webTargetFlugzeugList.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			fluegzeugeAsList = response.readEntity(new GenericType<ArrayList<Flugzeug>>() {
			});

		} catch (JsonSyntaxException ex) {
			throw new Exception(retFlugzeugeAsJson);
		} finally {

		}

		return fluegzeugeAsList;
	}
	
	public void addFlugzeug(Flugzeug f) {
		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = this.webTargetFlugzeugDetail.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.post(Entity.entity(f, MediaType.APPLICATION_JSON));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Airline> getAirlines() throws Exception {

		String retAirlinesAsJson = null;
		ArrayList<Airline> airlinesAsList = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = webTargetAirlineList.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			airlinesAsList = response.readEntity(new GenericType<ArrayList<Airline>>() {
			});

		} catch (JsonSyntaxException ex) {
			throw new Exception(retAirlinesAsJson);
		} finally {

		}

		return airlinesAsList;
	}
	
	public ArrayList<Flughafen> getAirports() throws Exception {

		List<Flughafen> airports = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = webTargetFlughafenListe.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			airports = response.readEntity(new GenericType<List<Flughafen>>() {
			});

		} catch (Exception ex) {
			throw new Exception();
		}

		return (ArrayList<Flughafen>) airports;
	}

	
	public ArrayList<Pilot> getPiloten() throws Exception {

		String retPilotAsJson = null;
		ArrayList<Pilot> pilotenAsList = null;

		Invocation.Builder invocationBuilder = null;
		Response response = null;

		try {
			invocationBuilder = webTargetPilotenList.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();
			pilotenAsList = response.readEntity(new GenericType<ArrayList<Pilot>>() {
			});

		} catch (JsonSyntaxException ex) {
			throw new Exception(retPilotAsJson);
		} finally {

		}

		return pilotenAsList;
	}

	public boolean createFlug(Flug newFlug) {
		boolean result = false;
		System.out.println("Bauen");
		try {
			Invocation.Builder invocationBuilder = this.webTargetFlugDetail.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(newFlug, MediaType.APPLICATION_JSON));
			if (response.getStatus() == 201) {
				result = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public void updateFlug(Flug f) {
		Invocation.Builder invocationBuilder = this.webTargetFlugDetail.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.put(Entity.entity(f, MediaType.APPLICATION_JSON));
	}
}

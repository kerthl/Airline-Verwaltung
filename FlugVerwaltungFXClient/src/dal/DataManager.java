package dal;

import com.google.gson.JsonSyntaxException;
import bll.*;
import util.PropertyManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
	WebTarget webTargetPilotenList;
	WebTarget webTargetFlugzeugList;
	WebTarget webTargetFlugList;
	WebTarget webTargetFlugDetail;

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
			webTargetFlugzeugList = webTarget.path(pm.readProperty("webTargetFlugzeugList"));
			webTargetFlugList = webTarget.path(pm.readProperty("webTargetFlugList"));
			webTargetFlugDetail = webTarget.path(pm.readProperty("webTargetFlugDetail"));
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

	/*
	 * public boolean addAktion(AktionsTyp a) {
	 * 
	 * boolean result = false; try { Invocation.Builder invocationBuilder =
	 * this.webTargetAktionDetail.request(MediaType.APPLICATION_JSON); Response
	 * response = invocationBuilder.post(Entity.entity(a,
	 * MediaType.APPLICATION_JSON)); if (response.getStatus() == 201) { result =
	 * true; } } catch (Exception ex) { ex.printStackTrace(); } return result; }
	 * 
	 * public void deleteAktion(AktionsTyp selectedItem) { webTargetAktionDetail =
	 * webTargetAktionDetail.path("/" + selectedItem.getId()); Invocation.Builder
	 * invocationBuilder =
	 * webTargetAktionDetail.request(MediaType.APPLICATION_JSON); Response response
	 * = invocationBuilder.delete(); this.resetWebTargetAktion(); }
	 * 
	 * public void updateAktion(AktionsTyp itemToUpdate) { Invocation.Builder
	 * invocationBuilder =
	 * webTargetAktionDetail.request(MediaType.APPLICATION_JSON); Response response
	 * = invocationBuilder.put(Entity.entity(itemToUpdate,
	 * MediaType.APPLICATION_JSON)); }
	 * 
	 * private void resetWebTargetArtikel() { webTargetArtikelDetail = null;
	 * webTargetArtikelList = null; webTargetArtikelDetail =
	 * webTarget.path(pm.readProperty("webTargetArtikelDetail"));
	 * webTargetArtikelList =
	 * webTarget.path(pm.readProperty("webTargetArtikelList")); }
	 * 
	 * private void resetWebTargetGegenstandel() { webTargetGegenstandeDetail =
	 * null; webTargetGegenstandeList = null; webTargetGegenstandeDetail =
	 * webTarget.path(pm.readProperty("webTargetGegenstandeDetail"));
	 * webTargetGegenstandeList =
	 * webTarget.path(pm.readProperty("webTargetGegenstandeList")); }
	 * 
	 * private void resetWebTargetAktion() { webTargetAktionDetail = null;
	 * webTargetAktionList = null; webTargetAktionDetail =
	 * webTarget.path(pm.readProperty("webTargetAktionDetail")); webTargetAktionList
	 * = webTarget.path(pm.readProperty("webTargetAktionList")); }
	 */
}

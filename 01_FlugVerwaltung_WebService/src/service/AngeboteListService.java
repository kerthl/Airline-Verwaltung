package service;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Angebot;
import dal.Database;

@Path("AngebotListe")
public class AngeboteListService {

	@Context
	private UriInfo context;

	public AngeboteListService() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAngebote() {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Angebot> alleAngebote = db.getAngebote();
			response.entity(new Gson().toJson(alleAngebote));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Angebote" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET ALLE Angebote called");
		return response.build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{flughafenAbId}")
	public Response getAngeboteForAirport(@PathParam("flughafenAbId") String flughafenAbId) {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Angebot> alleAngebote = db.getAngeboteByAirport(Integer.valueOf(flughafenAbId));
			response.entity(new Gson().toJson(alleAngebote));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Angebote" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET ALLE Angebote for airport called");
		return response.build();
	}

}


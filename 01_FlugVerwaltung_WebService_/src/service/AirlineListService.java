package service;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Airline;
import dal.Database;

@Path("AirlineListe")
public class AirlineListService {

	@Context
	private UriInfo context;

	public AirlineListService() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAirlines() {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Airline> alleA = db.getAirlines();
			response.entity(new Gson().toJson(alleA));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Airlines" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET ALLE Airlines called");
		return response.build();
	}

}


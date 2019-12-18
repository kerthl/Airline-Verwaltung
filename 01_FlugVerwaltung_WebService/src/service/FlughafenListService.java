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

import bll.Flughafen;
import dal.Database;

@Path("/")
public class FlughafenListService {

	@Context
	private UriInfo context;

	public FlughafenListService() {
	}

	@GET
	@Path("FlughafenListe")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFlugzeuge() {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Flughafen> alleFh = db.GetFlughafen();
			response.entity(new Gson().toJson(alleFh));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Flughäfen" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET ALLE FLUGHÄFEN called");
		return response.build();
	}

}


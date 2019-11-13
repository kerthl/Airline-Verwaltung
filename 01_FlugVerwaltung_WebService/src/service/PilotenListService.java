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
import bll.Flugzeug;
import bll.Pilot;
import dal.Database;

@Path("PilotenListe")
public class PilotenListService {

	@Context
	private UriInfo context;

	public PilotenListService() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPiloten() {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Pilot> allePiloten = db.getPiloten();
			response.entity(new Gson().toJson(allePiloten));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Flugzeuge" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET ALLE Piloten called");
		return response.build();
	}

}


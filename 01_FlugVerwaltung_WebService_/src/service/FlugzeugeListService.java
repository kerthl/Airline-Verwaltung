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
import dal.Database;

@Path("FlugzeugListe")
public class FlugzeugeListService {

	@Context
	private UriInfo context;

	public FlugzeugeListService() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFlugzeuge() {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Flugzeug> alleFZ = db.getFlugzeuge();
			response.entity(new Gson().toJson(alleFZ));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Flugzeuge" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET ALLE FLUGZEUGE called");
		return response.build();
	}

}


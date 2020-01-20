package service;

import java.io.IOException;
import java.util.LinkedList;

import javax.ws.rs.Consumes;
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
import bll.Flughafen;
import bll.Flugzeug;
import dal.Database;

@Path("FlughafenDetail")
public class FlughafenDetailService {

	@Context
	private UriInfo context;

	public FlughafenDetailService() {
	}
	
	@GET
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getall(@PathParam("id") String id) throws IOException {
		System.out.println("Test");
		return null;
	}

	@GET
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}/getAngebote")
	public Response get(@PathParam("id") String id) throws IOException {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			LinkedList<Angebot> alleAngebote = db.getAngeboteByAirport(Integer.valueOf(id));
			response.entity(new Gson().toJson(alleAngebote));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in get alle Angebote" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("======================webservice GET Angebote for one Airport called");
		return response.build();
	}

}

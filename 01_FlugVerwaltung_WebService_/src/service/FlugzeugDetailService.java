package service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Flugzeug;
import dal.Database;

@Path("FlugzeugDetail")
public class FlugzeugDetailService {

	@Context
	private UriInfo context;

	public FlugzeugDetailService() {
	}

	@GET
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response getFlugzeuz(@PathParam("id") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			Flugzeug flugzeug = db.getFlugzeugByID(Integer.parseInt(id));
			response.entity(new Gson().toJson(flugzeug));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] in getFlugzeug Service " + e.getMessage());
		}

		return response.build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newAktionstyp(String strFlugzeug) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			Flugzeug flugzeug = new Gson().fromJson(strFlugzeug, Flugzeug.class);
			db.setFlugzeug(flugzeug);
			response.entity("flugzeug added");
			} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		
		System.out.println("======================NEW Flugzeug: " + strFlugzeug);
		return response.build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateFlugzeug(String strFlugzeug) throws IOException {
		Database db = Database.getInstance();
	    Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			Flugzeug f = new Gson().fromJson(strFlugzeug, Flugzeug.class);
			db.updateFlugzeug(f);
			response.entity("Flugzeug updated");

		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("{id}")
	public Response deleteFlugzeug(@PathParam("id") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			db.deleteFlugzeug(Integer.parseInt(id));
			response.entity("Flugzeug deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		System.out.println("======================Flugzeug DELETED ");
		return response.build();
	}
}

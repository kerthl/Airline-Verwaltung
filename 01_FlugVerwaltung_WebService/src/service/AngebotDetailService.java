package service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bll.Angebot;
import bll.Flug;
import dal.Database;

/**
 * REST Web Service
 *
 */

@Path("AngebotDetail")
public class AngebotDetailService {
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newArtikel(String strAngebot) throws Exception {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		System.out.println("======================NEW Angebot: " + strAngebot);

		try {
			Angebot angebot = new Gson().fromJson(strAngebot, Angebot.class);
			db.setAngebot(angebot);
			response.entity("angebot added");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateAngebot(String strAngebot) throws IOException {
		Database db = Database.getInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			Angebot a = new Gson().fromJson(strAngebot, Angebot.class);
			db.updateAngebot(a);
			response.entity("Angebot updatet");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		return response.build();
	}

	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response deleteAngebot(@PathParam("id") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			db.deleteAngebot(Integer.valueOf(id));
			response.entity("Angebot deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

}

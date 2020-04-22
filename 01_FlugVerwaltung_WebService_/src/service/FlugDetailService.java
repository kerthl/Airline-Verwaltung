package service;

import java.io.IOException;

import javax.ws.rs.core.Context;

import javax.ws.rs.core.UriInfo;

import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;

import javax.ws.rs.Produces;

import javax.ws.rs.GET;

import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bll.*;

import dal.Database;

/**
 * 
 * REST Web Service
 *
 * 
 * 
 */

@Path("FlugDetail")

public class FlugDetailService {

	@Context

	private UriInfo context;

	public FlugDetailService() {

	}

	@GET

	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })

	@Produces({ MediaType.APPLICATION_JSON })

	@Path("/{id}")

	public Response getOne(@PathParam("id") String id) throws IOException {

		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		Database db = Database.getInstance();

		try {

			Flug one = db.getFlugByID(id);

			response.entity(new Gson().toJson(one));

		} catch (Exception e) {

			response.status(Response.Status.BAD_REQUEST);

			response.entity("[ERROR] " + e.getMessage());

		}

		return response.build();

	}

	@POST

	@Consumes({ MediaType.APPLICATION_JSON })

	public Response newFlug(String strFlug) throws Exception {

		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		Database db = Database.getInstance();

		System.out.println("======================NEW Flug: " + strFlug);

		try {

			Flug flug = new Gson().fromJson(strFlug, Flug.class);

			db.addFlug(flug);

			response.entity("Flug added");

		} catch (Exception e) {

			response.status(Response.Status.BAD_REQUEST);

			response.entity("[ERROR] " + e.getMessage());

		}

		return response.build();

	}

	@PUT

	@Consumes({ MediaType.APPLICATION_JSON })

	public Response updateFlug(String strFlug) throws IOException {

		Database db = Database.getInstance();

		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {

			Flug f = new Gson().fromJson(strFlug, Flug.class);

			db.updateFlug(f);

			response.entity("Flug updatet");

		} catch (Exception e) {

			response.status(Response.Status.BAD_REQUEST);

			response.entity("[ERROR] " + e.getMessage());

		}

		return response.build();

	}
	
	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response deleteArtikel(@PathParam("id") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			//db.deleteOneFlug(Integer.valueOf(id));
			response.entity("artikel deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

}
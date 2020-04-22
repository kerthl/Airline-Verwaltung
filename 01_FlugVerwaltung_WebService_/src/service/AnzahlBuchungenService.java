package service;

import java.io.IOException;

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

import bll.Flug;
import dal.Database;

@Path("AnzahlBuchungen")

public class AnzahlBuchungenService {

	@Context

	private UriInfo context;

	public AnzahlBuchungenService() {

	}

	@GET

	@Path("/{flgNr}")

	public Response getAnzahlBuchungen(@PathParam("flgNr") String flgNr) throws IOException {

		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		Database db = Database.getInstance();

		try {

			int anzahl = db.getAnzahlBuchungen(flgNr);

			response.entity(anzahl);

		} catch (Exception e) {

			response.status(Response.Status.BAD_REQUEST);

			response.entity("[ERROR] " + e.getMessage());

		}

		return response.build();

	}
}

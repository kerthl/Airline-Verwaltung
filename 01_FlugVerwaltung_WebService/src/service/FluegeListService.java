package service;



import java.io.IOException;

import java.util.LinkedList;



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

import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bll.*;

import dal.Database;



/**

 * REST Web Service

 *

 */



@Path("FlugListe")

public class FluegeListService {



	@Context

	private UriInfo context;



	public FluegeListService() {

	}



	@GET

	@Produces({ MediaType.APPLICATION_JSON })

	public Response getFLuege() {

		Database db = Database.getInstance();

		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {

			LinkedList<Flug> allGeg = db.getFluege();

			response.entity(new Gson().toJson(allGeg));

		} catch (Exception e) {

			response.status(Response.Status.BAD_REQUEST);

			response.entity("[ERROR] " + e.getMessage());

			e.printStackTrace();

		}

		System.out.println("======================webservice GET called");

		return response.build();

	}



}
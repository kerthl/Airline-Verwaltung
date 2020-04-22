package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Fliegt;
import bll.Passagier;
import dal.Database;

@Path("Fliegt")

public class FliegtDetailService {

	@Context

	private UriInfo context;

	public FliegtDetailService() {

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newFliegt(String strFliegt) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			Fliegt f = new Gson().fromJson(strFliegt, Fliegt.class);
			db.setFliegt(f);
			response.entity("buchung added");
			} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		
		System.out.println("======================NEW Buchung: " + strFliegt);
		return response.build();
	}

}
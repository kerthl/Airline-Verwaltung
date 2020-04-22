package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Flugzeug;
import bll.Passagier;
import dal.Database;

@Path("Passagier")

public class PassagierDetailService {

	@Context

	private UriInfo context;

	public PassagierDetailService() {

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newPassagier(String strPassagier) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		Database db = Database.getInstance();

		try {
			Passagier p = new Gson().fromJson(strPassagier, Passagier.class);
			db.setPassagier(p);
			response.entity("passagier added");
			} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		
		System.out.println("======================NEW Passagier: " + strPassagier);
		return response.build();
	}

}

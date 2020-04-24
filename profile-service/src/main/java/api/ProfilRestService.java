package api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.Profil;
import domain.service.ProfilService;
import io.swagger.annotations.ApiOperation;

@ApplicationScoped
@Path("/profil")
public class ProfilRestService {

	@Inject
	private ProfilService profilService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@ApiOperation(value = "Get all profil")
	public List<Profil> getDataUser() {
		return profilService.getDataUser();
	}
	
/*	@GET
	@Path("/name")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get the user name")
	public String name() {
		return profilService.name();
	}*/
	
/*	@GET
	@Path("{lei}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specific profil using its lei")
	public Profil get(@PathParam("lei") String lei) {
		return profilService.get(lei);
	}*/

}
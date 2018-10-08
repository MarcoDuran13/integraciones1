package co.edu.uniandes.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("")
public interface RestService {
	
	@GET
    @Path("/obtener-admision-tipo")
    @Produces(MediaType.APPLICATION_JSON)
    public co.edu.uniandes.dto.admisionTipo.Response obtenerAdmisionTipo(@QueryParam("snumerodocumento") String snumerodocumento, @QueryParam("spidm") String spidm,
            @QueryParam("slogin") String slogin,@QueryParam("scodigo") String scodigo);
	
	@GET
    @Path("/obtener-admision-pregrado")
    @Produces(MediaType.APPLICATION_JSON)
    public co.edu.uniandes.dto.admisionPregrado.Response obtenerAdmisionPregrado(@QueryParam("snumerodocumento") String snumerodocumento, @QueryParam("spidm") String spidm,
            @QueryParam("slogin") String slogin,@QueryParam("scodigo") String scodigo,@QueryParam("speriodo") String speriodo );
	
	@GET
    @Path("/obtener-admision-posgrado")
    @Produces(MediaType.APPLICATION_JSON)
    public co.edu.uniandes.dto.admisionPosgrado.Response obtenerAdmisionPosgrado(@QueryParam("snumerodocumento") String snumerodocumento, @QueryParam("spidm") String spidm,
            @QueryParam("slogin") String slogin,@QueryParam("scodigo") String scodigo,@QueryParam("speriodo") String speriodo, @QueryParam("snivel") String snivel);

	@GET
    @Path("/obtener-admision-pregrado")
    @Produces(MediaType.APPLICATION_JSON)
    public co.edu.uniandes.dto.admisionExamen.Response obtenerAdmisionExamen(@QueryParam("snumerodocumento") String snumerodocumento, @QueryParam("spidm") String spidm,
            @QueryParam("slogin") String slogin,@QueryParam("scodigo") String scodigo,@QueryParam("speriodo") String speriodo );
}

package co.edu.uniandes.processor.admisionPosgrado;

import co.edu.uniandes.model.Estudiante;
import co.edu.uniandes.dto.admisionPosgrado.AdmisionPosgrado;
import co.edu.uniandes.dto.admisionPosgrado.Response;
import co.edu.uniandes.model.Inscripcion;
import co.edu.uniandes.model.MensajeOut;
import co.edu.uniandes.model.Periodo;
import co.edu.uniandes.model.Programa;
import co.edu.uniandes.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class DataProcessor
{
  @BeanInject("props")
  private Properties properties;
  
  public void process(Exchange exchange, 
		  				@ExchangeProperty("estado") Boolean estado,
		  				@ExchangeProperty("message") String mensaje, 
		  				@ExchangeProperty("messagetecnico") String mensajetecnico) throws Exception
  {
    Response response = new Response();
    MensajeOut mensajeOut = new MensajeOut();
    List<Map<String, Object>> resultSet = (List) exchange.getIn().getBody();
    List<AdmisionPosgrado> listAdmision = new ArrayList();
    if(estado) {
	    if (resultSet != null && !resultSet.isEmpty())   {
	      for (Map<String, Object> map : resultSet)
	      {
	        AdmisionPosgrado AdmisionPregrado = new AdmisionPosgrado();
	        
	        Estudiante estudiante = new Estudiante();
	        estudiante.setsCodigo(Util.getColumnString(map.get("CODIGO_ESTUDIANTE").toString()));
	        AdmisionPregrado.setsEstudiante(estudiante);
	        
	        Periodo periodo = new Periodo();
	        periodo.setsPeriodo(Util.getColumnString(map.get("PERIODO")));
	        AdmisionPregrado.setsPeriodo(periodo);
	        
	        Programa programa = new Programa();
	        programa.setsNivel(Util.getColumnString(map.get("NIVEL").toString()));
	        programa.setsCodigoPrograma(Util.getColumnString(map.get("CODIGO_PROGRAMA").toString()));
	        AdmisionPregrado.setsPrograma(programa);
	        
	        Inscripcion inscripcion = new Inscripcion();
	        inscripcion.setsTipoRegistro(Util.getColumnString(map.get("TIPO_INSCRIP").toString()));
	        AdmisionPregrado.setsInscripcion(inscripcion);
	        
	        AdmisionPregrado.setsAdmitido(Util.getColumnString(map.get("ADMITIDO").toString()));
	        
	        listAdmision.add(AdmisionPregrado);
	      }
	      mensajeOut.setsCodigoRespuesta("http 200");
	      mensajeOut.setbOperacionExitosa(true);
	      mensajeOut.setsMensajeRtaTecnico(this.properties.getProperty("msj.operacion.exitosa"));
	      mensajeOut.setsMensajeRtaUsuario(this.properties.getProperty("msj.operacion.exitosa"));
	    } 
	    else  {
	      Estudiante estudiante = new Estudiante();
	      estudiante.setsPidm("");
	      
	      Periodo periodo = new Periodo();
	      periodo.setsPeriodo("");
	      mensajeOut.setbOperacionExitosa(false);
	      mensajeOut.setsCodigoRespuesta("http 404");
	      exchange.getOut().setHeader("CamelHttpCode", this.properties.getProperty("http.code.not.found"));
	      mensajeOut.setsMensajeRtaTecnico(this.properties.getProperty("excepcion.registros.vacios"));
	      mensajeOut.setsMensajeRtaUsuario(this.properties.getProperty("excepcion.registros.vacios"));
	    }
    } else {
    	Estudiante estudiante = new Estudiante();
	      estudiante.setsPidm("");
	      
	      Periodo periodo = new Periodo();
	      periodo.setsPeriodo("");
	      mensajeOut.setsCodigoRespuesta("http 500");
	      exchange.getOut().setHeader("CamelHttpCode", this.properties.getProperty("http.code.server.error"));
	      mensajeOut.setsMensajeRtaTecnico(mensaje);
	      mensajeOut.setsMensajeRtaUsuario(mensaje);
	      mensajeOut.setbOperacionExitosa(false);
  }
	    response.setAdmisionPosgrado(listAdmision);
	    response.setMensajeOut(mensajeOut);
	  
	  exchange.getOut().setHeader("CamelAcceptContentType", "application/json; charset=UTF-8");
	  exchange.getOut().setHeader("Content-Type", "application/json; charset=UTF-8");
	  exchange.getOut().setHeader("CamelCxfRsResponseClass", Response.class);
	  exchange.getOut().setBody(response, Response.class);
        String responseJson = new ObjectMapper().writeValueAsString(response);
        exchange.setProperty("responseAudit", responseJson);
  }
}

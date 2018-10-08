package co.edu.uniandes.processor.admisionExamen;

import co.edu.uniandes.dto.admisionExamen.Examen;
import co.edu.uniandes.dto.admisionExamen.Response;
import co.edu.uniandes.model.Estudiante;
import co.edu.uniandes.model.MensajeOut;
import co.edu.uniandes.model.Periodo;
import co.edu.uniandes.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

public class DataProcessor
{
  @BeanInject("props")
  private Properties properties;
  
  public void process(Exchange exchange, 
		  			@ExchangeProperty("messageusuario") String mensajeusuario, 
		  			@ExchangeProperty("message") String mensaje, 
		  			@ExchangeProperty("estado") boolean estado, 
		  			@ExchangeProperty("message") String message)
    throws Exception
  {
    List<Map<String, Object>> result = (List)exchange.getIn().getBody();
    List<Examen> examenes = new ArrayList();
    List<Examen> lista = new ArrayList();
    
    Response response = new Response();
    MensajeOut mensajeOut = new MensajeOut();
    if(estado) {
        if ((result != null) && (!result.isEmpty()))
        {
          for (Map<String, Object> map : result)
          {
            Examen examen = new Examen();
            examen.setsPidm((int) Util.getColumnIntegerFromBigDecimal(map.get("PIDM")));
            examen.setsCodigoExamen(Util.getColumnString(map.get("CODIGO_EXAMEN")));
            examen.setsNombreExamen(Util.getColumnString(map.get("NOMBRE_EXAMEN")));
            examen.setsTestScore(Util.getColumnString(map.get("TEST_SCORE")));
            examen.setsPuesto(Util.getColumnString(map.get("PUESTO")));
            examen.setsPeriodo(Util.getColumnString(map.get("PERIODO")));
            examen.setsSnp(Util.getColumnString(map.get("SNP")));
            XMLGregorianCalendar fechapres = Util.getColumnDate(map.get("FECHA_PRESENTACION"));
            Date dpres =  fechapres.toGregorianCalendar().getTime();
            examen.setdFechaPresentacion(dpres);
            XMLGregorianCalendar fechainicio = Util.getColumnDate(map.get("FECHA_ACTUALIZACION"));
            Date dact =  fechainicio.toGregorianCalendar().getTime();
            examen.setdFechaActualizacion(dact);
            examenes.add(examen);
          }
          mensajeOut.setsMensajeRtaTecnico(this.properties.getProperty("msj.operacion.exitosa"));
          mensajeOut.setsMensajeRtaUsuario(this.properties.getProperty("msj.operacion.exitosa"));
          mensajeOut.setbOperacionExitosa(true);
          mensajeOut.setsCodigoRespuesta("http 200");
        } 
        else
        {
          mensajeOut.setsMensajeRtaTecnico(this.properties.getProperty("excepcion.registros.vacios"));
          mensajeOut.setsMensajeRtaUsuario(this.properties.getProperty("excepcion.registros.vacios"));
          exchange.getOut().setHeader("CamelHttpCode", this.properties.getProperty("http.code.not.found"));
          mensajeOut.setbOperacionExitosa(false);
          mensajeOut.setsCodigoRespuesta("http 404");
        }
    }
    else {
    	Estudiante estudiante = new Estudiante();
	      estudiante.setsPidm("");
	      
	      Periodo periodo = new Periodo();
	      periodo.setsPeriodo("");
	      mensajeOut.setsCodigoRespuesta("http 500");
	      exchange.getOut().setHeader("CamelHttpCode", this.properties.getProperty("http.code.server.error"));
	      mensajeOut.setsMensajeRtaTecnico(mensaje);
	      mensajeOut.setsMensajeRtaUsuario(mensaje);
    }
        response.setExamen(examenes);
        response.setMensajeOut(mensajeOut);
      
    exchange.getOut().setHeader("CamelAcceptContentType", "application/json; charset=UTF-8");
    exchange.getOut().setHeader("Content-Type", "application/json; charset=UTF-8");
    exchange.getOut().setHeader("CamelCxfRsResponseClass", Response.class);
    exchange.getOut().setBody(response, Response.class);
    String responseJson = new ObjectMapper().writeValueAsString(response);
    exchange.setProperty("responseAudit", responseJson);
  }
}


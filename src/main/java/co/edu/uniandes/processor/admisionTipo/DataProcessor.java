package co.edu.uniandes.processor.admisionTipo;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import co.edu.uniandes.model.Estudiante;
import co.edu.uniandes.model.MensajeOut;
import co.edu.uniandes.model.Periodo;
import co.edu.uniandes.dto.admisionTipo.Response;

public class DataProcessor {

	@BeanInject(value = "props")
	private Properties properties;

	public void process(Exchange exchange,
			@ExchangeProperty("estado") boolean estado,
			@ExchangeProperty("message") String mensaje) throws Exception{
		List<Map<String, Object>> result = (List<Map<String, Object>>) exchange.getIn().getBody();
		Response response = new Response();
		MensajeOut mensajeOut = new MensajeOut();
		if(estado) {
			if (result != null && !result.isEmpty()) {
				Map	<String, Object> resp = (Map) result.get(0);
				response.setsTipoAdmision((String)resp.get("TIPO_ADMISION"));
				mensajeOut.setsMensajeRtaTecnico(this.properties.getProperty("msj.operacion.exitosa"));
				mensajeOut.setsMensajeRtaUsuario(this.properties.getProperty("msj.operacion.exitosa"));
				mensajeOut.setbOperacionExitosa(true);
				mensajeOut.setsCodigoRespuesta("http 200");
			} else {
				exchange.getOut().setHeader("CamelHttpCode", this.properties.getProperty("http.code.not.found"));
				mensajeOut.setbOperacionExitosa(false);
				mensajeOut.setsMensajeRtaTecnico(this.properties.getProperty("excepcion.registros.vacios"));
				mensajeOut.setsMensajeRtaUsuario(this.properties.getProperty("excepcion.registros.vacios"));
			  }
		}else {
			    	Estudiante estudiante = new Estudiante();
				      estudiante.setsPidm("");
				      
				      Periodo periodo = new Periodo();
				      periodo.setsPeriodo("");
				      mensajeOut.setsCodigoRespuesta("http 500");
				      mensajeOut.setbOperacionExitosa(false);
				      exchange.getOut().setHeader("CamelHttpCode", this.properties.getProperty("http.code.server.error"));
				      mensajeOut.setsMensajeRtaTecnico(mensaje);
				      mensajeOut.setsMensajeRtaUsuario(mensaje);
		} 
    	response.setMensajeOut(mensajeOut);
			
		exchange.getOut().setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json; charset=UTF-8");
		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json; charset=UTF-8");
		exchange.getOut().setHeader(CxfConstants.CAMEL_CXF_RS_RESPONSE_CLASS, Response.class);
		exchange.getOut().setBody(response, Response.class);
		String responseJson = new ObjectMapper().writeValueAsString(response);
		exchange.setProperty("responseAudit", responseJson);
	}

}

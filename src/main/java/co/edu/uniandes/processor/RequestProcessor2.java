package co.edu.uniandes.processor;

import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class RequestProcessor2 implements Processor{

	public void process(Exchange exchange) throws Exception {
		
		if (exchange.getIn() != null) {
			String queryString = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
			MultivaluedMap<String, String> queryMap = JAXRSUtils.getStructuredParams(queryString, "&", false, false);
			for (Entry<String, List<String>> eachQueryParam : queryMap.entrySet()) {
                exchange.setProperty(eachQueryParam.getKey(), eachQueryParam.getValue().get(0));
            }
			int length = queryMap.size();
			String requestJson = new ObjectMapper().writeValueAsString(queryString);
			exchange.setProperty("length", length);
		    exchange.setProperty("requestAudit", requestJson);
			if(exchange.getIn().getHeader(Exchange.HTTP_PATH).equals("/obtener-admision-posgrado")) {
				validate1(exchange);
			} else {
				validate(exchange);
			}
		}
	}

	private void validate(Exchange exchange) {

		String msg = null;
		validation: {

			if ((int)exchange.getProperty("length") > 5) {
				msg = "Excede la cantidad de parámetros del servicio";
				break validation;
			} else {
				if((exchange.getIn().getHeader("snumerodocumento") == null||exchange.getIn().getHeader("snumerodocumento").equals("")) 
						&& (exchange.getIn().getHeader("spidm") == null||exchange.getIn().getHeader("spidm").equals(""))
						&& (exchange.getIn().getHeader("slogin") == null||exchange.getIn().getHeader("slogin").equals(""))
						&& (exchange.getIn().getHeader("scodigo") == null||exchange.getIn().getHeader("scodigo").equals(""))) {
					msg = "Parámetros insuficientes para realizar la consulta. Por favor revise la documentación";
					break validation;
				} else if(exchange.getIn().getHeader("speriodo") == null||exchange.getIn().getHeader("speriodo").equals("")) {
					msg = "Parámetro speriodo inválido o inexistente";
					break validation;
				}
			}
		}
		if (msg != null)
			throw new IllegalArgumentException(msg);
	}
	private void validate1(Exchange exchange) {

		String msg = null;
		validation: {

			if ((int)exchange.getProperty("length") > 6) {
				msg = "Excede la cantidad de parámetros del servicio";
				break validation;
			} else {
				if((exchange.getIn().getHeader("snumerodocumento") == null||exchange.getIn().getHeader("snumerodocumento").equals("")) 
						&& (exchange.getIn().getHeader("spidm") == null||exchange.getIn().getHeader("spidm").equals(""))
						&& (exchange.getIn().getHeader("slogin") == null||exchange.getIn().getHeader("slogin").equals(""))
						&& (exchange.getIn().getHeader("scodigo") == null||exchange.getIn().getHeader("scodigo").equals(""))) {
					msg = "Parámetros insuficientes para realizar la consulta. Por favor revise la documentación";
					break validation;
				} else if(exchange.getIn().getHeader("speriodo") == null||exchange.getIn().getHeader("speriodo").equals("")) {
					msg = "Parámetro speriodo inválido o inexistente";
					break validation;
				} else if(exchange.getIn().getHeader("snivel") == null||exchange.getIn().getHeader("snivel").equals("")) {
					msg = "Parámetro snivel inválido o inexistente";
					break validation;
				}
			}
		}
		if (msg != null)
			throw new IllegalArgumentException(msg);
	}
}
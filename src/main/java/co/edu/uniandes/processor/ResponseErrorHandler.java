package co.edu.uniandes.processor;

import co.edu.uniandes.model.MensajeOut;
import java.util.Properties;
import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ResponseErrorHandler
{
  @BeanInject("props")
  private Properties properties;
  
  public void process(Exchange exchange, @ExchangeProperty("estado") Boolean estado, @ExchangeProperty("message") String message)
    throws Exception
  {
    ResponseClass responseClass = new ResponseClass();
    MensajeOut mensajeOut = new MensajeOut();
    mensajeOut.setbOperacionExitosa(false);
    mensajeOut.setsMensajeRtaTecnico(message);
    mensajeOut.setsMensajeRtaUsuario(message);
    mensajeOut.setsCodigoRespuesta("http " + this.properties.getProperty("http.code.bad.request"));
    
    responseClass.setMensajeRta(mensajeOut);
    exchange.setProperty("mensajeOut", mensajeOut);
    String responsejson = new ObjectMapper().writeValueAsString(responseClass);
    exchange.setProperty("responseAudit", responsejson);
    exchange.getOut().setHeader("CamelAcceptContentType", "application/json; charset=UTF-8");
    exchange.getOut().setHeader("Content-Type", "application/json; charset=UTF-8");
    exchange.getOut().setHeader("CamelCxfRsResponseClass", ResponseClass.class);
    exchange.getOut().setBody(responseClass);
  }
  
  private class ResponseClass
  {
    @JsonProperty("MensajeOut")
    private MensajeOut mensajeRta;
    
    private ResponseClass() {}
    
    public MensajeOut getMensajeRta()
    {
      return this.mensajeRta;
    }
    
    public void setMensajeRta(MensajeOut mensajeRta)
    {
      this.mensajeRta = mensajeRta;
    }
  }
}

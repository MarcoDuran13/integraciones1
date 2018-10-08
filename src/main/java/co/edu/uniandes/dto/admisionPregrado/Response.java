package co.edu.uniandes.dto.admisionPregrado;

import co.edu.uniandes.model.MensajeOut;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize
public class Response
{
	  @JsonProperty("AdmisionPregrado")
	  private List<AdmisionPregrado> admisionPregrado;
	  @JsonProperty("MensajeOut")
	  private MensajeOut mensajeOut;
	  
	  public List<AdmisionPregrado> getAdmisionPregrado()
	  {
	    return this.admisionPregrado;
	  }
	  
	  public void setAdmisionPregrado(List<AdmisionPregrado> admisionPregrado)
	  {
	    this.admisionPregrado = admisionPregrado;
	  }
	  
	  public MensajeOut getMensajeOut()
	  {
	    return this.mensajeOut;
	  }
	  
	  public void setMensajeOut(MensajeOut mensajeOut)
	  {
	    this.mensajeOut = mensajeOut;
	  }
}

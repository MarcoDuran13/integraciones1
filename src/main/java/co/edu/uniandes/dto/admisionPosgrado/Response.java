package co.edu.uniandes.dto.admisionPosgrado;

import co.edu.uniandes.model.MensajeOut;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize
public class Response 
{
  @JsonProperty("AdmisionPosgrado")
  private List<AdmisionPosgrado> admisionPosgrado;
  @JsonProperty("MensajeOut")
  private MensajeOut mensajeOut;
  
  public List<AdmisionPosgrado> getAdmisionPosgrado()
  {
    return this.admisionPosgrado;
  }
  
  public void setAdmisionPosgrado(List<AdmisionPosgrado> admisionPosgrado)
  {
    this.admisionPosgrado = admisionPosgrado;
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

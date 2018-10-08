package co.edu.uniandes.dto.admisionExamen;

import co.edu.uniandes.model.MensajeOut;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Response
{
  @JsonProperty("Examen")
  private List<Examen> examen;
  @JsonProperty("MensajeOut")
  private MensajeOut mensajeOut;
  
  public List<Examen> getExamen()
  {
    return this.examen;
  }
  
  public void setExamen(List<Examen> examen)
  {
    this.examen = examen;
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

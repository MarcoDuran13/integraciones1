package co.edu.uniandes.dto.admisionPregrado;

import co.edu.uniandes.model.Estudiante;
import co.edu.uniandes.model.Inscripcion;
import co.edu.uniandes.model.Periodo;
import co.edu.uniandes.model.Programa;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AdmisionPregrado
{
  @JsonProperty("Estudiante")
  private Estudiante sEstudiante;
  @JsonProperty("Programa")
  private Programa sPrograma;
  @JsonProperty("Admitido")
  private String sAdmitido;
  @JsonProperty("Inscripcion")
  private Inscripcion sInscripcion;
  @JsonProperty("Periodo")
  private Periodo sPeriodo;
  
  public Programa getsPrograma()
  {
    return this.sPrograma;
  }
  
  public void setsPrograma(Programa sPrograma)
  {
    this.sPrograma = sPrograma;
  }
  
  public String getsAdmitido()
  {
    return this.sAdmitido;
  }
  
  public void setsAdmitido(String sAdmitido)
  {
    this.sAdmitido = sAdmitido;
  }
  
  public Inscripcion getsInscripcion()
  {
    return this.sInscripcion;
  }
  
  public void setsInscripcion(Inscripcion sInscripcion)
  {
    this.sInscripcion = sInscripcion;
  }
  
  public Estudiante getsEstudiante()
  {
    return this.sEstudiante;
  }
  
  public void setsEstudiante(Estudiante sEstudiante)
  {
    this.sEstudiante = sEstudiante;
  }
  
  public Periodo getsPeriodo()
  {
    return this.sPeriodo;
  }
  
  public void setsPeriodo(Periodo sPeriodo)
  {
    this.sPeriodo = sPeriodo;
  }
}

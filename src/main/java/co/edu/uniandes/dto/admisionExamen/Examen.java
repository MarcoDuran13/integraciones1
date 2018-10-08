package co.edu.uniandes.dto.admisionExamen;

import co.edu.uniandes.model.util.CanonicalDateDeserializer;
import co.edu.uniandes.model.util.CanonicalDateSerializer;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Examen
{
  @JsonProperty("SPidm")
  private int sPidm;
  @JsonProperty("SCodigoExamen")
  private String sCodigoExamen;
  @JsonProperty("STestScore")
  private String sTestScore;
  @JsonProperty("SNombreExamen")
  private String sNombreExamen;
  @JsonProperty("SPuesto")
  private String sPuesto;
  @JsonProperty("SPeriodo")
  private String sPeriodo;
  @JsonSerialize(using=CanonicalDateSerializer.class)
  @JsonDeserialize(using=CanonicalDateDeserializer.class)
  @JsonProperty("DFechaPresentacion")
  private Date dFechaPresentacion;
  @JsonProperty("SSNP")
  private String sSnp;
  @JsonSerialize(using=CanonicalDateSerializer.class)
  @JsonDeserialize(using=CanonicalDateDeserializer.class)
  @JsonProperty("DFechaActualizacion")
  private Date dFechaActualizacion;
  
  public int getsPidm()
  {
    return this.sPidm;
  }
  
  public void setsPidm(int sPidm)
  {
    this.sPidm = sPidm;
  }
  
  public String getsCodigoExamen()
  {
    return this.sCodigoExamen;
  }
  
  public void setsCodigoExamen(String sCodigoExamen)
  {
    this.sCodigoExamen = sCodigoExamen;
  }
  
  public String getsTestScore()
  {
    return this.sTestScore;
  }
  
  public void setsTestScore(String sTestScore)
  {
    this.sTestScore = sTestScore;
  }
  
  public String getsNombreExamen()
  {
    return this.sNombreExamen;
  }
  
  public void setsNombreExamen(String sNombreExamen)
  {
    this.sNombreExamen = sNombreExamen;
  }
  
  public String getsPuesto()
  {
    return this.sPuesto;
  }
  
  public void setsPuesto(String sPuesto)
  {
    this.sPuesto = sPuesto;
  }
  
  public String getsPeriodo()
  {
    return this.sPeriodo;
  }
  
  public void setsPeriodo(String sPeriodo)
  {
    this.sPeriodo = sPeriodo;
  }
  
  public String getsSnp()
  {
    return this.sSnp;
  }
  
  public void setsSnp(String sSnp)
  {
    this.sSnp = sSnp;
  }
  
  public Date getdFechaPresentacion()
  {
    return this.dFechaPresentacion;
  }
  
  public void setdFechaPresentacion(Date dFechaPresentacion)
  {
    this.dFechaPresentacion = dFechaPresentacion;
  }
  
  public Date getdFechaActualizacion()
  {
    return this.dFechaActualizacion;
  }
  
  public void setdFechaActualizacion(Date dFechaActualizacion)
  {
    this.dFechaActualizacion = dFechaActualizacion;
  }
}

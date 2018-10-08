package co.edu.uniandes.dto.admisionTipo;

import org.codehaus.jackson.annotate.JsonProperty;

import co.edu.uniandes.model.MensajeOut;

public class Response {

	@JsonProperty(value = "STipoAdmision")
	private String sTipoAdmision;
	@JsonProperty(value = "MensajeOut")
	private MensajeOut mensajeOut;

	public String getsTipoAdmision() {
		return sTipoAdmision;
	}

	public void setsTipoAdmision(String sTipoAdmision) {
		this.sTipoAdmision = sTipoAdmision;
	}

	public MensajeOut getMensajeOut() {
		return mensajeOut;
	}

	public void setMensajeOut(MensajeOut mensajeOut) {
		this.mensajeOut = mensajeOut;
	}
}

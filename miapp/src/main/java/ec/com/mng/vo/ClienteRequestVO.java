package ec.com.mng.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteRequestVO implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCliente;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombres;
	private String correo;
	private String telefono;
	private String provincia;
	private String ciudad;
	private String direccion;
}

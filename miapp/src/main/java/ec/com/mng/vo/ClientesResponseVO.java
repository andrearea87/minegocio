package ec.com.mng.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vo para retornar datos de clientes
 * 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientesResponseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCliente;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombres;
	private String correo;
	private String telefono;
	private String provincia;
	private String ciudad;
	private String direccion;
	
}

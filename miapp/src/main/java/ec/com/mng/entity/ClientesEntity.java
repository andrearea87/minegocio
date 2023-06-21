package ec.com.mng.entity;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "MNGTCLIENTES")
public class ClientesEntity {
	
	/**
	 * Clave primaria de la tabla clientes
	 * @return
	 */
	@Id
	@Column(name ="ID_CLIENTE")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	/**
	 * Tipo identificacion (CED,RUC)
	 */
	@Column(name ="TIPO_IDENTIFICACION")
	private String tipoIdentificacion;
	
	/**
	 * Numero identificacion cedula o RUC
	 */
	@Column(name ="NUMERO_IDENTIFICACION")
	private String numeroIdentificacion;
	
	/**
	 * Nombre completos del cliente
	 */
	@Column(name ="NOMBRE")
	private String nombreCliente;
	
	/**
	 * Direccion de correo electronico
	 */
	@Column(name ="CORREO")
	private String correo;
	
	/**
	 * Numero de telefono celular
	 */
	@Column(name ="CELULAR")
	private String celular;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteEntity")
	private Collection<DireccionesEntity> direccionesCliente;
}

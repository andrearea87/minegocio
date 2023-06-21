package ec.com.mng.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "MNGTDIRECCIONES")
public class DireccionesEntity {
	
	/**
	 * Clave primaria de la tabla direcciones
	 * @return
	 */
	@Id
	@Column(name ="ID_DIRECCION")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idDireccion;
	
	/**
	 * Clave foranea de la tabla clientes
	 */
	@Column(name ="ID_CLIENTE")
	private Long idCliente;
	
	/**
	 * Nombre de la provincia
	 */
	private String provincia;
	
	/**
	 * Nombre de la ciudad
	 */
	private String ciudad;
	
	/**
	 * Direccion del cliente
	 */
	private String direccion;
	
	/**
	 * Indentifica si la direccion es principal
	 */
	@Column(name ="ESDIRECCIONMATRIZ")
	private Boolean esDireccionMatriz;	
	
	/**
     * FK Clientes
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", insertable = false, updatable = false)
    private ClientesEntity clienteEntity;
}

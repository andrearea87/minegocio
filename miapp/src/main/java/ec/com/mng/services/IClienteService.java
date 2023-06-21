package ec.com.mng.services;
import java.util.Collection;

import ec.com.mng.vo.ClienteRequestVO;
import ec.com.mng.vo.ClientesResponseVO;

public interface IClienteService{
	
	/**
	 * Buscar clientes por cedula o nombre.
	 * @param cedula
	 * @param nombre
	 * @return
	 */
	Collection<ClientesResponseVO> listaClientes(String cedula, String nombre);
	
	/**
	 * Crear nuevo cliente.
	 * @param cliente
	 * @return
	 */
	void crearCliente(ClienteRequestVO cliente);
	
	/**
	 * Actualizar datos de cliente
	 * @param cliente
	 */
	void actualizarCliente(ClienteRequestVO cliente);
	
	/**
	 * Eliminar datos de cliente por codigo
	 * @param codigoCliente
	 */
	void eliminarCliente(Long codigoCliente);
}

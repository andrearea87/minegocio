package ec.com.mng.repository;
import java.util.Collection;

import ec.com.mng.vo.ClientesResponseVO;

public interface IClienteRepository{

	/**
	 * Buscar clientes por cedula o nombre
	 * @param cedula
	 * @param nombre
	 * @return
	 */
	Collection<ClientesResponseVO> listaClientes(String cedula, String nombre);
}

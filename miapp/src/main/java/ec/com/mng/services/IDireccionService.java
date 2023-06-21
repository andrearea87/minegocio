package ec.com.mng.services;
import java.util.Collection;

import ec.com.mng.entity.DireccionesEntity;
import ec.com.mng.vo.DireccionResponseVO;

public interface IDireccionService{
	
	/**
	 * Buscar direcciones por cliente
	 * @param codigoCliente
	 * @return
	 */
	Collection<DireccionResponseVO> listaDirecciones(Long codigoCliente);
	
	/**
	 * Crear nueva direccion.
	 * @param direccion
	 * @return
	 */
	void crearNuevaDireccion(DireccionesEntity direccion);
}

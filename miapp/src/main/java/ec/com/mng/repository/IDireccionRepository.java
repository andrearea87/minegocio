package ec.com.mng.repository;
import java.util.Collection;

import ec.com.mng.entity.DireccionesEntity;
import ec.com.mng.vo.DireccionResponseVO;

public interface IDireccionRepository{

	/**
	 * Buscar direcciones por cliente
	 * @param codigoCliente
	 * @return
	 */
	Collection<DireccionResponseVO> listaDirecciones(Long codigoCliente);
	
	/**
	 * Lista de direcciones por cliente para eliminar
	 * @param codigoCliente
	 * @return
	 */
	Collection<DireccionesEntity> listaDireccionesEliminar(Long codigoCliente);
}

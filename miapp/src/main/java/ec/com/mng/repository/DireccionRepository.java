package ec.com.mng.repository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.com.mng.entity.DireccionesEntity;
import ec.com.mng.exception.MiAppException;
import ec.com.mng.vo.DireccionResponseVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Repository para gestionar datos de direcciones
 *
 */
@Repository
public class DireccionRepository implements IDireccionRepository{

	/**
	 * Bean entity manager
	 */
	@Autowired
	EntityManager entityManager;
	
	/**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
	public Collection<DireccionResponseVO> listaDirecciones(Long codigoCliente){
    	try {
    	// Consulta para obtener direcciones por cliente
    	Query nativeQuery = entityManager.createNativeQuery("SELECT ID_CLIENTE, PROVINCIA, CIUDAD, DIRECCION, ESDIRECCIONMATRIZ FROM MNGTDIRECCIONES WHERE ID_CLIENTE = :pCodigoCliente");
    	nativeQuery.setParameter("pCodigoCliente", codigoCliente);
    	List<Object[]> results = nativeQuery.getResultList();
        // Retoramos nuestros datos convertidos a la estructura de datos de respuesta 
    	return results
              .stream()
              .map(result -> new DireccionResponseVO((Integer) result[0], (String) result[1], (String) result[2], (String) result[3], (Boolean) result[4]))
              .collect(Collectors.toList());
    	}catch (Exception e) {
    		throw new MiAppException("Error al consultar lista de direcciones", e);
		}
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<DireccionesEntity> listaDireccionesEliminar(Long codigoCliente){
    	try {
    		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    		CriteriaQuery<DireccionesEntity> criteriosBusqueda = cb.createQuery(DireccionesEntity.class);
    		Root<DireccionesEntity> cliente = criteriosBusqueda.from(DireccionesEntity.class);
    		List<Predicate> predicates = new ArrayList<>();
          	predicates.add(cb.equal(cliente.get("idCliente"), codigoCliente));
          	criteriosBusqueda.where(predicates.toArray(new Predicate[0]));
          	TypedQuery<DireccionesEntity> query = entityManager.createQuery(criteriosBusqueda);
          	return query.getResultList();
    	}catch (Exception e) {
    		throw new MiAppException("Error al consultar lista de direcciones", e);
		}
	}
   
}

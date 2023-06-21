package ec.com.mng.repository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.com.mng.exception.MiAppException;
import ec.com.mng.vo.ClientesResponseVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class ClienteRepository implements IClienteRepository{

	@Autowired
	EntityManager entityManager;
	
	/**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
	public Collection<ClientesResponseVO> listaClientes(String cedula, String nombre){
        try {
        	StringBuilder query = new StringBuilder();
        	query.append("SELECT A.ID_CLIENTE, A.TIPO_IDENTIFICACION, A.NUMERO_IDENTIFICACION, A.NOMBRE, A.CORREO, A.CELULAR, B.PROVINCIA, B.CIUDAD, B.DIRECCION FROM MNGTCLIENTES A ")
        	.append("INNER JOIN MNGTDIRECCIONES B ON B.ID_CLIENTE = A.ID_CLIENTE ")
        	.append("WHERE B.ESDIRECCIONMATRIZ = true ");
        	 if(cedula != null && cedula.trim() != "") {
        		 query.append(" AND A.NUMERO_IDENTIFICACION = '"+cedula+"' ");
             }
             if(nombre != null && nombre.trim() != "") {
            	 query.append(" AND A.NOMBRE LIKE '%"+nombre+"%'");
             }
        	
        	// Consulta para obtener direcciones por cliente
        	Query nativeQuery = entityManager.createNativeQuery(query.toString());
        	List<Object[]> results = nativeQuery.getResultList();
            // Retoramos nuestros datos convertidos a la estructura de datos de respuesta 
        	return results
                  .stream()
                  .map(result -> new ClientesResponseVO((Integer) result[0], (String) result[1], (String) result[2], (String) result[3], (String) result[4], (String) result[5], (String) result[6], (String) result[7], (String) result[8]))
                  .collect(Collectors.toList());
        	}catch (Exception e) {
        		throw new MiAppException("Error al consultar lista de direcciones", e);
    		}
	}
}

package ec.com.mng.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.mng.entity.DireccionesEntity;

public interface IDireccionConector extends JpaRepository<DireccionesEntity, Long>{

}

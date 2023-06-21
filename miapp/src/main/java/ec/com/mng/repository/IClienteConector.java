package ec.com.mng.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.mng.entity.ClientesEntity;

public interface IClienteConector extends JpaRepository<ClientesEntity, Long>{

}

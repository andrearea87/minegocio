package ec.com.mng.services;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.mng.entity.ClientesEntity;
import ec.com.mng.entity.DireccionesEntity;
import ec.com.mng.exception.MiAppException;
import ec.com.mng.repository.IClienteConector;
import ec.com.mng.repository.IClienteRepository;
import ec.com.mng.repository.IDireccionConector;
import ec.com.mng.repository.IDireccionRepository;
import ec.com.mng.vo.ClienteRequestVO;
import ec.com.mng.vo.ClientesResponseVO;

@Service
public class ClienteService implements IClienteService{

	@Autowired
    IClienteConector clienteConector;
	
	@Autowired
    IDireccionConector direccionConector;
	
	@Autowired
	IClienteRepository clienteRepository;
	
	@Autowired
	IDireccionRepository direccionRepository;
	
	
	/**
     * {@inheritDoc}
     */
    @Override
	public Collection<ClientesResponseVO> listaClientes(String cedula, String nombre){
    	return this.clienteRepository.listaClientes(cedula, nombre);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void crearCliente(ClienteRequestVO cliente) {
		// Validar si ya existe un cliente con el numero de cedula 
		Collection<ClientesResponseVO> busquedaCliente = this.clienteRepository.listaClientes(cliente.getNumeroIdentificacion(), null);
		if(busquedaCliente.isEmpty()) {
			// Guardamos el cliente
			ClientesEntity clientesEntity = new ClientesEntity();
			clientesEntity.setTipoIdentificacion(cliente.getTipoIdentificacion());
			clientesEntity.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
			clientesEntity.setNombreCliente(cliente.getNombres());
			clientesEntity.setCorreo(cliente.getCorreo());
			clientesEntity.setCelular(cliente.getTelefono());
			this.clienteConector.save(clientesEntity);
			// Guardamos la direccion
			DireccionesEntity direccionesEntity = new DireccionesEntity();
			direccionesEntity.setIdCliente(clientesEntity.getIdCliente());
			direccionesEntity.setProvincia(cliente.getProvincia());
			direccionesEntity.setCiudad(cliente.getCiudad());
			direccionesEntity.setDireccion(cliente.getDireccion());
			direccionesEntity.setEsDireccionMatriz(Boolean.TRUE);			
			this.direccionConector.save(direccionesEntity);
		}else {
			throw new MiAppException("El cliente con numero de identificacion "+cliente.getNumeroIdentificacion()+" ya existe.");
		}
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void actualizarCliente(ClienteRequestVO cliente) {
		// Consultar datos antiguos
		ClientesEntity datosCliente = clienteConector.findById(cliente.getIdCliente()).get();
		// Validar si ya existe un cliente con el numero de cedula que sea diferente al codigo del cliente actualizado
		Collection<ClientesResponseVO> busquedaCliente = this.clienteRepository.listaClientes(cliente.getNumeroIdentificacion(), null);
		Collection<ClientesResponseVO> clientesConCedulaIgual = busquedaCliente.stream().filter(clienteActual -> clienteActual.getIdCliente().intValue() != datosCliente.getIdCliente().intValue()).collect(Collectors.toList());
		if(clientesConCedulaIgual.isEmpty()) {
			// Guardamos el cliente
			ClientesEntity clientesEntity = new ClientesEntity();
			clientesEntity.setIdCliente(cliente.getIdCliente());
			clientesEntity.setTipoIdentificacion(cliente.getTipoIdentificacion());
			clientesEntity.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
			clientesEntity.setNombreCliente(cliente.getNombres());
			clientesEntity.setCorreo(cliente.getCorreo());
			clientesEntity.setCelular(cliente.getTelefono());
			this.clienteConector.save(clientesEntity);
			
		}else {
			throw new MiAppException("Los datos del cliente no se puede actualizar porque existe otro cliente con numero de identificacion "+cliente.getNumeroIdentificacion()+".");
		}
	}
	
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void eliminarCliente(Long codigoCliente) {
		// Consultar datos cliente
		Optional<ClientesEntity> datosCliente = clienteConector.findById(codigoCliente);
		if(datosCliente.isEmpty()) {
			throw new MiAppException("El cliente no se puede eliminar porque no existe un cliente con el id "+codigoCliente+".");
		}else {
			// Consultar direcciones para eliminar del cliente
			Collection<DireccionesEntity> listaDirecciones = direccionRepository.listaDireccionesEliminar(codigoCliente);
			if(!listaDirecciones.isEmpty()) {
				listaDirecciones.stream().forEach(direccion -> {
					this.direccionConector.delete(direccion);
				});
			}
			this.clienteConector.delete(datosCliente.get());
		}
	}
}

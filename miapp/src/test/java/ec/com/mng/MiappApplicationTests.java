package ec.com.mng;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ec.com.mng.entity.DireccionesEntity;
import ec.com.mng.services.IClienteService;
import ec.com.mng.services.IDireccionService;
import ec.com.mng.vo.ClienteRequestVO;
import ec.com.mng.vo.ClientesResponseVO;
import ec.com.mng.vo.DireccionResponseVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MiappApplicationTests {

	@Autowired(required=true)
	private IClienteService clienteService;
	
	@Autowired(required=true)
	private IDireccionService direccionService;
	
	@Test
	void contextLoads() {
	}
	
	// Fincionalida 1
	//@Test
	void testListaClientes() {
		Collection<ClientesResponseVO> listaClientes = clienteService.listaClientes(null, null);
		log.info("Tamaño lista:"+listaClientes.size());
	}
	
	//Funcionalidad 2
	//@Test
	void testCrearCliente() {
		//3. Invocar nuestro constructor
		ClienteRequestVO cliente = new ClienteRequestVO();
		//4. Asignamos valores...
	    cliente.setTipoIdentificacion("CED");
	    cliente.setNumeroIdentificacion("1005844632");
	    cliente.setNombres("RICARDO ALEX ANDRADE MARIN");
	    cliente.setCorreo("ricardo@hotmail.com");
	    cliente.setTelefono("0998542177");
	    cliente.setProvincia("COTOPAXI");
	    cliente.setCiudad("AMBATO");
	    cliente.setDireccion("CALLE GUARANDA Y LOMAS");
	    //5. Finalidad invocamos al método insertproduct
	    clienteService.crearCliente(cliente);
	}
	
	//Funcionalidad 3
	@Test
	void testActualizarCliente() {
		//3. Invocar nuestro constructor
		ClienteRequestVO cliente = new ClienteRequestVO();
		//4. Asignamos valores...
		cliente.setIdCliente(2L);
	    cliente.setTipoIdentificacion("CED");
	    cliente.setNumeroIdentificacion("1002937777");
	    cliente.setNombres("RICARDO ALEX ANDRADE MARIN");
	    cliente.setCorreo("ricardo@hotmail.com");
	    cliente.setTelefono("0998542177");
	    //5. Finalidad invocamos al método insertproduct
	    clienteService.actualizarCliente(cliente);
	}
	
	//Funcionalidad 4
	@Test
	void testEliminarCliente() {
		Long codigoCliente = 3L;
	    //5. Finalidad invocamos al método insertproduct
	    clienteService.eliminarCliente(codigoCliente);
	}
	
	// Funcionalidad 5
	//@Test
	void testCrearDirecciones() {
		DireccionesEntity direccion = new DireccionesEntity();
		direccion.setIdCliente(1L);
		direccion.setProvincia("PASTAZA");
		direccion.setCiudad("TENA");
		direccion.setDireccion("TENA Y DEL PELICANO");
		direccion.setEsDireccionMatriz(Boolean.TRUE);
		direccionService.crearNuevaDireccion(direccion);
	}
	
	// Funcionalidad 6
	//@Test
	void testListaDirecciones() {
		Long codigoCliente = 1L;
		Collection<DireccionResponseVO> listaDirecciones = direccionService.listaDirecciones(codigoCliente);
		log.info("Tamaño lista:"+listaDirecciones.size());
	}

}

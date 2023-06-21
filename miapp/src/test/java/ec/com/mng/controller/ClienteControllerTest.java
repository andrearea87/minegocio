package ec.com.mng.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.mng.entity.ClientesEntity;
import ec.com.mng.vo.ClienteRequestVO;
import ec.com.mng.vo.FiltrosBusquedaVO;


@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yaml")
@Import(TestConfiguration.class)
@DataJpaTest
public class ClienteControllerTest {

    ClientesEntity clientesEntity;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup() {
    	clientesEntity = ClientesEntity.builder()
            .idCliente(1L)
            .tipoIdentificacion("CED")
            .numeroIdentificacion("1752387504")
            .nombreCliente("ROSA MARIA ANDRADE LOPEZ")
            .correo("maria@hotmail.com")
            .celular("09954215488")
            .build();
    }


    @Test
    public void testListaClientes() throws Exception {
    	FiltrosBusquedaVO request = new FiltrosBusquedaVO();
    	request.setNumeroIdentificacion("1005487215");
        mockMvc.perform(post("/miNegocioWs/api/v1/mng/admin/clientes/listaClientes").contextPath("/miNegocioWs")
        		.content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testCrearCliente() throws Exception {
    	ClienteRequestVO request = new ClienteRequestVO();
    	request.setTipoIdentificacion("CED");
    	request.setNumeroIdentificacion("1752387504");
    	request.setNombres("ROSA MARIA ANDRADE LOPEZ");
    	request.setCorreo("maria@hotmail.com");
    	request.setTelefono("09954215488");
    	request.setProvincia("MANABI");
    	request.setCiudad("MANTA");
    	request.setDireccion("MALECON Y PASAJE");
        mockMvc.perform(post("/miNegocioWs/api/v1/mng/admin/clientes/crearCliente").contextPath("/miNegocioWs")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testActualizarCliente() throws Exception {
    	ClienteRequestVO request = new ClienteRequestVO();
    	request.setIdCliente(1L);
    	request.setTipoIdentificacion("CED");
    	request.setNumeroIdentificacion("1752387504");
    	request.setNombres("ROSA MARIA ANDRADE LOPEZ");
    	request.setCorreo("maria@hotmail.com");
    	request.setTelefono("09954215488");
        mockMvc.perform(post("/miNegocioWs/api/v1/mng/admin/clientes/actualizarCliente").contextPath("/miNegocioWs")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testEliminarCliente() throws Exception {
    	FiltrosBusquedaVO request = new FiltrosBusquedaVO();
    	request.setCodigoCliente(1L);
        mockMvc.perform(post("/miNegocioWs/api/v1/mng/admin/clientes/eliminarCliente").contextPath("/miNegocioWs")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

}

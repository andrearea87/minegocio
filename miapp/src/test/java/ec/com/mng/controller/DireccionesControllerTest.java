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

import ec.com.mng.entity.DireccionesEntity;
import ec.com.mng.vo.FiltrosBusquedaVO;


@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yaml")
@Import(TestConfiguration.class)
@DataJpaTest
public class DireccionesControllerTest {

	DireccionesEntity direccionesEntity;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup() {
    	direccionesEntity = DireccionesEntity.builder()
            .provincia("PICHINCHA")
            .ciudad("QUITO")
            .direccion("CRISTOBAL COLON Y OLMEDO")
            .esDireccionMatriz(Boolean.FALSE)
            .build();
    }


    @Test
    public void testListaDirecciones() throws Exception {
    	FiltrosBusquedaVO request = new FiltrosBusquedaVO();
    	request.setCodigoCliente(1L);
        mockMvc.perform(post("/miNegocioWs/api/v1/mng/admin/direcciones/listaDireccionesCliente").contextPath("/miNegocioWs")
        		.content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testCrearDireccion() throws Exception {
        mockMvc.perform(post("/miNegocioWs/api/v1/mng/admin/direcciones/guardarDireccion").contextPath("/miNegocioWs")
                .content(objectMapper.writeValueAsString(direccionesEntity))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
    
}

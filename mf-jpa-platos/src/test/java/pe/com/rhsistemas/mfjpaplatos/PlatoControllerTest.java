/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;

/**
 * @author Edwin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureMockMvc
public class PlatoControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatoControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectmapper;

	String apiRootPath;

	/**
	 * 
	 */
	public PlatoControllerTest() {
		// TODO Auto-generated constructor stub
	}
	
	//@Test
	public void consultarPlatos() throws UnsupportedEncodingException, Exception {
		apiRootPath = "/PlatoRJPAService";

		String resultado = mockMvc.perform(
				MockMvcRequestBuilders.get(apiRootPath + "/platos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value())).andReturn().getResponse().getContentAsString();
	}
	
	//@Test
	public void tamanioConsultarPlatos() throws UnsupportedEncodingException, Exception {
		apiRootPath = "/PlatoRJPAService";

		String resultado = mockMvc.perform(
				MockMvcRequestBuilders.get(apiRootPath + "/platos").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
		
		Gson g = new Gson();
		Map m = g.fromJson(resultado, Map.class);
		
		List lista = (List) m.get(Constantes.VALOR_DATA_MAP);
		
		Assertions.assertThat(lista.size() > 0);
	}

}

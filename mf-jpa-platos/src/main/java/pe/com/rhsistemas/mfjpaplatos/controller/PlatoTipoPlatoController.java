/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.BaseValor;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.post.dto.ListaPlatoDto;
import pe.com.rhsistemas.mfjpaplatos.dao.PlatoTipoPlatoRepository;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoIngrediente;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/PlatoTipoPlatoRJPAService")
public class PlatoTipoPlatoController {

	private static final Logger log = LoggerFactory.getLogger(PlatoTipoPlatoController.class);

	@Autowired
	private PlatoTipoPlatoRepository platoTipoPlatoRepository;
	
	@GetMapping(value = "/tipoPlatoPlato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> registrarPlato(@RequestParam(name = "listaPlatoDto", required = true) ListaPlatoDto listaPlatoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			List<PlatoIngrediente> lista = platoTipoPlatoRepository.findByPlatoTipoPlatoIn(listaPlatoDto.getListaPlatos());
			List<PlatoIngredienteDto> listaPlato = new ArrayList<>();
			for (PlatoIngrediente platoIngrediente : lista) {
				PlatoIngredienteDto dto = new PlatoIngredienteDto();
				dto.setIdPlato(platoIngrediente.getId().getIdPlato());
				IngredienteDto ingrediente = new IngredienteDto();
				ingrediente.setId(platoIngrediente.getId().getIdIngrediente());
				dto.setIngrediente(ingrediente);
				dto.setCantidad(platoIngrediente.getNuCantidad());
				BaseValor unidadMedida = new BaseValor();
				unidadMedida.setCodigo(platoIngrediente.getUnidadMedida().getIdUnidMedi().toString());
				unidadMedida.setNombre(platoIngrediente.getUnidadMedida().getDeUnidMedi());
				dto.setUnidadMedida(unidadMedida);
				BaseValor tipoIngrediente = new BaseValor();
				tipoIngrediente.setCodigo(platoIngrediente.getTiIngrediente());
				dto.setTipoIngrediente(tipoIngrediente);
				listaPlato.add(dto);
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Consulta completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaPlato);
			
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
			
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);
		
		return salida;
	}
}

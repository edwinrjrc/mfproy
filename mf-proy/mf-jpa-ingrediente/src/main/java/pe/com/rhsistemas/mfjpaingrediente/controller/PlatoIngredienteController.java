/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.BaseValor;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteExportDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaingrediente.dao.PlatoIngredienteRepository;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngredienteInter;
import pe.com.rhsistemas.mfjpaingrediente.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/PlatoIngredienteRJPAService")
public class PlatoIngredienteController {

	private static final Logger log = LoggerFactory.getLogger(PlatoIngredienteController.class);

	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;
	
	@PostMapping(value = "/ingredientes", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> registraIngredientesPlato(@RequestBody(required = true) List<PlatoIngredienteDto> listaIngredientePlatoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = null;
		Map<String, Object> mapeo = null;
		
		try {
			log.debug("Recibiendo parametros en registraIngredientesPlato PlatoIngredienteController");
			UtilMfDto.pintaLog(listaIngredientePlatoDto, "listaIngredientePlatoDto");
			
			List<PlatoIngrediente> entities = new ArrayList<PlatoIngrediente>();
			
			for (PlatoIngredienteDto platoIngredienteDto : listaIngredientePlatoDto) {
				entities.add(Utilmfjpa.parsePlatoIngredienteDto(platoIngredienteDto));
			}
			
			platoIngredienteRepository.saveAll(entities);
			estadoHttp = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		
		salida = new ResponseEntity<Map<String, Object>>(mapeo, estadoHttp);
		
		return salida;
	}
	
	@GetMapping(value = "/ingredientes/{idPlato}", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> consultaIngredientesPlato(@PathVariable Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = null;
		Map<String, Object> mapeo = null;
		
		try {
			log.debug("Recibiendo parametros consultaIngredientesPlato "+this.getClass().getName());
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			List<PlatoIngrediente> listaIngredientes = platoIngredienteRepository.findAllByPlato(idPlato);
			estadoHttp = HttpStatus.NO_CONTENT;
			mapeo = new HashMap<String, Object>();
			List<PlatoIngredienteDto> listaIngredientesDto = new ArrayList<>();
			if (UtilMfDto.listaNoVacia(listaIngredientes)) {
				estadoHttp = HttpStatus.OK;
				for (PlatoIngrediente entity : listaIngredientes) {
					listaIngredientesDto.add(Utilmfjpa.parsePlatoIngrediente(entity));
				}
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaIngredientesDto);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, estadoHttp);
		
		return salida;
	}
	
	@GetMapping(value = "/platoIngredientes/{idMenu}", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> consultaIngredientesPlatoMenu(@PathVariable Integer idMenu) {
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = null;
		Map<String, Object> mapeo = null;
		
		try {
			log.debug("Recibiendo parametros consultaIngredientesPlatoMenu "+this.getClass().getName());
			UtilMfDto.pintaLog(idMenu, "idMenu");
			estadoHttp = HttpStatus.NO_CONTENT;
			
			List<PlatoIngredienteInter> lista = platoIngredienteRepository.cantidadIngredientePlatoIngrediente(UtilMfDto.parseIntALong(idMenu));
			List<PlatoIngredienteExportDto> listaIngredienteExport = null;
			if (UtilMfDto.listaNoVacia(lista)) {
				listaIngredienteExport = new ArrayList<>();
				PlatoIngredienteExportDto dto = null;
				for (PlatoIngredienteInter platoIngreInter : lista) {
					dto = new PlatoIngredienteExportDto();
					dto.setTotalIngrediente(platoIngreInter.getTotalIngrediente());
					IngredienteDto ingredienteDto = new IngredienteDto();
					ingredienteDto.setId(platoIngreInter.getIdIngrediente());
					ingredienteDto.setNombreIngrediente(platoIngreInter.getDeIngrediente());
					dto.setIngrediente(ingredienteDto);
					BaseValor unidadMedidaDto = new BaseValor();
					unidadMedidaDto.setCodigo(platoIngreInter.getIdUnidadMedida().toString());
					unidadMedidaDto.setNombre(platoIngreInter.getDeUnidadMedida());
					dto.setUnidadMedida(unidadMedidaDto);
					listaIngredienteExport.add(dto);
				}
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaIngredienteExport);
			estadoHttp = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, estadoHttp);
		
		return salida;
	}
	
	
	@DeleteMapping(value = "/ingredientes", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> eliminarIngredientesPlato(@RequestBody Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = null;
		Map<String, Object> mapeo = null;
		
		try {
			log.debug("Recibiendo parametros eliminarIngredientesPlato "+this.getClass().getName());
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			this.platoIngredienteRepository.eliminarIngredientesPlato(idPlato);
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			estadoHttp = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, estadoHttp);
		
		return salida;
	}
}

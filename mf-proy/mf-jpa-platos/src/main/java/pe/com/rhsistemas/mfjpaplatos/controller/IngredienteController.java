package pe.com.rhsistemas.mfjpaplatos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaplatos.dao.IngredienteRepository;
import pe.com.rhsistemas.mfjpaplatos.dao.PlatoIngredienteRepository;
import pe.com.rhsistemas.mfjpaplatos.entity.Ingrediente;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

@RestController
@RequestMapping(value = "/mf-jpa-platos")
public class IngredienteController {

	private static final Logger log = LoggerFactory.getLogger(IngredienteController.class);

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;

	@PostMapping(value = "/ingrediente")
	public ResponseEntity<Map<String, Object>> registraIngrediente(@RequestBody IngredienteDto dto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.info("Parametros recibidos");
			UtilMfDto.pintaLog(dto, "ingredienteDto");

			ingredienteRepository.save(Utilmfjpa.parseIngredienteDto(dto));

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

	@DeleteMapping(value = "/ingrediente/{idIngrediente}")
	public ResponseEntity<Map<String, Object>> eliminarIngrediente(@PathVariable Integer idIngrediente) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(idIngrediente, "idIngrediente");

			Ingrediente entity = new Ingrediente();
			entity.setIdIngrediente(idIngrediente);
			ingredienteRepository.delete(entity);
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

	@PostMapping(value = "/ingredientes")
	public ResponseEntity<Map<String, Object>> registraIngredientes(@RequestBody List<IngredienteDto> lista) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(lista, "listaIngredientes");
			ingredienteRepository.saveAll(Utilmfjpa.parseListaIngredienteDto(lista));

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

	@GetMapping(value = "/ingredientes/{idPlato}")
	public ResponseEntity<Map<String, Object>> ingredientesPlato(@PathVariable Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(idPlato, "idPlato");

			List<PlatoIngrediente> listaPlatoIngredientes = platoIngredienteRepository.findAllByPlato(idPlato);

			status = HttpStatus.NO_CONTENT;
			List<PlatoIngredienteDto> listaIngredientes = new ArrayList<>();
			if (UtilMfDto.listaNoVacia(listaPlatoIngredientes)) {
				for (PlatoIngrediente platoIngrediente : listaPlatoIngredientes) {
					listaIngredientes.add(Utilmfjpa.parsePlatoIngrediente(platoIngrediente));
				}
			}

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaIngredientes);
			status = HttpStatus.OK;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
}

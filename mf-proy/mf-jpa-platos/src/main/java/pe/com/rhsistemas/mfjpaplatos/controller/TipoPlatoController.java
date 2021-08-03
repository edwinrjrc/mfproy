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
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.TipoPlatoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaplatos.dao.TipoPlatoRepository;
import pe.com.rhsistemas.mfjpaplatos.entity.TipoPlato;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/TipoPlatoRJPAService")
public class TipoPlatoController {

	private static final Logger log = LoggerFactory.getLogger(TipoPlatoController.class);

	@Autowired
	private TipoPlatoRepository tipoPlatoRepository;

	@GetMapping(value = "/tipoPlato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> listaTipoPlatos() {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			List<TipoPlato> listaTipos = tipoPlatoRepository.findAll();
			List<TipoPlatoDto> listaTipoPlato = null;

			status = HttpStatus.NO_CONTENT;

			if (UtilMfDto.listaNoVacia(listaTipos)) {
				listaTipoPlato = new ArrayList<>();

				for (TipoPlato entity : listaTipos) {
					listaTipoPlato.add(Utilmfjpa.parseTipoPlato(entity));
				}
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Consulta completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaTipoPlato);
			
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");

			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	
	@GetMapping(value = "/tipoPlatoFondo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> listaTipoPlatosFondo(){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			List<TipoPlato> listaTipos = tipoPlatoRepository.findByInFondo("S");
			List<TipoPlatoDto> listaTipoPlato = null;

			status = HttpStatus.NO_CONTENT;

			if (UtilMfDto.listaNoVacia(listaTipos)) {
				listaTipoPlato = new ArrayList<>();

				for (TipoPlato entity : listaTipos) {
					listaTipoPlato.add(Utilmfjpa.parseTipoPlato(entity));
				}
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Consulta completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaTipoPlato);
			
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");

			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@GetMapping(value = "/tipoPlatoEntrada", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> listaTipoPlatosEntrada(){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			List<TipoPlato> listaTipos = tipoPlatoRepository.findByInEntrada("S");
			List<TipoPlatoDto> listaTipoPlato = null;

			status = HttpStatus.NO_CONTENT;

			if (UtilMfDto.listaNoVacia(listaTipos)) {
				listaTipoPlato = new ArrayList<>();

				for (TipoPlato entity : listaTipos) {
					listaTipoPlato.add(Utilmfjpa.parseTipoPlato(entity));
				}
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Consulta completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaTipoPlato);
			
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");

			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
}

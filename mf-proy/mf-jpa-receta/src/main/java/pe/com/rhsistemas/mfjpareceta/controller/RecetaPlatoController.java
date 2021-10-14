/**
 * 
 */
package pe.com.rhsistemas.mfjpareceta.controller;

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
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpareceta.dao.RecetaRepository;
import pe.com.rhsistemas.mfjpareceta.entity.Receta;
import pe.com.rhsistemas.mfjpareceta.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/RecetaPlatoRJPAService")
public class RecetaPlatoController {

	private static final Logger log = LoggerFactory.getLogger(RecetaPlatoController.class);
	
	@Autowired
	private RecetaRepository recetaRepository;
	
	@GetMapping(value = "/receta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> consultarPlatos(@RequestParam(name = "idPlato", required = true) Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("Parametros recibidos");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			status = HttpStatus.NO_CONTENT;
			
			List<Receta> listaReceta = recetaRepository.findByIdPlato(idPlato);
			List<RecetaDto> listaRecetaDto = new ArrayList<>();
			if ( UtilMfDto.listaNoVacia(listaReceta) ) {
				for (Receta receta : listaReceta) {
					listaRecetaDto.add(Utilmfjpa.parseReceta(receta));
				}
			}
			
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaRecetaDto);

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

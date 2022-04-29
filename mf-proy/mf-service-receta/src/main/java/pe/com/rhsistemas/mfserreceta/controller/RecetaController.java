/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfserreceta.service.RecetaService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/recetaservice")
public class RecetaController {
	
	private static final Logger log = LoggerFactory.getLogger(RecetaController.class);
	
	@Autowired
	private RecetaService recetaService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/receta")
	public ResponseEntity<Map<String, Object>> guardarModificacionReceta(
			@RequestBody Map<String, Object> preparacionModificada) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarModificacionReceta");
			UtilMfDto.pintaLog(preparacionModificada, "preparacionModificada");
			
			Object preparacion = preparacionModificada.get("preparacionModificada");
			
			List<Map<String,Object>> listaPreparacion = (List<Map<String,Object>>) preparacion;

			RecetaDto recetaDto = null;
			List<RecetaDto> listaReceta = null;
			if (UtilMfDto.listaNoVacia(listaPreparacion)) {
				listaReceta = new ArrayList<>();
				for (Map<String, Object> map : listaPreparacion) {
					recetaDto = new RecetaDto();
					recetaDto.setIdPaso(UtilMfDto.parseStringAInteger(map.get("nroPaso").toString()));
					recetaDto.setDescripcionReceta(map.get("descripcionPaso").toString());
					recetaDto.setMinutosCompletar(UtilMfDto.parseStringADouble(map.get("tiempo").toString()));
					listaReceta.add(recetaDto);
				}
			}
			
			status = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/comentariosReceta")
	public ResponseEntity<Map<String, Object>> guardarComentarioReceta(
			@RequestBody RecetaComentarioPostDto recetaComentarioDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarComentarioReceta");
			UtilMfDto.pintaLog(recetaComentarioDto, "recetaComentarioDto");
			
			recetaComentarioDto.setFechaRegistro(UtilMfDto.hoyDate());
			recetaComentarioDto.setFechaModificacion(UtilMfDto.hoyDate());
			
			recetaService.guardarComentarioReceta(recetaComentarioDto);
			
			status = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/comentariosReceta/{idComentarioReceta}/{idUsuario}")
	public ResponseEntity<Map<String, Object>> eliminarComentarioReceta(@PathVariable Integer idComentarioReceta, @PathVariable Integer idUsuario) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en eliminarComentarioReceta");
			UtilMfDto.pintaLog(idComentarioReceta, "idComentarioReceta");
			UtilMfDto.pintaLog(idUsuario, "idUsuario");
			
			RecetaComentarioPostDto recetaComentarioDto = new RecetaComentarioPostDto();
			recetaComentarioDto.setIdComentarioPlato(idComentarioReceta);
			recetaComentarioDto.setIdUsuarioRegistro(idUsuario);
			recetaComentarioDto.setFechaRegistro(UtilMfDto.hoyDate());
			recetaComentarioDto.setFechaModificacion(UtilMfDto.hoyDate());
			
			recetaService.eliminarComentarioReceta(recetaComentarioDto);
			
			status = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/comentariosReceta/{idPlato}")
	public ResponseEntity<Map<String, Object>> consultarComentariosReceta(@PathVariable Integer idPlato){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarComentarioReceta");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			List<RecetaComentarioDto> listaComentarios = recetaService.consultarComentario(idPlato);
			
			status = HttpStatus.OK;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaComentarios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
}

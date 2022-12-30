/**
 * 
 */
package pe.com.rhsistemas.mfjpareceta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfjpareceta.dao.RecetaComentarioRepository;
import pe.com.rhsistemas.mfjpareceta.dao.RecetaRepository;
import pe.com.rhsistemas.mfjpareceta.dao.UsuarioPersonaRepository;
import pe.com.rhsistemas.mfjpareceta.entity.Receta;
import pe.com.rhsistemas.mfjpareceta.entity.RecetaComentario;
import pe.com.rhsistemas.mfjpareceta.entity.Usuario;
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
	@Autowired
	private RecetaComentarioRepository recetaComentarioRepository;
	@Autowired
	private UsuarioPersonaRepository usuarioPersonaRepository;
	
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
	
	
	@PostMapping(value = "/recetaComentario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> guardarComentario(@RequestBody RecetaComentarioPostDto recetaComentarioDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("Parametros recibidos");
			UtilMfDto.pintaLog(recetaComentarioDto, "recetaComentarioDto");
			status = HttpStatus.NO_CONTENT;
			
			log.info("fecha registro ::"+recetaComentarioDto.getFechaRegistro());
			
			recetaComentarioRepository.save(Utilmfjpa.parseRecetaComentarioDto(recetaComentarioDto));
			
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
	
	@GetMapping(value = "/recetaComentario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> listarComentarios(@RequestParam(name = "idPlato", required = true) Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("Parametros recibidos");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			status = HttpStatus.NO_CONTENT;
			
			List<RecetaComentario> listaComentario = recetaComentarioRepository.findByIdPlato(idPlato);
			List<RecetaComentarioDto> listaRecetaComentario = null;
			if (UtilMfDto.listaNoVacia(listaComentario)) {
				listaRecetaComentario = new ArrayList<>();
				
				for (RecetaComentario entity : listaComentario) {
					RecetaComentarioDto recetaComen = Utilmfjpa.parseRecetaComentario(entity);
					Optional<Usuario> usuarioOptional = usuarioPersonaRepository.findById(Long.valueOf(recetaComen.getIdUsuarioRegistro()));
					
					String nombreUsuario = usuarioOptional.get().getPersona().getPersonaNatural().getNoPersona()+" ";
					nombreUsuario = nombreUsuario + usuarioOptional.get().getPersona().getPersonaNatural().getTxPrimApel()+ " ";
					nombreUsuario = nombreUsuario + usuarioOptional.get().getPersona().getPersonaNatural().getTxSeguApel();
					
					nombreUsuario = StringUtils.normalizeSpace(nombreUsuario);
					
					recetaComen.setNombreUsuario(nombreUsuario);
					
					listaRecetaComentario.add(recetaComen);
				}
			}
			
			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaRecetaComentario);
			
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
	
	@DeleteMapping(value = "/recetaComentario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> eliminarComentario(@RequestBody RecetaComentarioPostDto recetaComentarioDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("Parametros recibidos eliminarComentario");
			UtilMfDto.pintaLog(recetaComentarioDto, "recetaComentarioDto");
			status = HttpStatus.NO_CONTENT;
			
			recetaComentarioRepository.delete(Utilmfjpa.parseRecetaComentarioDto(recetaComentarioDto));
			
			status = HttpStatus.OK;
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
	
	@PostMapping(value = "/recetaPreparacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> guardarComentario(@RequestBody List<RecetaDto> listaPreparacion) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("Parametros recibidos");
			UtilMfDto.pintaLog(listaPreparacion, "listaPreparacion");
			status = HttpStatus.NO_CONTENT;
			
			List<Receta> listaPreparacionEntity = new ArrayList<>();
			Iterator<RecetaDto> iteradorPreparacionDto = listaPreparacion.iterator();
			while (iteradorPreparacionDto.hasNext()) {
				listaPreparacionEntity.add(Utilmfjpa.parseRecetaDto(iteradorPreparacionDto.next()));
			}
			this.recetaRepository.saveAll(listaPreparacionEntity);
			
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
	
	@DeleteMapping(value = "/recetaPreparacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> eliminarRecetaPreparacion(@RequestBody Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("Parametros recibidos /recetaPreparacion");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			status = HttpStatus.NO_CONTENT;
			
			this.recetaRepository.deleteByPlato(idPlato);
			
			status = HttpStatus.OK;
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
}

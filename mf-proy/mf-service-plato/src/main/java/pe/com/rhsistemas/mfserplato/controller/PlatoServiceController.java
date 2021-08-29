/**
 * 
 */
package pe.com.rhsistemas.mfserplato.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.PlatoFavoritoPostDto;
import pe.com.rhsistemas.mfserplato.service.PlatoService;


/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/platoservice")
public class PlatoServiceController {
	
	private static final Logger log = LoggerFactory.getLogger(PlatoServiceController.class);
	
	@Autowired
	private PlatoService platoService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/platoFavorito")
	public ResponseEntity<Map<String, Object>> guardarPlatoFavorito(@RequestBody PlatoFavoritoPostDto platoFavoritoPostDto){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.info("recibiendo parametros en "+this.getClass().getName()+" en guardarPlatoFavorito");
			UtilMfDto.pintaLog(platoFavoritoPostDto, "platoFavoritoPostDto");
			
			PlatoFavoritoDto platoFavoritoDto = new PlatoFavoritoDto();
			platoFavoritoDto.setFechaModificacion(new Date());
			platoFavoritoDto.setFechaRegistro(new Date());
			platoFavoritoDto.setIdPersona(UtilMfDto.parseIntALong(platoFavoritoPostDto.getIdPersona()));
			platoFavoritoDto.setIdPlato(platoFavoritoPostDto.getIdPlato());
			platoFavoritoDto.setIdUsuarioModificacion(platoFavoritoPostDto.getIdPersona());
			platoFavoritoDto.setIdUsuarioRegistro(platoFavoritoPostDto.getIdPersona());
			
			platoService.guardarPlatoFavorito(platoFavoritoDto);
			
			status = HttpStatus.CREATED;
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

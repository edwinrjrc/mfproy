/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.PlatoFavoritoPkDto;
import pe.com.rhsistemas.mfjpaplatos.dao.PlatoFavoritoRepository;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoFavorito;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoFavoritoPK;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/PlatoFavoritoRJPAService")
public class PlatoFavoritoController {

	private static final Logger log = LoggerFactory.getLogger(PlatoFavoritoController.class);

	@Autowired
	private PlatoFavoritoRepository platoFavoritoRepository;

	@PostMapping(value = "/platoFavorito", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> guardarPlatoFavorito(@RequestBody PlatoFavoritoDto platoFavoritoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("recibiendo parametros en " + this.getClass().getName() + " en guardarPlatoFavorito");
			UtilMfDto.pintaLog(platoFavoritoDto, "platoFavoritoDto");
			
			Optional<PlatoFavorito> platoFavoritoOptional = platoFavoritoRepository.findById(Utilmfjpa.parseaPlatoFavoritoPk(platoFavoritoDto.getIdPersona(), platoFavoritoDto.getIdPlato()));
			
			
			if (platoFavoritoOptional.isPresent()) {
				if (StringUtils.equals("A", platoFavoritoOptional.get().getStPlatFavo()) ) {
					platoFavoritoDto.setEstadoPlatoFavorito("I");
				}
				else {
					platoFavoritoDto.setEstadoPlatoFavorito("A");
				}
				platoFavoritoDto.setFechaRegistro(platoFavoritoOptional.get().getFeRegistro());
			}
			else {
				platoFavoritoDto.setEstadoPlatoFavorito("A");
			}
			
			platoFavoritoRepository.save(Utilmfjpa.parsePlatoFavoritoDto(platoFavoritoDto));

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<>(mapeo, status);
		return salida;
	}

	@GetMapping(value = "/platoFavorito", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> consultarPlatoFavorito(
			@RequestParam(name = "listaPlatoFavorito", required = true) String listaPlatoFavorito,
			@RequestParam(name = "idPersona", required = true) String idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("recibiendo parametros en " + this.getClass().getName() + " en consultarPlatoFavorito");
			UtilMfDto.pintaLog(listaPlatoFavorito, "listaPlatoFavorito");
			UtilMfDto.pintaLog(idPersona, "idPersona");

			status = HttpStatus.NO_CONTENT;
			List<PlatoFavoritoPkDto> listaEnviada = new ArrayList<>();
			String[] listaFavori = StringUtils.split(listaPlatoFavorito, ",");
			for (String idPlato : listaFavori) {
				listaEnviada.add( new PlatoFavoritoPkDto(UtilMfDto.parseStringAInteger(idPlato), UtilMfDto.parseStringAInteger(idPersona)) );
			}

			List<PlatoFavoritoPK> listaFavoritos = new ArrayList<>();

			if (UtilMfDto.listaNoVacia(listaEnviada)) {
				for (PlatoFavoritoPkDto platoFavoritoPkDto : listaEnviada) {
					listaFavoritos.add(Utilmfjpa.parseaPlatoFavoritoPk(platoFavoritoPkDto));
				}
				List<PlatoFavorito> listaFavo = this.platoFavoritoRepository.findAllById(listaFavoritos);
				List<PlatoFavoritoDto> listaFavoritosDto = new ArrayList<>();
				if (UtilMfDto.listaNoVacia(listaFavo)) {
					for (PlatoFavorito platoFavorito : listaFavo) {
						if ( StringUtils.equals("A",  platoFavorito.getStPlatFavo()) ) {
							listaFavoritosDto.add(Utilmfjpa.parseaPlatoFavorito(platoFavorito));
						}
					}
				}
				mapeo = new HashMap<String, Object>();
				mapeo.put("error", false);
				mapeo.put("mensaje", "Operacion Completada");
				mapeo.put(Constantes.VALOR_DATA_MAP, listaFavoritosDto);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}

		salida = new ResponseEntity<>(mapeo, status);
		return salida;
	}
	
	@GetMapping(value = "/platosFavoritos", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> consultarPlatosFavoritos(
			@RequestParam(name = "idPersona", required = true) String idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("recibiendo parametros en " + this.getClass().getName() + " en consultarPlatosFavoritos");
			UtilMfDto.pintaLog(idPersona, "idPersona");

			status = HttpStatus.NO_CONTENT;
			
			List<PlatoFavorito> listaFavoritos = platoFavoritoRepository.findByIdPersona(UtilMfDto.parseStringALong(idPersona));

			if (UtilMfDto.listaNoVacia(listaFavoritos)) {
				List<PlatoFavoritoDto> listaFavoritosDto = new ArrayList<>();
				for (PlatoFavorito platoFavorito : listaFavoritos) {
					listaFavoritosDto.add(Utilmfjpa.parseaPlatoFavorito(platoFavorito));
				}
				mapeo = new HashMap<String, Object>();
				mapeo.put("error", false);
				mapeo.put("mensaje", "Operacion Completada");
				mapeo.put(Constantes.VALOR_DATA_MAP, listaFavoritosDto);
				status = HttpStatus.OK;
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}

		salida = new ResponseEntity<>(mapeo, status);
		return salida;
	}
}

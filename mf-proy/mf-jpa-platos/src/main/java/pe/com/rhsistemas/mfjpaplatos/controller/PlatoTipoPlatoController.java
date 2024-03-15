/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.controller;

import java.util.ArrayList;
import java.util.Date;
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
import pe.com.rhsistemas.mf.cross.dto.PlatoTipoPlatoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaplatos.dao.PlatoRepository;
import pe.com.rhsistemas.mfjpaplatos.dao.PlatoTipoPlatoRepository;
import pe.com.rhsistemas.mfjpaplatos.entity.Plato;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoTipoPlato;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

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
	@Autowired
	private PlatoRepository platoRepository;
	
	/**
	 * 
	 * @param idPersona
	 * @param fechaCorteDesde
	 * @param fechaCorteHasta
	 * @return
	 */
	@GetMapping(value = "/tipoPlatoPlato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> consultarTiposPlato(
			@RequestParam(name = "idPersona", required = true) Integer idPersona,
			@RequestParam(name = "fechaCorteDesde", required = true) Date fechaCorteDesde,
			@RequestParam(name = "fechaCorteHasta", required = true) Date fechaCorteHasta) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.debug("recibiendo parametros consultarTiposPlato "+this.getClass().getName());
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");
			
			List<PlatoTipoPlatoDto> listaTipoPlato = new ArrayList<>();
			
			List<Plato> platos = platoRepository.platosNoConsumidos(idPersona,
					UtilMfDto.convertirUtilDateASqlDate(fechaCorteDesde), UtilMfDto.convertirUtilDateASqlDate(fechaCorteHasta));
			List<Integer> platosInt = new ArrayList<>();
			for (Plato plato : platos) {
				platosInt.add(plato.getIdPlato());
			}
			
			status = HttpStatus.NO_CONTENT;
			List<PlatoTipoPlato> lista = platoTipoPlatoRepository.findByPlatoTipoPlatoIn(platosInt);
			
			if (UtilMfDto.listaNoVacia(lista)) {
				for (PlatoTipoPlato entity : lista) {
					listaTipoPlato.add(Utilmfjpa.parsePlatoTipoPlato(entity));
				}
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Consulta completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaTipoPlato);
			
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

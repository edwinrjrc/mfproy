/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.util.UtilMf;
import pe.com.rhsistemas.mfjpamenu.dao.MenuRepository;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.Persona;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/MenuRJPAService")
public class MenuController {
	
	private static final Logger log = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuRepository menuRepository;
	
	@GetMapping(value = "/ultimoMenu")
	public ResponseEntity<Map<String, Object>> consultarUltimoMenu(@RequestParam(name = "idPersona", required = true) Integer idPersona, @RequestParam(name = "fechaRango", required = true) Date fechaRango) {
		ResponseEntity<Map<String, Object>> salida = null;
		log.debug("Parametros recibidos");
		UtilMf.pintaLog(idPersona,"idPersona");
		try {
			Persona persona = new Persona();
			persona.setIdPersona(Long.valueOf(idPersona));
			List<MenuDetalle> listaMenus = menuRepository.ultimosMenusRango(persona,null);
			List<MenuDetalleDto> listaMenuDto = new ArrayList<>();
			MenuDetalleDto menuDetalleDto = null;
			for (MenuDetalle menuDetalle : listaMenus) {
				menuDetalleDto = new MenuDetalleDto();
				menuDetalleDto.setFechaConsumo(menuDetalle.getFeConsumo());
				menuDetalleDto.setIdPlato(menuDetalle.getPlato().getIdPlato());
				menuDetalleDto.setFechaRegistro(menuDetalle.getFeRegistro());
				menuDetalleDto.setIdUsuarioRegistro(menuDetalle.getIdUsuaCrea());
				listaMenuDto.add(menuDetalleDto);
			}
			Map<String, Object> mapeo = new HashMap<String, Object>();
			mapeo.put(Constantes.VALOR_DATA_MAP, listaMenuDto);
			salida = new ResponseEntity<>(mapeo, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.debug("salida metodo :"+salida.toString());
		return salida;
	}
	
	public ResponseEntity<Map<String, Object>> registrarMenu(@RequestBody List<MenuDetalle> detalleMenuGenerado){
		ResponseEntity<Map<String, Object>> salida = null;
		log.debug("Parametros recibidos");
		UtilMf.pintaLog(detalleMenuGenerado,"detalleMenuGenerado");
		
		return salida;
	}
	
}

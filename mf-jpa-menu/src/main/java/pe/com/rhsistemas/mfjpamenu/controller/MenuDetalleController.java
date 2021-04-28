/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpamenu.dao.MenuDetalleRepository;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/MenuDetalleRJPAService")
public class MenuDetalleController {

	private static final Logger log = LoggerFactory.getLogger(MenuDetalleController.class);

	@Autowired
	private MenuDetalleRepository menuDetalleRepository;

	@GetMapping(value = "/menuDetalle/{idMenuGenerado}")
	public ResponseEntity<Map<String, Object>> consultarDetalleMenu(@PathVariable Long idMenuGenerado) {
		log.info("Recibiendo parametros");
		UtilMfDto.pintaLog(idMenuGenerado, "idMenuGenerado");
		
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = HttpStatus.NOT_FOUND;
		
		MenuGenerado menuGenerado = new MenuGenerado();
		menuGenerado.setIdGenerado(idMenuGenerado);
		
		Map<String, Object> mapeo = null;
		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
		
		List<MenuDetalle> listaDetalle = menuDetalleRepository.findByMenuGenerado(menuGenerado);
		for (MenuDetalle menuDetalle : listaDetalle) {
			listaMenuDetalle.add(Utilmfjpa.parseMenuDetalle(menuDetalle));
		}
		
		if (UtilMfDto.listaNoVacia(listaMenuDetalle)) {
			estadoHttp = HttpStatus.FOUND;
			mapeo = new HashMap<String, Object>();
			mapeo.put(Constantes.VALOR_DATA_MAP, listaMenuDetalle);
		}
		
		salida = new ResponseEntity<>(mapeo,estadoHttp);

		return salida;
	}
}

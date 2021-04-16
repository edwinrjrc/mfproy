package pe.com.rhsistemas.mfjpamenu.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.entity.Persona;
import pe.com.rhsistemas.mfjpamenu.entity.Plato;

public class Utilmfjpa {

	public Utilmfjpa() {
		// TODO Auto-generated constructor stub
	}
	
	public static MenuGenerado parsePlatoDto(MenuGeneradoDto dto) {
		MenuGenerado entity = new MenuGenerado();
		if (dto != null) {
			entity.setFeGenerado(dto.getFechaGenerado());
			entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
			entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			Persona persona = new Persona();
			persona.setIdPersona(Long.valueOf(dto.getIdPersona()));
			entity.setPersona(persona);
			List<MenuDetalle> menuDetalles = new ArrayList<>();
			MenuDetalle menuDetalle = null;
			for (MenuDetalleDto detaDto : dto.getListaPlatos()) {
				menuDetalle = new MenuDetalle();
				menuDetalle.setFeConsumo(detaDto.getFechaConsumo());
				menuDetalle.setFeModificacion(new Timestamp(System.currentTimeMillis()));
				menuDetalle.setFeRegistro(new Timestamp(System.currentTimeMillis()));
				menuDetalle.setIdUsuaCrea(detaDto.getIdUsuarioRegistro());
				menuDetalle.setIdUsuaModi(detaDto.getIdUsuarioModificacion());
				menuDetalle.setMenuGenerado(entity);
				Plato plato = new Plato();
				plato.setIdPlato(detaDto.getIdPlato());
				menuDetalle.setPlato(plato);
				menuDetalles.add(menuDetalle);
			}
			entity.setMenuDetalles(menuDetalles);
			entity.setNuDias(dto.getNumeroDias());
		}
		
		return entity;
	}

}

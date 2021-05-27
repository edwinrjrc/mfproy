package pe.com.rhsistemas.mfjpamenu.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetallePK;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.entity.Persona;

public class Utilmfjpa {

	
	public static MenuGenerado parseMenuDto(MenuGeneradoDto dto) {
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
				menuDetalle = parseaMenuDetalleDto(detaDto);
				menuDetalle.setMenuGenerado(entity);
				menuDetalles.add(menuDetalle);
			}
			entity.setMenuDetalles(menuDetalles);
			entity.setNuDias(dto.getNumeroDias());
		}
		
		return entity;
	}
	
	public static MenuGeneradoDto parseMenuGenerado(MenuGenerado entity) {
		MenuGeneradoDto dto = new MenuGeneradoDto();
		
		dto = new MenuGeneradoDto();
		dto.setFechaGenerado(entity.getFeGenerado());
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.setIdGenerado(entity.getIdGenerado());
		dto.setIdPersona(entity.getPersona().getIdPersona().intValue());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaModi());
		dto.setNumeroDias(entity.getNuDias());
		if (UtilMfDto.listaNoVacia(entity.getMenuDetalles())) {
			List<MenuDetalleDto> menuDetalles = new ArrayList<>();
			MenuDetalleDto menuDetalleDto = null;
			for (MenuDetalle detaEntity : entity.getMenuDetalles()) {
				menuDetalleDto = new MenuDetalleDto();
				
				menuDetalleDto.setFechaConsumo(detaEntity.getId().getFeConsumo());
				menuDetalleDto.setFechaModificacion(detaEntity.getFeModificacion());
				menuDetalleDto.setFechaRegistro(detaEntity.getFeRegistro());
				menuDetalleDto.getPlatoDto().setId(detaEntity.getId().getIdPlato());
				menuDetalleDto.setIdUsuarioModificacion(detaEntity.getIdUsuaModi());
				menuDetalleDto.setIdUsuarioRegistro(detaEntity.getIdUsuaCrea());

				menuDetalles.add(menuDetalleDto);
			}
			dto.setListaPlatos(menuDetalles);
		}
		entity.setNuDias(dto.getNumeroDias());
		
		return dto;
	}
	
	public static MenuDetalleDto parseMenuDetalle(MenuDetalle entity) {
		MenuDetalleDto dto = new MenuDetalleDto();
		
		dto.setFechaConsumo(entity.getId().getFeConsumo());
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.getPlatoDto().setId(entity.getId().getIdPlato());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		
		return dto;
	}
	
	public static MenuDetalle parseaMenuDetalleDto(MenuDetalleDto detaDto) {
		MenuDetalle menuDetalle = new MenuDetalle();
		MenuDetallePK menuDetalleId = new MenuDetallePK();
		menuDetalleId.setFeConsumo(detaDto.getFechaConsumo());
		menuDetalleId.setIdPlato(detaDto.getPlatoDto().getId());
		
		menuDetalle.setId(menuDetalleId);
		menuDetalle.setFeModificacion(new Timestamp(System.currentTimeMillis()));
		menuDetalle.setFeRegistro(new Timestamp(System.currentTimeMillis()));
		menuDetalle.setIdUsuaCrea(detaDto.getIdUsuarioRegistro());
		menuDetalle.setIdUsuaModi(detaDto.getIdUsuarioModificacion());
		
		return menuDetalle;
	}

}

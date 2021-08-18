package pe.com.rhsistemas.mfjpamenu.util;

import java.sql.Timestamp;
import java.util.Date;

import pe.com.rhsistemas.mf.cross.dto.BaseValor;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetallePK;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.entity.PlatoIngrediente;

public class Utilmfjpa {
	
	public static MenuGenerado parseMenuDto(MenuGeneradoDto dto) {
		MenuGenerado entity = new MenuGenerado();
		if (dto != null) {
			entity.setFeGenerado(UtilMfDto.parseDateASqlTimestamp(dto.getFechaGenerado()));
			entity.setFeDesde(UtilMfDto.parseDateASqlTimestamp(dto.getFechaDesde()));
			entity.setFeHasta(UtilMfDto.parseDateASqlTimestamp(dto.getFechaHasta()));
			entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
			entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setIdPersona(UtilMfDto.parseIntALong(dto.getIdPersona()));
			entity.setIdGenerado(dto.getIdGenerado());
			
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
		dto.setIdPersona(entity.getIdPersona().intValue());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaModi());
		dto.setNumeroDias(entity.getNuDias());
		entity.setNuDias(dto.getNumeroDias());
		
		return dto;
	}
	
	public static MenuDetalleDto parseMenuDetalle(MenuDetalle entity) {
		MenuDetalleDto dto = new MenuDetalleDto();
		
		dto.setFechaConsumo(entity.getId().getFeConsumo());
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.getPlatoDto().setId(entity.getIdPlato());
		dto.setIdTipoPlato(entity.getIdTipoPlato());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		
		return dto;
	}
	
	public static MenuDetalle parseaMenuDetalleDto(MenuDetalleDto detaDto) {
		MenuDetalle menuDetalle = new MenuDetalle();
		menuDetalle.setFeModificacion(new Timestamp(System.currentTimeMillis()));
		menuDetalle.setFeRegistro(new Timestamp(System.currentTimeMillis()));
		menuDetalle.setIdPlato(detaDto.getPlatoDto().getId());
		menuDetalle.setIdTipoPlato(detaDto.getIdTipoPlato());
		menuDetalle.setIdUsuaCrea(detaDto.getIdUsuarioRegistro());
		menuDetalle.setIdUsuaModi(detaDto.getIdUsuarioModificacion());
		MenuDetallePK detapk = new MenuDetallePK();
		detapk.setFeConsumo(detaDto.getFechaConsumo());
		detapk.setIdGenerado(Long.valueOf(detaDto.getIdGenerado()));
		menuDetalle.setId(detapk);
		
		return menuDetalle;
	}
	
	public static PlatoIngredienteDto parsePlatoIngrediente(PlatoIngrediente entity) {
		PlatoIngredienteDto dto = new PlatoIngredienteDto();
		dto.setIdPlato(entity.getId().getIdPlato());
		IngredienteDto ingrediente = new IngredienteDto();
		ingrediente.setId(entity.getId().getIdIngrediente());
		dto.setIngrediente(ingrediente);
		dto.setCantidad(entity.getNuCantidad());
		BaseValor unidadMedida = new BaseValor();
		unidadMedida.setCodigo(entity.getUnidadMedida().getIdUnidMedi().toString());
		unidadMedida.setNombre(entity.getUnidadMedida().getDeUnidMedi());
		dto.setUnidadMedida(unidadMedida);
		BaseValor tipoIngrediente = new BaseValor();
		tipoIngrediente.setCodigo(entity.getTiIngrediente());
		dto.setTipoIngrediente(tipoIngrediente);
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaRegistro(entity.getFeRegistro());
		return dto;
	}

}

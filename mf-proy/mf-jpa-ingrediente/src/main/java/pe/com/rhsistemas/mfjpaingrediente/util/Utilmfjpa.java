/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import pe.com.rhsistemas.mf.cross.dto.BaseValor;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PasoPreparacionDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mf.cross.dto.ValorNutricionalDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaingrediente.entity.Ingrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.Plato;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngredientePK;
import pe.com.rhsistemas.mfjpaingrediente.entity.Receta;
import pe.com.rhsistemas.mfjpaingrediente.entity.RecetaPK;
import pe.com.rhsistemas.mfjpaingrediente.entity.TipoCocina;
import pe.com.rhsistemas.mfjpaingrediente.entity.TipoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.UnidadMedida;
import pe.com.rhsistemas.mfjpaingrediente.entity.ValorNutricional;
import pe.com.rhsistemas.mfjpaingrediente.entity.ValorNutricionalPK;

/**
 * @author Edwin
 *
 */
public class Utilmfjpa {

	public static Plato parsePlatoDto(PlatoDto dto) throws UtilMfDtoException {
		Plato entity = new Plato();
		if (dto != null && !StringUtils.isEmpty(dto.getNombrePlato())) {
			entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
			entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
			entity.setIdEstaPlat(dto.getIdEstado());
			entity.setIdPersona(dto.getPersona().getId());
			entity.setIdPlato(dto.getId());
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setInAcompaniamiento(dto.isAcompaniamiento()?"S":"N");
			entity.setNoPlato(dto.getNombrePlato());
			TipoCocina tipoCocina = new TipoCocina();
			tipoCocina.setIdTipoCoci(UtilMfDto.parseStringAInteger(dto.getTipoCocina().getCodigo()));
			entity.setTipoCocina(tipoCocina);
		}
		
		return entity;
	}
	
	public static PlatoDto parsePlatoEntity(Plato entity) {
		PlatoDto dto = new PlatoDto();
		if (entity != null) {
			dto.setAcompaniamiento("S".equalsIgnoreCase(entity.getInAcompaniamiento()));
			dto.setFechaModificacion(entity.getFeModificacion());
			dto.setFechaRegistro(entity.getFeRegistro());
			dto.setId(UtilMfDto.parseaNullInt(entity.getIdPlato()));
			dto.setIdEstado(UtilMfDto.parseaNullInt(entity.getIdEstaPlat()));
			dto.setIdUsuarioModificacion(UtilMfDto.parseaNullInt(entity.getIdUsuaModi()));
			dto.setIdUsuarioRegistro(UtilMfDto.parseaNullInt(entity.getIdUsuaCrea()));
			dto.setNombrePlato(entity.getNoPlato());
			PersonaDto persona = new PersonaDto();
			persona.setId(UtilMfDto.parseaNullLong(entity.getIdPersona()));
			dto.setPersona(persona);
			BaseValor tipoCocinaV = new BaseValor();
			tipoCocinaV.setCodigo(entity.getTipoCocina().getIdTipoCoci().toString());
			dto.setTipoCocina(tipoCocinaV);
		}
		
		return dto;
	}
	
	public static Ingrediente parseIngredienteDto(IngredienteDto dto) {
		Ingrediente entity = new Ingrediente();
		
		if (dto != null && !StringUtils.isEmpty(dto.getNombreIngrediente())) {
			entity.setDeIngrediente(dto.getNombreIngrediente());
			entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
			entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
			entity.setIdIngrediente(dto.getId());
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setNuCalorias(Double.valueOf(dto.getNumeroCalorias()).floatValue());
			entity.setNuGrasas(Double.valueOf(dto.getNumeroGrasas()).floatValue());
			entity.setNuProteina(Double.valueOf(dto.getNumeroProteina()).floatValue());
			TipoIngrediente tipoIngrediente = new TipoIngrediente();
			
			tipoIngrediente.setIdTipoIngr(Integer.valueOf(dto.getTipoIngrediente().getCodigo()));
			tipoIngrediente.setNoTipoIngr(dto.getTipoIngrediente().getValor());
			tipoIngrediente.setIdUsuaRegi(dto.getIdUsuarioRegistro());
			tipoIngrediente.setIdUsuaModi(dto.getIdUsuarioModificacion());
			tipoIngrediente.setFeRegistro(new Timestamp(System.currentTimeMillis()));
			tipoIngrediente.setFeModificacion(new Timestamp(System.currentTimeMillis()));
			entity.setTipoIngrediente(tipoIngrediente);
		}
		
		return entity;
	}
	
	public static IngredienteDto parseIngrediente(Ingrediente entity) {
		IngredienteDto dto = new IngredienteDto();
		
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.setId(entity.getIdIngrediente());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		dto.setNombreIngrediente(entity.getDeIngrediente());
		dto.setNumeroCalorias(entity.getNuCalorias());
		dto.setNumeroGrasas(entity.getNuGrasas());
		dto.setNumeroProteina(entity.getNuProteina());
		
		BaseValor tipoIngrediente = new BaseValor();
		tipoIngrediente.setCodigo(entity.getTipoIngrediente().getIdTipoIngr().toString());
		tipoIngrediente.setNombre(entity.getTipoIngrediente().getNoTipoIngr());
		tipoIngrediente.setValor(entity.getTipoIngrediente().getNoTipoIngr());
		
		dto.setTipoIngrediente(tipoIngrediente);
		
		return dto;
	}
	
	
	public static List<Ingrediente> parseListaIngredienteDto(List<IngredienteDto> lista) {
		List<Ingrediente> listaSalida = new ArrayList<>();
		for (IngredienteDto dto : lista) {
			if (dto != null) {
				listaSalida.add(parseIngredienteDto(dto));
			}
		}
		
		return listaSalida;
	}
	
	public static List<PlatoIngrediente> parseListaPlatoIngrediente(List<PlatoIngredienteDto> lista) throws UtilMfDtoException{
		List<PlatoIngrediente> listaSalida = new ArrayList<>();
		
		for (PlatoIngredienteDto dto : lista) {
			if (dto != null) {
				listaSalida.add(parsePlatoIngredienteDto(dto));
			}
		}
		
		return listaSalida;
		
	}
	
	public static PlatoIngrediente parsePlatoIngredienteDto(PlatoIngredienteDto dto) throws UtilMfDtoException {
		PlatoIngrediente entity = new PlatoIngrediente();
		
		if (dto != null) {
			entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
			entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
			
			PlatoIngredientePK entityPk = new PlatoIngredientePK();
			entityPk.setIdIngrediente(dto.getIngrediente().getId());
			entityPk.setIdPlato(dto.getIdPlato());
			entity.setId(entityPk);
			UnidadMedida unidadMedida = new UnidadMedida();
			unidadMedida.setIdUnidMedi(UtilMfDto.parseStringAInteger(dto.getUnidadMedida().getCodigo()));
			entity.setUnidadMedida(unidadMedida);
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setNuCantidad(Double.valueOf(dto.getCantidad()).floatValue());
			entity.setNuOrden(dto.getNumeroOrden());
			if (dto.getTipoIngrediente() != null && StringUtils.isNotBlank(dto.getTipoIngrediente().getCodigo()) ) {
				entity.setTiIngrediente(dto.getTipoIngrediente().getCodigo());
			}
		}
		
		return entity;
	}
	
	public static List<Receta> parseListaPasoPreparacionDto(List<PasoPreparacionDto> lista){
		List<Receta> listaSalida = new ArrayList<Receta>();
		for (PasoPreparacionDto dto : lista) {
			listaSalida.add(parsePasoPreparacionDto(dto));
		}
		return listaSalida;
	}
	
	public static Receta parsePasoPreparacionDto(PasoPreparacionDto dto) {
		Receta entity = new Receta();
		
		entity.setDePasoRece(dto.getDescripcioPaso());
		entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
		entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
		
		RecetaPK entityPk = new RecetaPK();
		entityPk.setIdPaso(dto.getIdPaso());
		entityPk.setIdPlato(dto.getIdPlato());
		entity.setId(entityPk);
		
		entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
		entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
		
		entity.setInPasoCocc(dto.isUsaFuego()?1:0);
		entity.setNuMinuComp(dto.getTiempoMinutos());
		Plato plato = new Plato();
		plato.setIdPlato(dto.getIdPlato());
		
		return entity;
	}
	
	public static ValorNutricional parseValorNutricionalDto(ValorNutricionalDto dto) {
		ValorNutricional entity = new ValorNutricional();
		
		entity.setFeModificacion(new Timestamp(System.currentTimeMillis()));
		entity.setFeRegistro(new Timestamp(System.currentTimeMillis()));
		ValorNutricionalPK pkid = new ValorNutricionalPK();
		pkid.setIdIngrediente(dto.getIdIngrediente());
		pkid.setIdTipoCompo(dto.getIdTipoCompo());
		entity.setId(pkid);
		entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
		entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
		entity.setNuAporte(dto.getNuAporte());
		
		return entity;
	}
	
	public static PlatoIngredienteDto parsePlatoIngrediente(PlatoIngrediente entity) {
		PlatoIngredienteDto dto = new PlatoIngredienteDto();
		
		dto.setIdPlato(entity.getId().getIdPlato());
		
		IngredienteDto ingrediente = new IngredienteDto();
		ingrediente.setId(entity.getId().getIdIngrediente());
		ingrediente.setNombreIngrediente(entity.getIngrediente().getDeIngrediente());
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
		dto.setNumeroOrden(entity.getNuOrden());
		
		return dto;
	}
	
	public static UnidadMedidaDto parseUnidadMedidad (UnidadMedida entity) {
		UnidadMedidaDto dto = new UnidadMedidaDto();
		dto.setIdUnidadMedida(entity.getIdUnidMedi());
		dto.setDescripcionUnidadMedida(entity.getDeUnidMedi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setFechaModificacion(entity.getFeModificacion());
		
		return dto;
	}
	
}

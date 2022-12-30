/**
 * 
 */
package pe.com.rhsistemas.mfjpaconfiguracion.util;

import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
import pe.com.rhsistemas.mfjpaconfiguracion.entity.ConfiguracionFamilia;
import pe.com.rhsistemas.mfjpaconfiguracion.entity.Persona;

/**
 * @author Edwin
 *
 */
public class Utilmfjpa {
	
	public static ConfiguracionFamilia parseConfiguracionCuentaDto(ConfiguracionCuentaDto dto) {
		ConfiguracionFamilia entity = new ConfiguracionFamilia();
		
		entity.setIdPersona(dto.getIdPersona());
		entity.setNoFamilia(dto.getNombreFamilia());
		entity.setNuDamas(dto.getNumeroMujeres());
		entity.setNuVarones(dto.getNumeroHombres());
		entity.setDiasCocina(dto.getDiasCocinaSemana());
		
		/*
		Persona persona = new Persona();
		persona.setIdPersona(dto.getIdPersona());
		entity.setPersona(persona);*/
		
		return entity;
	}
	
	public static ConfiguracionCuentaDto parseConfiguracionFamilia(ConfiguracionFamilia entity) {
		ConfiguracionCuentaDto dto = new ConfiguracionCuentaDto();
		
		dto.setDiasCocinaSemana(entity.getDiasCocina());
		dto.setIdPersona(entity.getIdPersona());
		dto.setNombreFamilia(entity.getNoFamilia());
		dto.setNumeroHombres(entity.getNuVarones());
		dto.setNumeroMujeres(entity.getNuDamas());
		
		return dto;
	}

}

/**
 * 
 */
package pe.com.rhsistemas.mf.cross.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;

/**
 * @author Edwin
 *
 */
public class UtilMfDto {
	
	private static final Logger log = LoggerFactory.getLogger(UtilMfDto.class);
	
	/**
	 * Patron por defecto para metodos que no envien el patron
	 */
	private static final String PATRON_SDF_DEFECTO = "dd/MM/yyyy";

	public static void pintaLog(Object bean, String prefijo) {
		ObjectMapper converter = new ObjectMapper();
		try {
			log.info(prefijo+" : "+converter.writeValueAsString(bean));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public static java.sql.Date convertirUtilDateASqlDate(java.util.Date utilDate){
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		return sqlDate;
	}
	
	public static int numeroEnteroAleatorio(int min, int max) {
		double ra = Math.random();
		
		Double r = Math.floor(ra * (max - min + 1) + min);
		
		int rpta = r.intValue();
		
		return rpta;
	}
	
	public static Date parseStringADate(String cadenaFecha, String patron) throws UtilMfDtoException {
		try {
			if (StringUtils.isBlank(cadenaFecha)) {
				throw new UtilMfDtoException("La fecha a convertir esta en blanco o es nula");
			}
			SimpleDateFormat sdf = new SimpleDateFormat((StringUtils.isBlank(patron)?PATRON_SDF_DEFECTO:patron));
			return sdf.parse(cadenaFecha);
		} catch (ParseException e) {
			throw new UtilMfDtoException(e);
		}
	}
	
	public static String parseDateAString(Date fecha, String patron) throws UtilMfDtoException {
		if (fecha == null) {
			throw new UtilMfDtoException("La fecha es nula");
		}
		SimpleDateFormat sdf = new SimpleDateFormat((StringUtils.isBlank(patron)?PATRON_SDF_DEFECTO:patron));
		return sdf.format(fecha);
	}

}

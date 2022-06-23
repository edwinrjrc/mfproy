/**
 * 
 */
package pe.com.rhsistemas.mf.cross.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
	
	public static Date parseStringADate(String cadenaFecha, String patron, TimeZone timeZone) throws UtilMfDtoException {
		try {
			if (StringUtils.isBlank(cadenaFecha)) {
				throw new UtilMfDtoException("La fecha a convertir esta en blanco o es nula");
			}
			log.info(patron);
			SimpleDateFormat sdf = new SimpleDateFormat((StringUtils.isBlank(patron)?PATRON_SDF_DEFECTO:patron));
			sdf.setTimeZone(TimeZone.getDefault());
			if (timeZone != null) {
				sdf.setTimeZone(timeZone);
			}
			
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
	
	public static Timestamp parseDateASqlTimestamp(Date fecha) {
		Timestamp t = new Timestamp(fecha.getTime());
		
		return t;
	}
	
	public static boolean listaNoVacia(List<?> lista) {
		
		boolean resultado = (lista != null && !lista.isEmpty());
		
		return resultado;
	}
	
	public static int parseaNullInt(Integer valor) {
		int dato = 0;
		if (valor != null) {
			dato = valor.intValue();
		}
		return dato;
	}
	public static long parseaNullLong(Long valor) {
		long dato = 0;
		if (valor != null) {
			dato = valor.longValue();
		}
		return dato;
	}
	
	public static Integer parseStringAInteger(String cadena) throws UtilMfDtoException {
		try {
			return Integer.valueOf(cadena);
		} catch (NumberFormatException e) {
			throw new UtilMfDtoException(e);
		}
	}
	
	public static Double parseStringADouble(String cadena) throws UtilMfDtoException {
		try {
			return Double.valueOf(cadena);
		} catch (NumberFormatException e) {
			throw new UtilMfDtoException(e);
		}
	}
	
	public static Long parseIntALong(int valor) {
		Long salida = null;
		
		salida = Long.valueOf(valor);
		return salida;
	}
	
	public static Long parseIntegerALong(Integer valor) throws UtilMfDtoException {
		try {
			return Long.valueOf(valor);
		} catch (Exception e) {
			throw new UtilMfDtoException(e);
		}
	}
	
	public static Long parseStringALong(String valor) throws UtilMfDtoException {
		try {
			return Long.valueOf(valor);
		} catch (Exception e) {
			throw new UtilMfDtoException(e);
		}
	}
	
	public static Date hoyDate() {
		Date fechaHoy = new Date();
		return fechaHoy;
	}
	
	public static Timestamp hoyTimestamp() {
		Timestamp fechaHoy = new Timestamp(System.currentTimeMillis());
		return fechaHoy;
	}
	
	public static String escribeObjetoEnLog(Object objeto) throws UtilMfDtoException {
		try {
			ObjectMapper Obj = new ObjectMapper();
			
			return Obj.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			throw new UtilMfDtoException(e);
		}
	}
	
	public static String nombreDia(Date fecha) {
		String nombreDia = null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			nombreDia = "Domingo";
			break;
		case Calendar.MONDAY:
			nombreDia = "Lunes";
			break;
		case Calendar.TUESDAY:
			nombreDia = "Martes";
			break;
		case Calendar.WEDNESDAY:
			nombreDia = "Miercoles";
			break;
		case Calendar.THURSDAY:
			nombreDia = "Jueves";
			break;
		case Calendar.FRIDAY:
			nombreDia = "Viernes";
			break;
		case Calendar.SATURDAY:
			nombreDia = "Sabado";
			break;
		}
		
		return nombreDia;
	}
	
	public static int numeroDia(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}

/**
 * 
 */
package pe.com.rhsistemas.mf.cross.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Edwin
 *
 */
public class UtilMf {
	
	private static final Logger log = LoggerFactory.getLogger(UtilMf.class);

	public static void pintaLog(Object bean, String prefijo) {
		ObjectMapper converter = new ObjectMapper();
		try {
			log.debug(prefijo+" : "+converter.writeValueAsString(bean));
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

}

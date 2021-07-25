/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;

/**
 * @author Edwin
 *
 */
public class Extractor<T> implements ResponseExtractor<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(Extractor.class);

	/**
	 * 
	 */
	public Extractor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public T extractData(ClientHttpResponse response) throws IOException {
		String e = response.getStatusCode().toString();
		
		logger.info(e);
		
		return null;
	}

}

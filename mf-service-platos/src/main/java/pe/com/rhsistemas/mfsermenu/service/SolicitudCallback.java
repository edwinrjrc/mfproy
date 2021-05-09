/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;

/**
 * @author Edwin
 *
 */
public class SolicitudCallback implements RequestCallback {

	private HttpHeaders headers;
	/**
	 * 
	 */
	public SolicitudCallback(HttpHeaders headers) {
		this.headers = headers;
	}

	@Override
	public void doWithRequest(ClientHttpRequest request) throws IOException {
		request.getHeaders().putAll(headers);
	}

}

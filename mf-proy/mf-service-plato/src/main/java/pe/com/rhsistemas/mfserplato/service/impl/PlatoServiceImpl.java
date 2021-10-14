/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;
import pe.com.rhsistemas.mfserplato.service.PlatoService;
import pe.com.rhsistemas.mfserplato.service.remote.RemoteServicePlato;
import pe.com.rhsistemas.mfserplato.service.remote.RemoteServiceReceta;

/**
 * @author Edwin
 *
 */
@Service
public class PlatoServiceImpl implements PlatoService {
	
	@Autowired
	private RemoteServicePlato remoteServicePlato;
	
	@Autowired
	private RemoteServiceReceta remoteServiceReceta;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
	}
	
	@Override
	public void guardarPlatoFavorito(PlatoFavoritoDto platoFavoritoDto) throws MFServicePlatoException {
		remoteServicePlato.guardarPlatoFavorito(platoFavoritoDto);
	}

	@Override
	public List<PlatoDto> listarPlatos(Integer idPersona) throws MFServicePlatoException{
		List<PlatoDto> listaPlatos = remoteServicePlato.listarPlatos();
		List<PlatoFavoritoDto> listaFavoritos = remoteServicePlato.listarPlatosFavoritos(idPersona);
		
		if (UtilMfDto.listaNoVacia(listaPlatos)) {
			for (PlatoDto platoDto : listaPlatos) {
				if (UtilMfDto.listaNoVacia(listaFavoritos)) {
					for (PlatoFavoritoDto platoFavoritoDto : listaFavoritos) {
						if (platoFavoritoDto.getIdPlato().intValue() == platoDto.getId().intValue()) {
							platoDto.setFavorito(true);
							break;
						}
					}
				}
			}
		}
		
		return listaPlatos;
	}
	
	@Override
	public Map<String,Object> consultarCompletaPlato(Integer idPlato) throws MFServicePlatoException{
		Map<String,Object> mapeoLista = new HashMap<String,Object>();
		List<PlatoIngredienteDto> listaIngredientes = remoteServiceReceta.listarIngredientesPlato(idPlato);
		List<RecetaDto> listaReceta = remoteServiceReceta.consultaRecetaPlato(idPlato);
		PlatoDto plato = remoteServicePlato.consultarPlato(idPlato);
		
		mapeoLista.put(Constantes.VALOR_DTO, plato);
		mapeoLista.put(Constantes.VALOR_LISTA_RECETA, listaReceta);
		mapeoLista.put(Constantes.VALOR_LISTA_INGREDIENTES, listaIngredientes);
		
		return mapeoLista;
	}
}

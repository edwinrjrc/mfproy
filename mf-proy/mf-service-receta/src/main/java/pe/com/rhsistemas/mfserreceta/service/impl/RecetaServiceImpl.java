/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.IngredientesPlatoCargaDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfserreceta.exception.MfServiceRecetaException;
import pe.com.rhsistemas.mfserreceta.service.RecetaService;
import pe.com.rhsistemas.mfserreceta.service.remote.RemoteServiceIngrediente;
import pe.com.rhsistemas.mfserreceta.service.remote.RemoteServiceReceta;
import pe.com.rhsistemas.mfserreceta.service.remote.RemoteServiceUnidadMedida;

/**
 * @author Edwin
 *
 */
@Service
public class RecetaServiceImpl implements RecetaService {

	private static final Logger log = LoggerFactory.getLogger(RecetaServiceImpl.class);

	@Autowired
	private RemoteServiceReceta remoteServiceReceta;

	@Autowired
	private RemoteServiceUnidadMedida remoteServiceUnidadMedida;

	@Autowired
	private RemoteServiceIngrediente remoteServiceIngrediente;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		return restTemplate;
	}

	@Override
	public void guardarComentarioReceta(RecetaComentarioPostDto recetaComentarioDto) throws MfServiceRecetaException {
		remoteServiceReceta.guardarComentarioReceta(recetaComentarioDto);
	}

	@Override
	public void eliminarComentarioReceta(RecetaComentarioPostDto recetaComentarioDto) throws MfServiceRecetaException {
		remoteServiceReceta.eliminarComentario(recetaComentarioDto);
	}

	@Override
	public List<RecetaComentarioDto> consultarComentario(Integer idPlato) throws MfServiceRecetaException {
		return remoteServiceReceta.consultarComentario(idPlato);
	}

	@Override
	public void guardarIngredientesPreparacionPlato(List<IngredientesPlatoCargaDto> listaIngredientesCarga, List<RecetaDto> listaPasosReceta)
			throws MfServiceRecetaException, UtilMfDtoException {
		Integer idPlato = 0;
		
		idPlato = UtilMfDto.parseStringAInteger(listaIngredientesCarga.get(0).getIdPlato());
		
		eliminarIngredientesPlato(idPlato);
		guardarIngredientesPlato(listaIngredientesCarga);
		
		eliminarPreparacionPlato(idPlato);
		guardarPreparacionPlato(listaPasosReceta);
	}
	
	@Override
	public void guardarIngredientesPlato(List<IngredientesPlatoCargaDto> listaIngredientesCarga)
			throws MfServiceRecetaException {
		List<UnidadMedidaDto> listaUnidadesMedida = remoteServiceUnidadMedida.consultarUnidadesMedida();
		List<IngredienteDto> listaIngredientes = remoteServiceIngrediente.consultarIngredientes();

		Map<String, Integer> mapeoUnidades = listaUnidadesMedida.stream().collect(
				Collectors.toMap(UnidadMedidaDto::getDescripcionMayuscula, UnidadMedidaDto::getIdUnidadMedida));
		Map<String, Integer> mapeoIngredientes = listaIngredientes.stream()
				.collect(Collectors.toMap(IngredienteDto::getNombreIngrediente, IngredienteDto::getId));

		List<PlatoIngredienteDto> listaIngredientesPlato = new ArrayList<PlatoIngredienteDto>();
		PlatoIngredienteDto platoIngredienteDto = null;
		int i=0;
		for (IngredientesPlatoCargaDto ingrePlatoCargaDto : listaIngredientesCarga) {
			platoIngredienteDto = new PlatoIngredienteDto();
			platoIngredienteDto.setCantidad(Float.valueOf(ingrePlatoCargaDto.getCantidad()));
			platoIngredienteDto.setFechaModificacion(UtilMfDto.hoyTimestamp());
			platoIngredienteDto.setFechaRegistro(UtilMfDto.hoyTimestamp());
			platoIngredienteDto.setIdPlato(Integer.valueOf(ingrePlatoCargaDto.getIdPlato()));
			platoIngredienteDto.setIdUsuarioModificacion(Integer.valueOf(ingrePlatoCargaDto.getIdUsuario()));
			platoIngredienteDto.setIdUsuarioRegistro(Integer.valueOf(ingrePlatoCargaDto.getIdUsuario()));
			platoIngredienteDto.getIngrediente().setId(mapeoIngredientes.get(StringUtils.upperCase(ingrePlatoCargaDto.getNombreIngrediente())));
			platoIngredienteDto.getUnidadMedida().setCodigo(mapeoUnidades.get(StringUtils.upperCase(ingrePlatoCargaDto.getNombreUnidadMedida())).toString());
			if (platoIngredienteDto.getIngrediente().getId() != null && StringUtils.isNotBlank(platoIngredienteDto.getUnidadMedida().getCodigo()) && platoIngredienteDto.getCantidad() != null ) {
				i++;
				platoIngredienteDto.setNumeroOrden(i);
				listaIngredientesPlato.add(platoIngredienteDto);
			}
		}
		
		remoteServiceReceta.guardarIngredientesPlato(listaIngredientesPlato);
	}
	
	@Override
	public void guardarPreparacionPlato(List<RecetaDto> listaPasosReceta)
			throws MfServiceRecetaException {

		remoteServiceReceta.guardarPreparacionPlato(listaPasosReceta);
	}
	
	@Override
	public void eliminarIngredientesPlato(Integer idPlato) throws MfServiceRecetaException {
		remoteServiceReceta.eliminarIngredientesPlato(idPlato);
	}
	
	
	@Override
	public void eliminarPreparacionPlato(Integer idPlato) throws MfServiceRecetaException {
		remoteServiceReceta.eliminarPreparacionPlato(idPlato);
	}
	
}

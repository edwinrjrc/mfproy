/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteExportDto;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;
import pe.com.rhsistemas.mfserviceingrediente.service.IngredienteLogicaService;
import pe.com.rhsistemas.mfserviceingrediente.service.remote.RemoteServiceIngrediente;
import pe.com.rhsistemas.mfserviceingrediente.service.remote.RemoteServiceMenu;
import pe.com.rhsistemas.mfserviceingrediente.service.remote.RemoteServiceUnidadMedida;

/**
 * @author Edwin
 *
 */
@Service
public class IngredienteLogicaServiceImpl implements IngredienteLogicaService {

	private static final Logger log = LoggerFactory.getLogger(IngredienteLogicaServiceImpl.class);

	@Autowired
	private RemoteServiceIngrediente remoteServiceIngrediente;

	@Autowired
	private RemoteServiceUnidadMedida remoteServiceUnidadMedida;
	
	@Autowired
	private RemoteServiceMenu remoteServiceMenu;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		return restTemplate;
	}

	@Override
	public List<PlatoIngredienteDto> ingredientesPlato(Integer idPlato) throws MfServiceIngredienteException {
		List<PlatoIngredienteDto> listaSalida = null;

		listaSalida = remoteServiceIngrediente.ingredientesPlato(idPlato);

		return listaSalida;
	}

	@Override
	public void registrarIngredientesPlato(List<PlatoIngredienteDto> listaIngredientes)
			throws MfServiceIngredienteException {

		remoteServiceIngrediente.registrarIngredientesPlato(listaIngredientes);

	}

	@Override
	public List<UnidadMedidaDto> listarUnidadesMedida() throws MfServiceIngredienteException {
		List<UnidadMedidaDto> lista = null;

		lista = remoteServiceUnidadMedida.listarUnidadesMedida();

		return lista;
	}

	@Override
	public List<IngredienteDto> listarIngredientes() throws MfServiceIngredienteException {
		List<IngredienteDto> lista = null;

		lista = remoteServiceIngrediente.listarIngredientes();

		return lista;
	}

	@Override
	public List<PlatoIngredienteDto> listarIngredientesMenu(Integer idMenu) throws MfServiceIngredienteException {
		return remoteServiceIngrediente.listarIngredientesMenu(idMenu);
	}

	@Override
	public void listarPlatoIngredientesMenu(Long idMenu) throws MfServiceIngredienteException {
		try {
			List<PlatoIngredienteExportDto> salidaLista = remoteServiceIngrediente.listarPlatoIngredientesMenu(idMenu);
			
			for (PlatoIngredienteExportDto platoIngredienteExportDto : salidaLista) {
				log.info("totalIngrediente ::"+platoIngredienteExportDto.getTotalIngrediente().toString());
				log.info("Nombre Ingrediente ::"+platoIngredienteExportDto.getIngrediente().getNombreIngrediente());
			}
			
			MenuGeneradoDto menuGeneradoDto = remoteServiceMenu.consultarMenuGenerado(idMenu);
			log.info("Menu Generado ::"+UtilMfDto.escribeObjetoEnLog(menuGeneradoDto));
		} catch (MfServiceIngredienteException e) {
			throw new MfServiceIngredienteException(e);
		} catch (UtilMfDtoException e) {
			throw new MfServiceIngredienteException(e);
		}
	}

}

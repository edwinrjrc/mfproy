/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
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

	//private static final Logger log = LoggerFactory.getLogger(IngredienteLogicaServiceImpl.class);

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
	public Map<String,Object> listarPlatoIngredientesMenu(Long idMenu) throws MfServiceIngredienteException {
		Map<String,Object> salidaPdf = null;
		try {
			List<PlatoIngredienteExportDto> salidaLista = remoteServiceIngrediente.listarPlatoIngredientesMenu(idMenu);

			MenuGeneradoDto menuGeneradoDto = remoteServiceMenu.consultarMenuGenerado(idMenu);
			
			String ruta = "C:\\Server\\jasper\\";

			File archivoJRxml = new File(ruta + "ingredientesPlatos.jrxml");
			InputStream is = new FileInputStream(archivoJRxml);
			JasperReport jasperReport = JasperCompileManager.compileReport(is);

			JRDataSource jds = new JRBeanCollectionDataSource(salidaLista);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, obtenerMapParameters(menuGeneradoDto),
					jds);
			
			String nombreArchivoPdf = "ingrPlato_"+idMenu + "_" + menuGeneradoDto.getIdPersona() + "_" + UtilMfDto.parseDateAString(UtilMfDto.hoyDate(), "yyyyMMddHHmmss");
			nombreArchivoPdf = nombreArchivoPdf + ".pdf";
			
			salidaPdf = new HashMap<String,Object>();
			salidaPdf.put("nombreArchivo", nombreArchivoPdf);
			salidaPdf.put("bytes64", JasperExportManager.exportReportToPdf(jasperPrint));
			
			is.close();
			
		} catch (MfServiceIngredienteException e) {
			throw new MfServiceIngredienteException(e);
		} catch (JRException e) {
			throw new MfServiceIngredienteException(e);
		} catch (IOException e) {
			throw new MfServiceIngredienteException(e);
		} catch (UtilMfDtoException e) {
			throw new MfServiceIngredienteException(e);
		}
		return salidaPdf;
	}

	private Map<String, Object> obtenerMapParameters(MenuGeneradoDto menuGeneradoDto) {
		Map<String, Object> mapParametros = new HashMap<String, Object>();

		Iterator<MenuDetalleDto> iterador = menuGeneradoDto.getListaPlatos().iterator();

		while (iterador.hasNext()) {
			MenuDetalleDto menuDeta = iterador.next();

			if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.SUNDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_1, menuDeta.getPlatoDto().getNombrePlato());
			} else if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.MONDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_2, menuDeta.getPlatoDto().getNombrePlato());
			} else if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.TUESDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_3, menuDeta.getPlatoDto().getNombrePlato());
			} else if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.WEDNESDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_4, menuDeta.getPlatoDto().getNombrePlato());
			} else if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.THURSDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_5, menuDeta.getPlatoDto().getNombrePlato());
			} else if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.FRIDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_6, menuDeta.getPlatoDto().getNombrePlato());
			} else if (UtilMfDto.numeroDia(menuDeta.getFechaConsumo()) == Calendar.SATURDAY) {
				mapParametros.put(Constantes.PARAM_MENU_DIA_7, menuDeta.getPlatoDto().getNombrePlato());
			}
		}

		return mapParametros;
	}

}

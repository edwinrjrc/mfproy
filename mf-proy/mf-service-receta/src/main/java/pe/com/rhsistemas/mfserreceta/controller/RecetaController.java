/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredientesPlatoCargaDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfserreceta.service.RecetaService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/recetaservice")
public class RecetaController {
	
	private static final Logger log = LoggerFactory.getLogger(RecetaController.class);
	
	@Autowired
	private RecetaService recetaService;

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/receta")
	public ResponseEntity<Map<String, Object>> guardarModificacionReceta(
			@RequestBody Map<String, Object> preparacionModificada) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarModificacionReceta");
			UtilMfDto.pintaLog(preparacionModificada, "preparacionModificada");
			
			List<Map<String,Object>> listaPreparacion = (List<Map<String,Object>>) preparacionModificada.get("preparacionModificada");

			RecetaDto recetaDto = null;
			List<RecetaDto> listaReceta = null;
			if (UtilMfDto.listaNoVacia(listaPreparacion)) {
				listaReceta = new ArrayList<>();
				for (Map<String, Object> map : listaPreparacion) {
					recetaDto = new RecetaDto();
					recetaDto.setIdPaso(UtilMfDto.parseStringAInteger(map.get("nroPaso").toString()));
					recetaDto.setDescripcionReceta(map.get("descripcionPaso").toString());
					recetaDto.setMinutosCompletar(UtilMfDto.parseStringADouble(map.get("tiempo").toString()));
					listaReceta.add(recetaDto);
				}
			}
			
			status = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/comentariosReceta")
	public ResponseEntity<Map<String, Object>> guardarComentarioReceta(
			@RequestBody RecetaComentarioPostDto recetaComentarioDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarComentarioReceta");
			UtilMfDto.pintaLog(recetaComentarioDto, "recetaComentarioDto");
			
			recetaComentarioDto.setFechaRegistro(UtilMfDto.hoyTimestamp());
			recetaComentarioDto.setFechaModificacion(UtilMfDto.hoyTimestamp());
			
			recetaService.guardarComentarioReceta(recetaComentarioDto);
			
			status = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/comentariosReceta/{idComentarioReceta}/{idUsuario}")
	public ResponseEntity<Map<String, Object>> eliminarComentarioReceta(@PathVariable Integer idComentarioReceta, @PathVariable Integer idUsuario) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en eliminarComentarioReceta");
			UtilMfDto.pintaLog(idComentarioReceta, "idComentarioReceta");
			UtilMfDto.pintaLog(idUsuario, "idUsuario");
			
			RecetaComentarioPostDto recetaComentarioDto = new RecetaComentarioPostDto();
			recetaComentarioDto.setIdComentarioPlato(idComentarioReceta);
			recetaComentarioDto.setIdUsuarioRegistro(idUsuario);
			recetaComentarioDto.setFechaRegistro(UtilMfDto.hoyTimestamp());
			recetaComentarioDto.setFechaModificacion(UtilMfDto.hoyTimestamp());
			
			recetaService.eliminarComentarioReceta(recetaComentarioDto);
			
			status = HttpStatus.CREATED;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/comentariosReceta/{idPlato}")
	public ResponseEntity<Map<String, Object>> consultarComentariosReceta(@PathVariable Integer idPlato){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarComentarioReceta");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			List<RecetaComentarioDto> listaComentarios = recetaService.consultarComentario(idPlato);
			
			status = HttpStatus.OK;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaComentarios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/datosReceta")
	public ResponseEntity<Map<String, Object>> guardarDatosReceta(
			@RequestParam("files") MultipartFile[] files, @RequestParam("idPlato") String idPlato, @RequestParam("idUsuario") String idUsuario) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros en guardarComentarioReceta");
			log.info("tamano ::"+files.length);
			UtilMfDto.pintaLog(idPlato, "idPlato");
			UtilMfDto.pintaLog(idUsuario, "idUsuario");
			
			String extensionArchivo = FilenameUtils.getExtension(files[0].getOriginalFilename());
			
			Workbook workBook = null;
			
			if (Constantes.XLS_EXTENSION.equals(extensionArchivo)) {
				workBook = new HSSFWorkbook(files[0].getInputStream());
			}
			else if (Constantes.XLSX_EXTENSION.equals(extensionArchivo)) {
				workBook = new XSSFWorkbook(files[0].getInputStream());
			}
			
			Sheet hojaCero = workBook.getSheetAt(0);
			
			Iterator<Row> iterador = hojaCero.iterator();
			iterador.next();
			Row fila = null;
			Cell celda1 = null;
			IngredientesPlatoCargaDto ingredientesPlatoCarga = null;
			List<IngredientesPlatoCargaDto> listaIngredientesCarga = new ArrayList<>();
			while(iterador.hasNext()) {
				fila = iterador.next();
				ingredientesPlatoCarga = new IngredientesPlatoCargaDto();
				
				celda1 = fila.getCell(0);
				ingredientesPlatoCarga.setNombrePlato(celda1.getStringCellValue());
				ingredientesPlatoCarga.setIdPlato(idPlato);
				ingredientesPlatoCarga.setIdUsuario(idUsuario);
				ingredientesPlatoCarga.setIdUsuarioRegistro(UtilMfDto.parseStringAInteger(idUsuario));
				ingredientesPlatoCarga.setIdUsuarioModificacion(UtilMfDto.parseStringAInteger(idUsuario));
				
				celda1 = fila.getCell(1);
				ingredientesPlatoCarga.setNombreIngrediente(celda1.getStringCellValue());
				
				celda1 = fila.getCell(2);
				if (celda1.getCellType() == CellType.NUMERIC) {
					ingredientesPlatoCarga.setCantidad(String.valueOf(celda1.getNumericCellValue()));
				}
				else {
					ingredientesPlatoCarga.setCantidad(celda1.getStringCellValue());
				}
				
				celda1 = fila.getCell(3);
				ingredientesPlatoCarga.setNombreUnidadMedida(celda1.getStringCellValue());
				ingredientesPlatoCarga.setFechaModificacion(UtilMfDto.hoyTimestamp());
				ingredientesPlatoCarga.setFechaRegistro(UtilMfDto.hoyTimestamp());
				listaIngredientesCarga.add(ingredientesPlatoCarga);
			}
			
			Sheet hojaUno = workBook.getSheetAt(1);
			
			iterador = hojaUno.iterator();
			iterador.next();
			RecetaDto recetaDto = null;
			List<RecetaDto> listaPasosReceta = new ArrayList<>();
			while (iterador.hasNext()) {
				fila = iterador.next();
				
				recetaDto = new RecetaDto();
				celda1 = fila.getCell(1);
				recetaDto.setIdPaso(Double.valueOf(celda1.getNumericCellValue()).intValue());
				
				celda1 = fila.getCell(2);
				recetaDto.setDescripcionReceta(celda1.getStringCellValue());
				
				celda1 = fila.getCell(3);
				recetaDto.setMinutosCompletar(Double.valueOf(celda1.getNumericCellValue()));
				
				celda1 = fila.getCell(4);
				recetaDto.setIndicadorCoccion( ""+ Double.valueOf(celda1.getNumericCellValue()).intValue() );
				
				recetaDto.setIdPlato(UtilMfDto.parseStringAInteger(idPlato));
				recetaDto.setIdUsuarioRegistro(UtilMfDto.parseStringAInteger(idUsuario));
				recetaDto.setIdUsuarioModificacion(UtilMfDto.parseStringAInteger(idUsuario));
				recetaDto.setFechaModificacion(UtilMfDto.hoyTimestamp());
				recetaDto.setFechaRegistro(UtilMfDto.hoyTimestamp());
				listaPasosReceta.add(recetaDto);
			}
			
			status = HttpStatus.CREATED;
			
			recetaService.guardarIngredientesPreparacionPlato(listaIngredientesCarga,listaPasosReceta);
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
}

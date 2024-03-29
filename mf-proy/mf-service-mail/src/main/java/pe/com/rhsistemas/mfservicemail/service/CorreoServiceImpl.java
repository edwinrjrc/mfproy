/**
 * 
 */
package pe.com.rhsistemas.mfservicemail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import pe.com.rhsistemas.mf.post.dto.DatosCorreo;
import pe.com.rhsistemas.mfservicemail.exception.MfServiceMailException;

/**
 * @author Edwin
 *
 */
@Service
public class CorreoServiceImpl implements CorreoService {
	
	private static final Logger log = LoggerFactory.getLogger(CorreoServiceImpl.class);
	
	@Autowired
	private JavaMailSender envioCorreo;
	
	@Value("${spring.mail.correoin}")
	private String correoIn;
	
	@Value("${spring.mail.user.name}")
	private String nombreCorreoFrom;

	@Override
	public void enviarCorreoSinAdjunto(DatosCorreo datosCorreo) throws MfServiceMailException {
		try {
			MimeMessage mensajeObject = envioCorreo.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mensajeObject, true, "UTF-8");
			
			helper.setFrom(correoIn,nombreCorreoFrom);
	        helper.setTo(datosCorreo.getPara());
	        helper.setSubject(datosCorreo.getAsunto());
	        helper.setText(datosCorreo.getMensaje(), true);

			envioCorreo.send(mensajeObject);
			
			log.debug("Correo Electronico enviado a "+datosCorreo.getPara());
			
		} catch (MailException e) {
			throw new MfServiceMailException(e);
		} catch (MessagingException e) {
			throw new MfServiceMailException(e);
		} catch (UnsupportedEncodingException e) {
			throw new MfServiceMailException(e);
		}
	}


}

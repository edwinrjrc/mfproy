package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the solicitud_recupera_credencial database table.
 * 
 */
@Entity
@Table(name="solicitud_recupera_credencial", schema = "sistema")
@NamedQuery(name="SolicitudRecuperaCredencial.findAll", query="SELECT s FROM SolicitudRecuperaCredencial s")
public class SolicitudRecuperaCredencial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOLICITUD_RECUPERA_CREDENCIAL_IDSOLIRECUCRED_GENERATOR", sequenceName="SEQ_SOLRECUPEROCREDENCIAL", schema = "SISTEMA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLICITUD_RECUPERA_CREDENCIAL_IDSOLIRECUCRED_GENERATOR")
	@Column(name="id_soli_recu_cred", unique=true, nullable=false)
	private Long idSoliRecuCred;

	@Column(name="fe_cadu_soli")
	private Timestamp feCaduSoli;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="fe_solicitud", nullable=false)
	private Timestamp feSolicitud;

	@Column(name="id_persona", nullable=false)
	private Long idPersona;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	public SolicitudRecuperaCredencial() {
	}

	public Long getIdSoliRecuCred() {
		return this.idSoliRecuCred;
	}

	public void setIdSoliRecuCred(Long idSoliRecuCred) {
		this.idSoliRecuCred = idSoliRecuCred;
	}

	public Timestamp getFeCaduSoli() {
		return this.feCaduSoli;
	}

	public void setFeCaduSoli(Timestamp feCaduSoli) {
		this.feCaduSoli = feCaduSoli;
	}

	public Timestamp getFeModificacion() {
		return this.feModificacion;
	}

	public void setFeModificacion(Timestamp feModificacion) {
		this.feModificacion = feModificacion;
	}

	public Timestamp getFeRegistro() {
		return this.feRegistro;
	}

	public void setFeRegistro(Timestamp feRegistro) {
		this.feRegistro = feRegistro;
	}

	public Timestamp getFeSolicitud() {
		return this.feSolicitud;
	}

	public void setFeSolicitud(Timestamp feSolicitud) {
		this.feSolicitud = feSolicitud;
	}

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Integer getIdUsuaCrea() {
		return this.idUsuaCrea;
	}

	public void setIdUsuaCrea(Integer idUsuaCrea) {
		this.idUsuaCrea = idUsuaCrea;
	}

	public Integer getIdUsuaModi() {
		return this.idUsuaModi;
	}

	public void setIdUsuaModi(Integer idUsuaModi) {
		this.idUsuaModi = idUsuaModi;
	}

}
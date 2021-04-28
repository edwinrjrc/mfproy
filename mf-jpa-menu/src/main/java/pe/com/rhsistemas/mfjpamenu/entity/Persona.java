package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name="persona", schema = "Sistema")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_IDPERSONA_GENERATOR", sequenceName="SISTEMA.SEQ_PERSONA", schema = "Sistema", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_IDPERSONA_GENERATOR")
	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_nacimiento")
	private Date feNacimiento;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_tipo_docu")
	private Integer idTipoDocu;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="nu_documento", length=30)
	private String nuDocumento;

	public Persona() {
	}

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Timestamp getFeModificacion() {
		return this.feModificacion;
	}

	public void setFeModificacion(Timestamp feModificacion) {
		this.feModificacion = feModificacion;
	}

	public Date getFeNacimiento() {
		return this.feNacimiento;
	}

	public void setFeNacimiento(Date feNacimiento) {
		this.feNacimiento = feNacimiento;
	}

	public Timestamp getFeRegistro() {
		return this.feRegistro;
	}

	public void setFeRegistro(Timestamp feRegistro) {
		this.feRegistro = feRegistro;
	}

	public Integer getIdTipoDocu() {
		return this.idTipoDocu;
	}

	public void setIdTipoDocu(Integer idTipoDocu) {
		this.idTipoDocu = idTipoDocu;
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

	public String getNuDocumento() {
		return this.nuDocumento;
	}

	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}

}
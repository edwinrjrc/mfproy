package pe.com.rhsistemas.mfjpareceta.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the historia_inicio_sesion database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="historia_inicio_sesion")
@NamedQuery(name="HistoriaInicioSesion.findAll", query="SELECT h FROM HistoriaInicioSesion h")
public class HistoriaInicioSesion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HistoriaInicioSesionPK id;

	@Column(name="fe_logout")
	private Timestamp feLogout;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	public HistoriaInicioSesion() {
	}

	public HistoriaInicioSesionPK getId() {
		return this.id;
	}

	public void setId(HistoriaInicioSesionPK id) {
		this.id = id;
	}

	public Timestamp getFeLogout() {
		return this.feLogout;
	}

	public void setFeLogout(Timestamp feLogout) {
		this.feLogout = feLogout;
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
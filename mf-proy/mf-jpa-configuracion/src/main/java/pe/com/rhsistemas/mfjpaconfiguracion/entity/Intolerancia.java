package pe.com.rhsistemas.mfjpaconfiguracion.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the intolerancia database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="intolerancia")
@NamedQuery(name="Intolerancia.findAll", query="SELECT i FROM Intolerancia i")
public class Intolerancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_intolerancia", unique=true, nullable=false)
	private Integer idIntolerancia;

	@Column(name="de_intolerancia", length=100)
	private String deIntolerancia;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	public Intolerancia() {
	}

	public Integer getIdIntolerancia() {
		return this.idIntolerancia;
	}

	public void setIdIntolerancia(Integer idIntolerancia) {
		this.idIntolerancia = idIntolerancia;
	}

	public String getDeIntolerancia() {
		return this.deIntolerancia;
	}

	public void setDeIntolerancia(String deIntolerancia) {
		this.deIntolerancia = deIntolerancia;
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

}
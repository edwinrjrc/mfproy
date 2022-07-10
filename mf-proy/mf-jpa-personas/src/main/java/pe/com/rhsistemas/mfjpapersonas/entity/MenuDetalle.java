package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the menu_detalle database table.
 * 
 */
@Entity
@Table(name="menu_detalle", schema = "sistema")
@NamedQuery(name="MenuDetalle.findAll", query="SELECT m FROM MenuDetalle m")
public class MenuDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MenuDetallePK id;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_plato")
	private Integer idPlato;

	@Column(name="id_tipo_plato")
	private Integer idTipoPlato;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	public MenuDetalle() {
	}

	public MenuDetallePK getId() {
		return this.id;
	}

	public void setId(MenuDetallePK id) {
		this.id = id;
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

	public Integer getIdPlato() {
		return this.idPlato;
	}

	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}

	public Integer getIdTipoPlato() {
		return this.idTipoPlato;
	}

	public void setIdTipoPlato(Integer idTipoPlato) {
		this.idTipoPlato = idTipoPlato;
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
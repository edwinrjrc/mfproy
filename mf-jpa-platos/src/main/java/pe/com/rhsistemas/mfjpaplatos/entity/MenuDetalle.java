package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the menu_detalle database table.
 * 
 */
@Entity
@Table(name="menu_detalle", schema = "sistema")
@NamedQuery(name="MenuDetalle.findAll", query="SELECT m FROM MenuDetalle m")
public class MenuDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="fe_consumo", unique=true, nullable=false)
	private Date feConsumo;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	//bi-directional many-to-one association to MenuGenerado
	@ManyToOne
	@JoinColumn(name="id_generado", nullable=false)
	private MenuGenerado menuGenerado;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="id_plato", nullable=false)
	private Plato plato;

	public MenuDetalle() {
	}

	public Date getFeConsumo() {
		return this.feConsumo;
	}

	public void setFeConsumo(Date feConsumo) {
		this.feConsumo = feConsumo;
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

	public MenuGenerado getMenuGenerado() {
		return this.menuGenerado;
	}

	public void setMenuGenerado(MenuGenerado menuGenerado) {
		this.menuGenerado = menuGenerado;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

}
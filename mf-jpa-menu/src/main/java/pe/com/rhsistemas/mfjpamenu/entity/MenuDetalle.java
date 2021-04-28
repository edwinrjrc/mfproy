package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the menu_detalle database table.
 * 
 */
@Entity
@Table(name="menu_detalle", schema = "Sistema")
@NamedQuery(name="MenuDetalle.findAll", query="SELECT m FROM MenuDetalle m")
public class MenuDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MenuDetallePK id;

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
	@JoinColumn(name="id_generado", nullable=false, insertable=false, updatable=false)
	private MenuGenerado menuGenerado;

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

}
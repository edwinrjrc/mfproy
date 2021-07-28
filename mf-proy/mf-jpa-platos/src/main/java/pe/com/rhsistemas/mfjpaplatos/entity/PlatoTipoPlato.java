package pe.com.rhsistemas.mfjpaplatos.entity;

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
 * The persistent class for the plato_tipo_plato database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="plato_tipo_plato")
@NamedQuery(name="PlatoTipoPlato.findAll", query="SELECT p FROM PlatoTipoPlato p")
public class PlatoTipoPlato implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoTipoPlatoPK id;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	//bi-directional many-to-one association to TipoPlato
	@ManyToOne
	@JoinColumn(name="id_tipo_plat", nullable=false, insertable=false, updatable=false)
	private TipoPlato tipoPlato;

	public PlatoTipoPlato() {
	}

	public PlatoTipoPlatoPK getId() {
		return this.id;
	}

	public void setId(PlatoTipoPlatoPK id) {
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

	public TipoPlato getTipoPlato() {
		return this.tipoPlato;
	}

	public void setTipoPlato(TipoPlato tipoPlato) {
		this.tipoPlato = tipoPlato;
	}

}
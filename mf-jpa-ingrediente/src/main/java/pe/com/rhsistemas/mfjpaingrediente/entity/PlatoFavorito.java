package pe.com.rhsistemas.mfjpaingrediente.entity;

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
 * The persistent class for the plato_favorito database table.
 * 
 */
@Entity
@Table(name="plato_favorito", schema = "sistema")
@NamedQuery(name="PlatoFavorito.findAll", query="SELECT p FROM PlatoFavorito p")
public class PlatoFavorito implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoFavoritoPK id;

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

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="id_plato", nullable=false, insertable=false, updatable=false)
	private Plato plato;

	public PlatoFavorito() {
	}

	public PlatoFavoritoPK getId() {
		return this.id;
	}

	public void setId(PlatoFavoritoPK id) {
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

}
package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the plato_favorito database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="plato_favorito")
@NamedQuery(name="PlatoFavorito.findAll", query="SELECT p FROM PlatoFavorito p")
public class PlatoFavorito implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoFavoritoPK id;
	
	@Column(name="st_plat_favo", nullable=false)
	private String stPlatFavo;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

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

	/**
	 * @return the stPlatFavo
	 */
	public String getStPlatFavo() {
		return stPlatFavo;
	}

	/**
	 * @param stPlatFavo the stPlatFavo to set
	 */
	public void setStPlatFavo(String stPlatFavo) {
		this.stPlatFavo = stPlatFavo;
	}

}
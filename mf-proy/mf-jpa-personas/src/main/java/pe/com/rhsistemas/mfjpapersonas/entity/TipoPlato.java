package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tipo_plato database table.
 * 
 */
@Entity
@Table(name="tipo_plato", schema = "sistema")
@NamedQuery(name="TipoPlato.findAll", query="SELECT t FROM TipoPlato t")
public class TipoPlato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_plat", unique=true, nullable=false)
	private Integer idTipoPlat;

	@Column(name="de_tipo_plat", nullable=false, length=100)
	private String deTipoPlat;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="in_entrada", length=1)
	private String inEntrada;

	@Column(name="in_fondo", length=1)
	private String inFondo;

	//bi-directional many-to-one association to PlatoTipoPlato
	@OneToMany(mappedBy="tipoPlato")
	private List<PlatoTipoPlato> platoTipoPlatos;

	public TipoPlato() {
	}

	public Integer getIdTipoPlat() {
		return this.idTipoPlat;
	}

	public void setIdTipoPlat(Integer idTipoPlat) {
		this.idTipoPlat = idTipoPlat;
	}

	public String getDeTipoPlat() {
		return this.deTipoPlat;
	}

	public void setDeTipoPlat(String deTipoPlat) {
		this.deTipoPlat = deTipoPlat;
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

	public String getInEntrada() {
		return this.inEntrada;
	}

	public void setInEntrada(String inEntrada) {
		this.inEntrada = inEntrada;
	}

	public String getInFondo() {
		return this.inFondo;
	}

	public void setInFondo(String inFondo) {
		this.inFondo = inFondo;
	}

	public List<PlatoTipoPlato> getPlatoTipoPlatos() {
		return this.platoTipoPlatos;
	}

	public void setPlatoTipoPlatos(List<PlatoTipoPlato> platoTipoPlatos) {
		this.platoTipoPlatos = platoTipoPlatos;
	}

	public PlatoTipoPlato addPlatoTipoPlato(PlatoTipoPlato platoTipoPlato) {
		getPlatoTipoPlatos().add(platoTipoPlato);
		platoTipoPlato.setTipoPlato(this);

		return platoTipoPlato;
	}

	public PlatoTipoPlato removePlatoTipoPlato(PlatoTipoPlato platoTipoPlato) {
		getPlatoTipoPlatos().remove(platoTipoPlato);
		platoTipoPlato.setTipoPlato(null);

		return platoTipoPlato;
	}

}
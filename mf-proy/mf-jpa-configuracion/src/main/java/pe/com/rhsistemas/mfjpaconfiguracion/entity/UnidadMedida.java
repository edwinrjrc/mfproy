package pe.com.rhsistemas.mfjpaconfiguracion.entity;

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
 * The persistent class for the unidad_medida database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="unidad_medida")
@NamedQuery(name="UnidadMedida.findAll", query="SELECT u FROM UnidadMedida u")
public class UnidadMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_unid_medi", unique=true, nullable=false)
	private Integer idUnidMedi;

	@Column(name="de_unid_medi", length=100)
	private String deUnidMedi;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	//bi-directional many-to-one association to PlatoIngrediente
	@OneToMany(mappedBy="unidadMedida")
	private List<PlatoIngrediente> platoIngredientes;

	public UnidadMedida() {
	}

	public Integer getIdUnidMedi() {
		return this.idUnidMedi;
	}

	public void setIdUnidMedi(Integer idUnidMedi) {
		this.idUnidMedi = idUnidMedi;
	}

	public String getDeUnidMedi() {
		return this.deUnidMedi;
	}

	public void setDeUnidMedi(String deUnidMedi) {
		this.deUnidMedi = deUnidMedi;
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

	public List<PlatoIngrediente> getPlatoIngredientes() {
		return this.platoIngredientes;
	}

	public void setPlatoIngredientes(List<PlatoIngrediente> platoIngredientes) {
		this.platoIngredientes = platoIngredientes;
	}

	public PlatoIngrediente addPlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().add(platoIngrediente);
		platoIngrediente.setUnidadMedida(this);

		return platoIngrediente;
	}

	public PlatoIngrediente removePlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().remove(platoIngrediente);
		platoIngrediente.setUnidadMedida(null);

		return platoIngrediente;
	}

}
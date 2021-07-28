package pe.com.rhsistemas.mfjpaingrediente.entity;

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
 * The persistent class for the ubigeo database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="ubigeo")
@NamedQuery(name="Ubigeo.findAll", query="SELECT u FROM Ubigeo u")
public class Ubigeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ubigeo", unique=true, nullable=false)
	private Integer idUbigeo;

	@Column(name="co_departamento")
	private Integer coDepartamento;

	@Column(name="co_distrito")
	private Integer coDistrito;

	@Column(name="co_provincia")
	private Integer coProvincia;

	@Column(name="de_departamento", length=100)
	private String deDepartamento;

	@Column(name="de_distrito", length=100)
	private String deDistrito;

	@Column(name="de_provincia", length=100)
	private String deProvincia;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="ubigeo")
	private List<Direccion> direccions;

	public Ubigeo() {
	}

	public Integer getIdUbigeo() {
		return this.idUbigeo;
	}

	public void setIdUbigeo(Integer idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	public Integer getCoDepartamento() {
		return this.coDepartamento;
	}

	public void setCoDepartamento(Integer coDepartamento) {
		this.coDepartamento = coDepartamento;
	}

	public Integer getCoDistrito() {
		return this.coDistrito;
	}

	public void setCoDistrito(Integer coDistrito) {
		this.coDistrito = coDistrito;
	}

	public Integer getCoProvincia() {
		return this.coProvincia;
	}

	public void setCoProvincia(Integer coProvincia) {
		this.coProvincia = coProvincia;
	}

	public String getDeDepartamento() {
		return this.deDepartamento;
	}

	public void setDeDepartamento(String deDepartamento) {
		this.deDepartamento = deDepartamento;
	}

	public String getDeDistrito() {
		return this.deDistrito;
	}

	public void setDeDistrito(String deDistrito) {
		this.deDistrito = deDistrito;
	}

	public String getDeProvincia() {
		return this.deProvincia;
	}

	public void setDeProvincia(String deProvincia) {
		this.deProvincia = deProvincia;
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

	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setUbigeo(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setUbigeo(null);

		return direccion;
	}

}
package pe.com.rhsistemas.mfjpaplatos.entity;

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
 * The persistent class for the tipo_cocina database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="tipo_cocina")
@NamedQuery(name="TipoCocina.findAll", query="SELECT t FROM TipoCocina t")
public class TipoCocina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_coci", unique=true, nullable=false)
	private Integer idTipoCoci;

	@Column(name="de_tipo_coci", length=100)
	private String deTipoCoci;

	@Column(name="fe_modificacion")
	private Timestamp feModificacion;

	@Column(name="fe_registro")
	private Timestamp feRegistro;

	@Column(name="id_usua_modi")
	private Integer idUsuaModi;

	@Column(name="id_usua_regi")
	private Integer idUsuaRegi;

	//bi-directional many-to-one association to Plato
	@OneToMany(mappedBy="tipoCocina")
	private List<Plato> platos;

	public TipoCocina() {
	}

	public Integer getIdTipoCoci() {
		return this.idTipoCoci;
	}

	public void setIdTipoCoci(Integer idTipoCoci) {
		this.idTipoCoci = idTipoCoci;
	}

	public String getDeTipoCoci() {
		return this.deTipoCoci;
	}

	public void setDeTipoCoci(String deTipoCoci) {
		this.deTipoCoci = deTipoCoci;
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

	public Integer getIdUsuaModi() {
		return this.idUsuaModi;
	}

	public void setIdUsuaModi(Integer idUsuaModi) {
		this.idUsuaModi = idUsuaModi;
	}

	public Integer getIdUsuaRegi() {
		return this.idUsuaRegi;
	}

	public void setIdUsuaRegi(Integer idUsuaRegi) {
		this.idUsuaRegi = idUsuaRegi;
	}

	public List<Plato> getPlatos() {
		return this.platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public Plato addPlato(Plato plato) {
		getPlatos().add(plato);
		plato.setTipoCocina(this);

		return plato;
	}

	public Plato removePlato(Plato plato) {
		getPlatos().remove(plato);
		plato.setTipoCocina(null);

		return plato;
	}

}
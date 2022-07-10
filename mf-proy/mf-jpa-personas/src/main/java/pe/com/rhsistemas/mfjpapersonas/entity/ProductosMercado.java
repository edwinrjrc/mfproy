package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the productos_mercado database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="productos_mercado")
@NamedQuery(name="ProductosMercado.findAll", query="SELECT p FROM ProductosMercado p")
public class ProductosMercado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_prod_merc", unique=true, nullable=false)
	private Integer idProdMerc;

	@Column(name="co_sku", length=100)
	private String coSku;

	@Column(name="de_prod_merc", length=300)
	private String deProdMerc;

	@Column(name="fe_modificacion")
	private Timestamp feModificacion;

	@Column(name="fe_registro")
	private Timestamp feRegistro;

	@Column(name="id_empresa")
	private Integer idEmpresa;

	@Column(name="id_usua_modi")
	private Integer idUsuaModi;

	@Column(name="id_usua_regi")
	private Integer idUsuaRegi;

	public ProductosMercado() {
	}

	public Integer getIdProdMerc() {
		return this.idProdMerc;
	}

	public void setIdProdMerc(Integer idProdMerc) {
		this.idProdMerc = idProdMerc;
	}

	public String getCoSku() {
		return this.coSku;
	}

	public void setCoSku(String coSku) {
		this.coSku = coSku;
	}

	public String getDeProdMerc() {
		return this.deProdMerc;
	}

	public void setDeProdMerc(String deProdMerc) {
		this.deProdMerc = deProdMerc;
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

	public Integer getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
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

}
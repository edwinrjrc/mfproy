package pe.com.rhsistemas.mfjpaingrediente.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the conversion_unidad_medida database table.
 * 
 */
@Entity
@Table(name="conversion_unidad_medida", schema = "sistema")
@NamedQuery(name="ConversionUnidadMedida.findAll", query="SELECT c FROM ConversionUnidadMedida c")
public class ConversionUnidadMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConversionUnidadMedidaPK id;

	@Column(name="fe_modificacion")
	private Timestamp feModificacion;

	@Column(name="fe_registro")
	private Timestamp feRegistro;

	@Column(name="id_usua_crea")
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi")
	private Integer idUsuaModi;

	@Column(name="vl_fact_conv", precision=15, scale=6)
	private BigDecimal vlFactConv;
	

	public ConversionUnidadMedida() {
	}

	public ConversionUnidadMedidaPK getId() {
		return this.id;
	}

	public void setId(ConversionUnidadMedidaPK id) {
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

	public BigDecimal getVlFactConv() {
		return this.vlFactConv;
	}

	public void setVlFactConv(BigDecimal vlFactConv) {
		this.vlFactConv = vlFactConv;
	}

}
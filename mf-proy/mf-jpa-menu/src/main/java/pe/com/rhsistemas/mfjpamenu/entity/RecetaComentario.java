package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the receta_comentario database table.
 * 
 */
@Entity
@Table(name="receta_comentario", schema = "sistema")
@NamedQuery(name="RecetaComentario.findAll", query="SELECT r FROM RecetaComentario r")
public class RecetaComentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECETA_COMENTARIO_IDCOMEPLAT_GENERATOR", sequenceName="SEQ_COMEN_RECETA", schema = "sistema")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECETA_COMENTARIO_IDCOMEPLAT_GENERATOR")
	@Column(name="id_come_plat", unique=true, nullable=false)
	private Long idComePlat;

	@Column(name="fe_modificacion")
	private Timestamp feModificacion;

	@Column(name="fe_registro")
	private Timestamp feRegistro;

	@Column(name="id_plato", nullable=false)
	private Integer idPlato;

	@Column(name="id_usua_crea")
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi")
	private Integer idUsuaModi;

	@Column(name="tx_comentario", length=2147483647)
	private String txComentario;

	public RecetaComentario() {
	}

	public Long getIdComePlat() {
		return this.idComePlat;
	}

	public void setIdComePlat(Long idComePlat) {
		this.idComePlat = idComePlat;
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

	public Integer getIdPlato() {
		return this.idPlato;
	}

	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
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

	public String getTxComentario() {
		return this.txComentario;
	}

	public void setTxComentario(String txComentario) {
		this.txComentario = txComentario;
	}

}
package pe.com.rhsistemas.mfjpaingrediente.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the configuracion_familia database table.
 * 
 */
@Entity
@Table(name="configuracion_familia")
@NamedQuery(name="ConfiguracionFamilia.findAll", query="SELECT c FROM ConfiguracionFamilia c")
public class ConfiguracionFamilia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="no_familia", length=100)
	private String noFamilia;

	@Column(name="nu_damas")
	private Integer nuDamas;

	@Column(name="nu_varones")
	private Integer nuVarones;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_persona", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	public ConfiguracionFamilia() {
	}

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNoFamilia() {
		return this.noFamilia;
	}

	public void setNoFamilia(String noFamilia) {
		this.noFamilia = noFamilia;
	}

	public Integer getNuDamas() {
		return this.nuDamas;
	}

	public void setNuDamas(Integer nuDamas) {
		this.nuDamas = nuDamas;
	}

	public Integer getNuVarones() {
		return this.nuVarones;
	}

	public void setNuVarones(Integer nuVarones) {
		this.nuVarones = nuVarones;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
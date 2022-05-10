package kancelarijskiPribor.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the poruceni_proizvod database table.
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@Table(name="poruceni_proizvod")
@NamedQuery(name="PoruceniProizvod.findAll", query="SELECT p FROM PoruceniProizvod p")
public class PoruceniProizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORUCENI_PROIZVOD_PORUCENIPROIZVODID_GENERATOR", sequenceName="PORUCENI_PROIZVOD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUCENI_PROIZVOD_PORUCENIPROIZVODID_GENERATOR")
	@Column(name="poruceni_proizvod_id")
	private Integer poruceniProizvodId;

	private Integer kolicina;

	//bi-directional many-to-one association to Porudzbina
	@ManyToOne
	@JoinColumn(name="porudzbina_id",insertable=false, updatable=false)
	private Porudzbina porudzbina;

	//bi-directional many-to-one association to Proizvod
	@ManyToOne
	@JoinColumn(name="proizvod_id",insertable=false, updatable=false)
	private Proizvod proizvod;

	public PoruceniProizvod() {
	}

	
	public Integer getPoruceniProizvodId() {
		return poruceniProizvodId;
	}


	public void setPoruceniProizvodId(Integer poruceniProizvodId) {
		this.poruceniProizvodId = poruceniProizvodId;
	}


	public Integer getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Porudzbina getPorudzbina() {
		return this.porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public Proizvod getProizvod() {
		return this.proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

}
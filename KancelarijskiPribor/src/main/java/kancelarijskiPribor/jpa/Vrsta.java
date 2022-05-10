package kancelarijskiPribor.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the vrsta database table.
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@NamedQuery(name="Vrsta.findAll", query="SELECT v FROM Vrsta v")
public class Vrsta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VRSTA_VRSTAID_GENERATOR", sequenceName="VRSTA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VRSTA_VRSTAID_GENERATOR")
	@Column(name="vrsta_id")
	private Integer vrstaId;

	@Column(name="naziv_vrste")
	private String nazivVrste;

	//bi-directional many-to-one association to Proizvod
	@OneToMany(mappedBy="vrsta")
	@JsonIgnore
	private List<Proizvod> proizvods;

	public Vrsta() {
	}

	public Integer getVrstaId() {
		return this.vrstaId;
	}

	public void setVrstaId(Integer vrstaId) {
		this.vrstaId = vrstaId;
	}

	public String getNazivVrste() {
		return this.nazivVrste;
	}

	public void setNazivVrste(String nazivVrste) {
		this.nazivVrste = nazivVrste;
	}

	public List<Proizvod> getProizvods() {
		return this.proizvods;
	}

	public void setProizvods(List<Proizvod> proizvods) {
		this.proizvods = proizvods;
	}

	public Proizvod addProizvod(Proizvod proizvod) {
		getProizvods().add(proizvod);
		proizvod.setVrsta(this);

		return proizvod;
	}

	public Proizvod removeProizvod(Proizvod proizvod) {
		getProizvods().remove(proizvod);
		proizvod.setVrsta(null);

		return proizvod;
	}

}
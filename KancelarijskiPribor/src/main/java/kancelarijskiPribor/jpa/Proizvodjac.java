package kancelarijskiPribor.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the proizvodjac database table.
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@NamedQuery(name="Proizvodjac.findAll", query="SELECT p FROM Proizvodjac p")
public class Proizvodjac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVODJAC_PROIZVODJACID_GENERATOR", sequenceName="PORIZVODJAC_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVODJAC_PROIZVODJACID_GENERATOR")
	@Column(name="proizvodjac_id")
	private Integer proizvodjacId;

	@Column(name="naziv_proizvodjaca")
	private String nazivProizvodjaca;

	//bi-directional many-to-one association to Proizvod
	@JsonIgnore
	@OneToMany(mappedBy="proizvodjac")
	private List<Proizvod> proizvods;

	public Proizvodjac() {
	}

	public Integer getProizvodjacId() {
		return this.proizvodjacId;
	}

	public void setProizvodjacId(Integer proizvodjacId) {
		this.proizvodjacId = proizvodjacId;
	}

	public String getNazivProizvodjaca() {
		return this.nazivProizvodjaca;
	}

	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}

	public List<Proizvod> getProizvods() {
		return this.proizvods;
	}

	public void setProizvods(List<Proizvod> proizvods) {
		this.proizvods = proizvods;
	}

	public Proizvod addProizvod(Proizvod proizvod) {
		getProizvods().add(proizvod);
		proizvod.setProizvodjac(this);

		return proizvod;
	}

	public Proizvod removeProizvod(Proizvod proizvod) {
		getProizvods().remove(proizvod);
		proizvod.setProizvodjac(null);

		return proizvod;
	}

}
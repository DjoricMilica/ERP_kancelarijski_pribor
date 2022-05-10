package kancelarijskiPribor.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the kategorija database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="KATEGORIJA_KATEGORIJAID_GENERATOR", sequenceName="KATEGORIJA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KATEGORIJA_KATEGORIJAID_GENERATOR")
	@Column(name="kategorija_id")
	private Integer kategorijaId;

	@Column(name="naziv_kategorija")
	private String nazivKategorija;

	//bi-directional many-to-one association to Proizvod
	@JsonIgnore
	@OneToMany(mappedBy="kategorija")
	private List<Proizvod> proizvods;

	public Kategorija() {
	}

	public Integer getKategorijaId() {
		return this.kategorijaId;
	}

	public void setKategorijaId(Integer kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getNazivKategorija() {
		return this.nazivKategorija;
	}

	public void setNazivKategorija(String nazivKategorija) {
		this.nazivKategorija = nazivKategorija;
	}

	public List<Proizvod> getProizvods() {
		return this.proizvods;
	}

	public void setProizvods(List<Proizvod> proizvods) {
		this.proizvods = proizvods;
	}

	public Proizvod addProizvod(Proizvod proizvod) {
		getProizvods().add(proizvod);
		proizvod.setKategorija(this);

		return proizvod;
	}

	public Proizvod removeProizvod(Proizvod proizvod) {
		getProizvods().remove(proizvod);
		proizvod.setKategorija(null);

		return proizvod;
	}

}
package kancelarijskiPribor.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the proizvod database table.
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@NamedQuery(name="Proizvod.findAll", query="SELECT p FROM Proizvod p")
public class Proizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVOD_PROIZVODID_GENERATOR", sequenceName="PROIZVOD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_PROIZVODID_GENERATOR")
	@Column(name="proizvod_id")
	private Integer proizvodId;

	private BigDecimal cena;

	private String jedinica;

	@Column(name="na_stanju")
	private Integer naStanju;

	private String naziv;

	//bi-directional many-to-one association to PoruceniProizvod
	@JsonIgnore
	@OneToMany(mappedBy="proizvod")
	private List<PoruceniProizvod> poruceniProizvods;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name="kategorija_id")
	private Kategorija kategorija;

	//bi-directional many-to-one association to Proizvodjac
	@ManyToOne
	@JoinColumn(name="proizvodjac_id")
	private Proizvodjac proizvodjac;

	//bi-directional many-to-one association to Vrsta
	@ManyToOne
	@JoinColumn(name="vrsta_id")
	private Vrsta vrsta;

	public Proizvod() {
	}

	public Integer getProizvodId() {
		return this.proizvodId;
	}

	public void setProizvodId(Integer proizvodId) {
		this.proizvodId = proizvodId;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public String getJedinica() {
		return this.jedinica;
	}

	public void setJedinica(String jedinica) {
		this.jedinica = jedinica;
	}

	public Integer getNaStanju() {
		return this.naStanju;
	}

	public void setNaStanju(Integer naStanju) {
		this.naStanju = naStanju;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<PoruceniProizvod> getPoruceniProizvods() {
		return this.poruceniProizvods;
	}

	public void setPoruceniProizvods(List<PoruceniProizvod> poruceniProizvods) {
		this.poruceniProizvods = poruceniProizvods;
	}

	public PoruceniProizvod addPoruceniProizvod(PoruceniProizvod poruceniProizvod) {
		getPoruceniProizvods().add(poruceniProizvod);
		poruceniProizvod.setProizvod(this);

		return poruceniProizvod;
	}

	public PoruceniProizvod removePoruceniProizvod(PoruceniProizvod poruceniProizvod) {
		getPoruceniProizvods().remove(poruceniProizvod);
		poruceniProizvod.setProizvod(null);

		return poruceniProizvod;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public Vrsta getVrsta() {
		return this.vrsta;
	}

	public void setVrsta(Vrsta vrsta) {
		this.vrsta = vrsta;
	}

}
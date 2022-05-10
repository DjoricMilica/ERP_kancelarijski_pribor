package kancelarijskiPribor.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the porudzbina database table.
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@NamedQuery(name="Porudzbina.findAll", query="SELECT p FROM Porudzbina p")
public class Porudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORUDZBINA_PORUDZBINAID_GENERATOR", sequenceName="PORUDZBINA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUDZBINA_PORUDZBINAID_GENERATOR")
	@Column(name="porudzbina_id")
	private Integer porudzbinaId;

	@Column(name="adresa_isporuke")
	private String adresaIsporuke;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_kreiranja")
	private Date datumKreiranja;

	@Column(name="status_isporuke")
	private Boolean statusIsporuke;

	@Column(name="status_naplate")
	private Boolean statusNaplate;

	@Column(name="ukupna_cena")
	private BigDecimal ukupnaCena;

	@Column(name="vreme_kreiranja")
	private Time vremeKreiranja;

	//bi-directional many-to-one association to PoruceniProizvod
	@JsonIgnore
	@OneToMany(mappedBy="porudzbina")
	private List<PoruceniProizvod> poruceniProizvods;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_id")
	private Korisnik korisnik;

	public Porudzbina() {
	}

	public Integer getPorudzbinaId() {
		return this.porudzbinaId;
	}

	public void setPorudzbinaId(Integer porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

	public String getAdresaIsporuke() {
		return this.adresaIsporuke;
	}

	public void setAdresaIsporuke(String adresaIsporuke) {
		this.adresaIsporuke = adresaIsporuke;
	}

	public Date getDatumKreiranja() {
		return this.datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Boolean getStatusIsporuke() {
		return this.statusIsporuke;
	}

	public void setStatusIsporuke(Boolean statusIsporuke) {
		this.statusIsporuke = statusIsporuke;
	}

	public Boolean getStatusNaplate() {
		return this.statusNaplate;
	}

	public void setStatusNaplate(Boolean statusNaplate) {
		this.statusNaplate = statusNaplate;
	}

	public BigDecimal getUkupnaCena() {
		return this.ukupnaCena;
	}

	public void setUkupnaCena(BigDecimal ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Time getVremeKreiranja() {
		return this.vremeKreiranja;
	}

	public void setVremeKreiranja(Time vremeKreiranja) {
		this.vremeKreiranja = vremeKreiranja;
	}

	public List<PoruceniProizvod> getPoruceniProizvods() {
		return this.poruceniProizvods;
	}

	public void setPoruceniProizvods(List<PoruceniProizvod> poruceniProizvods) {
		this.poruceniProizvods = poruceniProizvods;
	}

	public PoruceniProizvod addPoruceniProizvod(PoruceniProizvod poruceniProizvod) {
		getPoruceniProizvods().add(poruceniProizvod);
		poruceniProizvod.setPorudzbina(this);

		return poruceniProizvod;
	}

	public PoruceniProizvod removePoruceniProizvod(PoruceniProizvod poruceniProizvod) {
		getPoruceniProizvods().remove(poruceniProizvod);
		poruceniProizvod.setPorudzbina(null);

		return poruceniProizvod;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}
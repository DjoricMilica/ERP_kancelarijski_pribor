package kancelarijskiPribor.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the korisnik database table.
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="KORISNIK_KORISNIKID_GENERATOR", sequenceName="KORISNIK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KORISNIK_KORISNIKID_GENERATOR")
	@Column(name="korisnik_id")
	private Integer korisnikId;

	private String email;

	private String ime;

	private String jmbg;

	private String kontakt;

	@Column(name="korisnicko_ime")
	private String korisnickoIme;

	private String lozinka;

	private String prezime;

	@Column(name="stanje_na_racunu")
	private BigDecimal stanjeNaRacunu;

	@Column(name="tip_korisnika")
	private String tipKorisnika;

	//bi-directional many-to-one association to Porudzbina
	@JsonIgnore
	@OneToMany(mappedBy="korisnik")
	private List<Porudzbina> porudzbinas;

	public Korisnik() {
	}

	public Integer getKorisnikId() {
		return this.korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getKontakt() {
		return this.kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public BigDecimal getStanjeNaRacunu() {
		return this.stanjeNaRacunu;
	}

	public void setStanjeNaRacunu(BigDecimal stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}

	public String getTipKorisnika() {
		return this.tipKorisnika;
	}

	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public List<Porudzbina> getPorudzbinas() {
		return this.porudzbinas;
	}

	public void setPorudzbinas(List<Porudzbina> porudzbinas) {
		this.porudzbinas = porudzbinas;
	}

	public Porudzbina addPorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().add(porudzbina);
		porudzbina.setKorisnik(this);

		return porudzbina;
	}

	public Porudzbina removePorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().remove(porudzbina);
		porudzbina.setKorisnik(null);

		return porudzbina;
	}

}
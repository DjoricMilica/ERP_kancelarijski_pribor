package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik,Integer>,PagingAndSortingRepository<Korisnik, Integer> {
	Korisnik findByKorisnickoIme(String korisnickoIme);
}

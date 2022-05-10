package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.PoruceniProizvod;

public interface PoruceniProizvodRepository extends JpaRepository<PoruceniProizvod, Integer>, PagingAndSortingRepository<PoruceniProizvod, Integer>{

}

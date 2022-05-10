package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer>, PagingAndSortingRepository<Porudzbina, Integer>{

}

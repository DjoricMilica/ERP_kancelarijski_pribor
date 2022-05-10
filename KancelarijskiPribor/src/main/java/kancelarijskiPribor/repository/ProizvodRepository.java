package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>, PagingAndSortingRepository<Proizvod, Integer> {

}

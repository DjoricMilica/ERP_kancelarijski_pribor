package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.Proizvodjac;

public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Integer>, PagingAndSortingRepository<Proizvodjac, Integer> {

}

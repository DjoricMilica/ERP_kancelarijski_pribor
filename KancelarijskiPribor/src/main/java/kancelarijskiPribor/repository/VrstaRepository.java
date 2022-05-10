package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.Vrsta;

public interface VrstaRepository extends JpaRepository<Vrsta, Integer>,  PagingAndSortingRepository<Vrsta, Integer> {

}

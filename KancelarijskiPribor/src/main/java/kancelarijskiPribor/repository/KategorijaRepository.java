package kancelarijskiPribor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kancelarijskiPribor.jpa.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer>, PagingAndSortingRepository<Kategorija, Integer>{
}

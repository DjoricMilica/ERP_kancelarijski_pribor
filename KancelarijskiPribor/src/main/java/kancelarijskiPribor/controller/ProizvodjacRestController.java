package kancelarijskiPribor.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kancelarijskiPribor.jpa.Proizvodjac;
import kancelarijskiPribor.repository.ProizvodjacRepository;

@RestController
public class ProizvodjacRestController {

	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getProizvodjaci(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "proizvodjacId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		Page<Proizvodjac> pagedResult = proizvodjacRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return null;
		}
	}
	
	@GetMapping("proizvodjac/{id}")
	public Proizvodjac getProizvodjac(@PathVariable("id") Integer id) {
		return proizvodjacRepository.getById(id);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@PostMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> postProizvodjac (@RequestBody Proizvodjac proizvodjac){
		if (!proizvodjacRepository.existsById(proizvodjac.getProizvodjacId())) {
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		}
		return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@PutMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> putProizvodjac(@RequestBody Proizvodjac proizvodjac) {
		if (!proizvodjacRepository.existsById(proizvodjac.getProizvodjacId())){
			return new ResponseEntity<Proizvodjac>(HttpStatus.NO_CONTENT);
		}
		proizvodjacRepository.save(proizvodjac);
		return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@DeleteMapping("proizvodjac/{id}")
	public ResponseEntity<Proizvodjac> deleteProizvodjac(@PathVariable("id") Integer id) {
		if (!proizvodjacRepository.existsById(id))
			return new ResponseEntity<Proizvodjac>(HttpStatus.NO_CONTENT);
		proizvodjacRepository.deleteById(id);
		return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
	}
	
}

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

import kancelarijskiPribor.jpa.Vrsta;
import kancelarijskiPribor.repository.VrstaRepository;

@RestController
public class VrstaRestController {

	@Autowired
	private VrstaRepository vrstaRepository;

	@GetMapping("vrsta")
	public Collection<Vrsta> getVrste(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "vrstaId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		Page<Vrsta> pagedResult = vrstaRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return null;
		}
	}
	
	@GetMapping("vrsta/{id}")
	public Vrsta getVrsta(@PathVariable("id") Integer id) {
		return vrstaRepository.getById(id);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@PostMapping("vrsta")
	public ResponseEntity<Vrsta> postVrsta (@RequestBody Vrsta vrsta){
		if (!vrstaRepository.existsById(vrsta.getVrstaId())) {
			vrstaRepository.save(vrsta);
			return new ResponseEntity<Vrsta>(HttpStatus.OK);
		}
		return new ResponseEntity<Vrsta>(HttpStatus.CONFLICT);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@PutMapping("vrsta")
	public ResponseEntity<Vrsta> putVrsta(@RequestBody Vrsta vrsta) {
		if (!vrstaRepository.existsById(vrsta.getVrstaId())){
			return new ResponseEntity<Vrsta>(HttpStatus.NO_CONTENT);
		}
		vrstaRepository.save(vrsta);
		return new ResponseEntity<Vrsta>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@DeleteMapping("vrsta/{id}")
	public ResponseEntity<Vrsta> deleteVrsta(@PathVariable("id") Integer id) {
		if (!vrstaRepository.existsById(id))
			return new ResponseEntity<Vrsta>(HttpStatus.NO_CONTENT);
		vrstaRepository.deleteById(id);
		return new ResponseEntity<Vrsta>(HttpStatus.OK);
	}
}

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

import kancelarijskiPribor.jpa.Porudzbina;
import kancelarijskiPribor.repository.PorudzbinaRepository;

@RestController
public class PorudzbinaRestController {

	@Autowired
	private PorudzbinaRepository porudzbinaRepository;

	@PreAuthorize("hasAnyAuthority('kupac','zaposleni')")
	@GetMapping("porudzbina")
	public Collection<Porudzbina> getPorudzbine(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "porudzbinaId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Porudzbina> pagedResult = porudzbinaRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return null;
		}
	}

	@PreAuthorize("hasAnyAuthority('kupac','zaposleni')")
	@GetMapping("porudzbina/{id}")
	public Porudzbina getPorudzbina(@PathVariable("id") Integer id) {
		return porudzbinaRepository.getById(id);
	}

	@PreAuthorize("hasAuthority('kupac')")
	@PostMapping("porudzbina")
	public ResponseEntity<Porudzbina> postPorudzbina(@RequestBody Porudzbina porudzbina) {
		if (!porudzbinaRepository.existsById(porudzbina.getPorudzbinaId())) {
			porudzbinaRepository.save(porudzbina);
			return new ResponseEntity<Porudzbina>(HttpStatus.OK);
		}
		return new ResponseEntity<Porudzbina>(HttpStatus.CONFLICT);
	}

	@PreAuthorize("hasAnyAuthority('kupac','zaposleni')")
	@PutMapping("porudzbina")
	public ResponseEntity<Porudzbina> putPorudzbina(@RequestBody Porudzbina porudzbina) {
		if (!porudzbinaRepository.existsById(porudzbina.getPorudzbinaId())) {
			return new ResponseEntity<Porudzbina>(HttpStatus.NO_CONTENT);
		}
		porudzbinaRepository.save(porudzbina);
		return new ResponseEntity<Porudzbina>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('kupac')")
	@DeleteMapping("porudzbina/{id}")
	public ResponseEntity<Porudzbina> deletePorudzbina(@PathVariable("id") Integer id) {
		if (!porudzbinaRepository.existsById(id))
			return new ResponseEntity<Porudzbina>(HttpStatus.NO_CONTENT);
		porudzbinaRepository.deleteById(id);
		return new ResponseEntity<Porudzbina>(HttpStatus.OK);
	}
}

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

import kancelarijskiPribor.jpa.Proizvod;
import kancelarijskiPribor.repository.ProizvodRepository;

@RestController
public class ProizvodRestController {
	@Autowired
	private ProizvodRepository proizvodRepository;

	@GetMapping("proizvod")
	public Collection<Proizvod> getProizvodi(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "proizvodId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Proizvod> pagedResult = proizvodRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return null;
		}
	}

	@GetMapping("proizvod/{id}")
	public Proizvod getProizvod(@PathVariable("id") Integer id) {
		return proizvodRepository.getById(id);
	}

	@PreAuthorize("hasAuthority('zaposleni')")
	@PostMapping("proizvod")
	public ResponseEntity<Proizvod> postProizvod(@RequestBody Proizvod proizvod) {
		if (!proizvodRepository.existsById(proizvod.getProizvodId())) {
			proizvodRepository.save(proizvod);
			return new ResponseEntity<Proizvod>(HttpStatus.OK);
		}
		return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
	}

	@PreAuthorize("hasAuthority('zaposleni')")
	@PutMapping("proizvod")
	public ResponseEntity<Proizvod> putProizvod(@RequestBody Proizvod proizvod) {
		if (!proizvodRepository.existsById(proizvod.getProizvodId())) {
			return new ResponseEntity<Proizvod>(HttpStatus.NO_CONTENT);
		}
		proizvodRepository.save(proizvod);
		return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('zaposleni')")
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable("id") Integer id) {
		if (!proizvodRepository.existsById(id))
			return new ResponseEntity<Proizvod>(HttpStatus.NO_CONTENT);
		proizvodRepository.deleteById(id);
		return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}
}

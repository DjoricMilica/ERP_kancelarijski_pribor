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
import kancelarijskiPribor.jpa.PoruceniProizvod;
import kancelarijskiPribor.repository.PoruceniProizvodRepository;

@RestController
public class PoruceniProizvodRestController {
	@Autowired
	private PoruceniProizvodRepository poruceniProizvodRepository;

	@PreAuthorize("hasAnyAuthority('zaposleni','kupac')")
	@GetMapping("poruceniProizvod")
	public Collection<PoruceniProizvod> getPoruceniProizvod(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "poruceniProizvodId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<PoruceniProizvod> pagedResult = poruceniProizvodRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return null;
		}
	}

	@PreAuthorize("hasAnyAuthority('zaposleni','kupac')")
	@GetMapping("poruceniProizvod/{id}")
	public PoruceniProizvod getPoruceniProizvod(@PathVariable("id") Integer id) {
		return poruceniProizvodRepository.getById(id);
	}

	@PreAuthorize("hasAuthority('kupac')")
	@PostMapping("poruceniProizvod")
	public ResponseEntity<PoruceniProizvod> postPoruceniProizvod(@RequestBody PoruceniProizvod poruceniProizvod) {
		if (!poruceniProizvodRepository.existsById(poruceniProizvod.getPoruceniProizvodId())) {
			poruceniProizvodRepository.save(poruceniProizvod);
			return new ResponseEntity<PoruceniProizvod>(HttpStatus.OK);
		}
		return new ResponseEntity<PoruceniProizvod>(HttpStatus.CONFLICT);
	}

	@PreAuthorize("hasAuthority('kupac')")
	@PutMapping("poruceniProizvod")
	public ResponseEntity<PoruceniProizvod> putPoruceniProizvod(@RequestBody PoruceniProizvod poruceniProizvod) {
		if (!poruceniProizvodRepository.existsById(poruceniProizvod.getPoruceniProizvodId())) {
			return new ResponseEntity<PoruceniProizvod>(HttpStatus.NO_CONTENT);
		}
		poruceniProizvodRepository.save(poruceniProizvod);
		return new ResponseEntity<PoruceniProizvod>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('kupac')")
	@DeleteMapping("poruceniProizvod/{id}")
	public ResponseEntity<PoruceniProizvod> deletePoruceniProizvod(@PathVariable("id") Integer id) {
		if (!poruceniProizvodRepository.existsById(id))
			return new ResponseEntity<PoruceniProizvod>(HttpStatus.NO_CONTENT);
		poruceniProizvodRepository.deleteById(id);
		return new ResponseEntity<PoruceniProizvod>(HttpStatus.OK);
	}
}

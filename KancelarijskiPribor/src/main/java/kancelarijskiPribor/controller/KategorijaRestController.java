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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kancelarijskiPribor.jpa.Kategorija;
import kancelarijskiPribor.repository.KategorijaRepository;


@RequestMapping("/api")
@RestController
public class KategorijaRestController {

	@Autowired
	private KategorijaRepository kategorijaRepository;

	@GetMapping("/kategorija")
	public Collection<Kategorija> getKategorije(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "kategorijaId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		Page<Kategorija> pagedResult = kategorijaRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return null;
		}
	}
	
	@GetMapping("kategorija/{id}")
	public Kategorija getKategorija(@PathVariable("id") Integer id) {
		return kategorijaRepository.getById(id);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@PostMapping("kategorija")
	public ResponseEntity<Kategorija> postKategorija (@RequestBody Kategorija kategorija){
		if (!kategorijaRepository.existsById(kategorija.getKategorijaId())) {
			kategorijaRepository.save(kategorija);
			return new ResponseEntity<Kategorija>(HttpStatus.OK);
		}
		return new ResponseEntity<Kategorija>(HttpStatus.CONFLICT);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@PutMapping("kategorija")
	public ResponseEntity<Kategorija> putKategorija(@RequestBody Kategorija kategorija) {
		if (!kategorijaRepository.existsById(kategorija.getKategorijaId())){
			return new ResponseEntity<Kategorija>(HttpStatus.NO_CONTENT);
		}
		kategorijaRepository.save(kategorija);
		return new ResponseEntity<Kategorija>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('zaposleni')")
	@DeleteMapping("kategorija/{id}")
	public ResponseEntity<Kategorija> deleteKategorija(@PathVariable("id") Integer id) {
		if (!kategorijaRepository.existsById(id))
			return new ResponseEntity<Kategorija>(HttpStatus.NO_CONTENT);
		kategorijaRepository.deleteById(id);
		return new ResponseEntity<Kategorija>(HttpStatus.OK);
	}
}

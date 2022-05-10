package kancelarijskiPribor.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kancelarijskiPribor.jpa.Korisnik;
import kancelarijskiPribor.repository.KorisnikRepository;

@RestController
@RequestMapping("/api")
public class KorisnikRestController {
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	   KorisnikRestController(KorisnikRepository repository) {
	       this.korisnikRepository = repository;
	   }
	   
	@GetMapping("/korisnik")
	public Collection<Korisnik> getKorisnici(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "korisnikId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		Page<Korisnik> pagedResult = korisnikRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return null;
		}
	}
	
	@GetMapping("/korisnik/{id}")
	public Korisnik getKorisnik(@PathVariable("id") Integer id) {
		return korisnikRepository.getById(id);
	}
	
	
	@PostMapping("/korisnik")
	public ResponseEntity<Korisnik> postKorisnik(@RequestBody Korisnik korisnik){
		if (!korisnikRepository.existsById(korisnik.getKorisnikId())) {
			korisnikRepository.save(korisnik);
			return new ResponseEntity<Korisnik>(HttpStatus.OK);
		}
		return new ResponseEntity<Korisnik>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("/korisnik")
	public ResponseEntity<Korisnik> putKorisnik(@RequestBody Korisnik korisnik) {
		if (!korisnikRepository.existsById(korisnik.getKorisnikId())){
			return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
		}
		korisnikRepository.save(korisnik);
		return new ResponseEntity<Korisnik>(HttpStatus.OK);
	}
	
	@DeleteMapping("/korisnik/{id}")
	public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable("id") Integer id) {
		if (!korisnikRepository.existsById(id))
			return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
		korisnikRepository.deleteById(id);
		//if (id == -100)
			//jdbcTemplate.execute(" INSERT INTO \"kategorija\" (\"kategorija_id\", \"naziv_kategorija\") "
				//	+ "VALUES (-100, 'Test')");
		return new ResponseEntity<Korisnik>(HttpStatus.OK);
	}
}

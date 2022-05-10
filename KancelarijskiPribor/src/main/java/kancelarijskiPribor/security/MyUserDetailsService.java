package kancelarijskiPribor.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kancelarijskiPribor.jpa.Korisnik;
import kancelarijskiPribor.repository.KorisnikRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Korisnik korisnik =  korisnikRepository.findByKorisnickoIme(username);
		 ArrayList<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		 auths.add(new SimpleGrantedAuthority(korisnik.getTipKorisnika().toString()));
		 return new User(korisnik.getKorisnickoIme(),korisnik.getLozinka(), auths);
	    }
}

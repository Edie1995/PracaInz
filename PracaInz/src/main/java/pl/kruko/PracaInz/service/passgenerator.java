package pl.kruko.PracaInz.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passgenerator {
	public static void Main (String... args) {
		
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder ();
        
	System.out.println(bCryptPasswordEncoder.encode ("1234"));
    }
}

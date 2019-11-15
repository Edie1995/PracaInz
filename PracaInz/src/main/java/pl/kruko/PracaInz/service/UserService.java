package pl.kruko.PracaInz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User findByLogin(String login) {	
		User user = userRepository.findByLogin(login);
		return user;
	}
}

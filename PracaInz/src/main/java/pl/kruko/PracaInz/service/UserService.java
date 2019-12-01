package pl.kruko.PracaInz.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.UserDTO;
import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public UserDTO findByLogin(String login) {	
		User user = userRepository.findByLogin(login);
		return modelMapper.map(user, UserDTO.class);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
}

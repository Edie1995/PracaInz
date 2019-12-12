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

	public UserDTO findDTOByLogin(String login) {	
		User user = findByLogin(login);
		return modelMapper.map(user, UserDTO.class);
	}
	
	public User findByLogin(String login) {
		User user = userRepository.findByLogin(login);
		return user;
	}
	public void save(User user) {
		userRepository.save(user);
	}
}

package com.ownproject.taskproject.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownproject.taskproject.entity.Users;
import com.ownproject.taskproject.payload.UserDto;
import com.ownproject.taskproject.repository.UserRepository;
import com.ownproject.taskproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Users user = userDtoToEntity(userDto); //converted userdto to users
		Users users = modelMapper.map(userDto, Users.class);
		Users savedUser = userRepository.save(users);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

}

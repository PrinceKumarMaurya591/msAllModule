package com.ms.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ms.userservice.dto.DepartmentDto;
import com.ms.userservice.dto.ResponseDto;
import com.ms.userservice.dto.UserDto;
import com.ms.userservice.entity.User;
import com.ms.userservice.repository.UserRepository;
import com.ms.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
    @Autowired

    private WebClient webClient;
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        DepartmentDto departmentDto = webClient.get()
                 .uri("http://localhost:9000/api/departments/" + user.getDepartmentId())
                 .retrieve()
                                 .bodyToMono(DepartmentDto.class)
                                         .block();
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }


    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
package com.erenildo.fakebank.auth.service;

import com.erenildo.fakebank.auth.dtos.UserCreateAccountDTO;
import com.erenildo.fakebank.auth.entity.Role;
import com.erenildo.fakebank.auth.entity.User;
import com.erenildo.fakebank.auth.exceptions.RegraDeNegocioException;
import com.erenildo.fakebank.auth.repository.RoleRepository;
import com.erenildo.fakebank.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;


    public void createUser(UserCreateAccountDTO  dto) throws Exception{
        Role roleBasic = roleRepository.findByRoleId(Role.Values.BASIC.getRoleId());

        User user = objectMapper.convertValue(dto, User.class);
        user.setRoles(Set.of(roleBasic));
        userRepository.save(user);
    }
}

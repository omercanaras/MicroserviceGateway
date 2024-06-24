package com.omercan.gateway_app.model.service;

import com.omercan.gateway_app.model.entity.User;
import com.omercan.gateway_app.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public abstract class AbstractUserService implements EntityService<User, Integer>
{
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    public abstract Optional<User> findByUsername(String username);
}

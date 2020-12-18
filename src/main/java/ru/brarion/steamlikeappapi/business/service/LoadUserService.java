package ru.brarion.steamlikeappapi.business.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.brarion.steamlikeappapi.business.entity.User;

public interface LoadUserService extends UserDetailsService {

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;
}

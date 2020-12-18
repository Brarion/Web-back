package ru.brarion.steamlikeappapi.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.brarion.steamlikeappapi.business.entity.User;
import ru.brarion.steamlikeappapi.business.repository.UserRepository;
import ru.brarion.steamlikeappapi.business.service.LoadUserService;

@Service
@RequiredArgsConstructor
public class LoadUserServiceImpl implements LoadUserService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username " + username));
    }
}

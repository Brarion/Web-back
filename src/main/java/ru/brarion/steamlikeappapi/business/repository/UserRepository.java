package ru.brarion.steamlikeappapi.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brarion.steamlikeappapi.business.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}

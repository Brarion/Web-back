package ru.brarion.steamlikeappapi.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brarion.steamlikeappapi.business.entity.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}

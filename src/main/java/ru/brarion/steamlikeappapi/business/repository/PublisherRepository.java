package ru.brarion.steamlikeappapi.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brarion.steamlikeappapi.business.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

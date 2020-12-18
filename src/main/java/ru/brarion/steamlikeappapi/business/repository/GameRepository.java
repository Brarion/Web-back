package ru.brarion.steamlikeappapi.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brarion.steamlikeappapi.business.entity.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByNameStartsWith(String nameStart);

    List<Game> findAllByDeveloperNameStartsWith(String developerNameStart);

    List<Game> findAllByDeveloperId(Long id);

    List<Game> findAllByPublisherNameStartsWith(String publisherNameStart);

    List<Game> findAllByPublisherId(Long id);
}

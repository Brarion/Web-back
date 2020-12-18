package ru.brarion.steamlikeappapi.business.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameResponse {

    Long id;

    String name;

    Integer price;

    String description;

    DeveloperResponse developer;

    PublisherResponse publisher;
}

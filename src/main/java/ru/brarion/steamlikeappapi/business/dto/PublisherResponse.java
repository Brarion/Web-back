package ru.brarion.steamlikeappapi.business.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherResponse {

    Long id;

    String name;
}

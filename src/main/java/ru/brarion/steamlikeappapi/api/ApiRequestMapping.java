package ru.brarion.steamlikeappapi.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiRequestMapping {

    public final String PUBLIC_ENDPOINT = "api";

    public final String USER_ACCOUNTING = PUBLIC_ENDPOINT + "/account";

    public final String GAMES = PUBLIC_ENDPOINT + "/games";

    public final String DEVELOPERS = PUBLIC_ENDPOINT + "/developers";

    public final String PUBLISHERS = PUBLIC_ENDPOINT + "/publishers";
}

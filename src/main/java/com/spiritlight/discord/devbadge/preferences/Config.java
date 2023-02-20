package com.spiritlight.discord.devbadge.preferences;

public class Config {
    private String token;

    public Config(String token) {
        this.token = token;
    }

    private Config() {};

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

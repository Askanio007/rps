package org.artem.model;

import io.netty.channel.Channel;

public class Player {

    private final String playerId;
    private final Channel channel;
    private String name;
    private String gameId;
    private GameElement choice;

    public static Player of(String playerId, Channel channel) {
        return new Player(channel, playerId);
    }

    public Player(Channel channel, String playerId) {
        this.channel = channel;
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public Channel getChannel() {
        return channel;
    }

    public GameElement getChoice() {
        return choice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setChoice(GameElement choice) {
        this.choice = choice;
    }

    public String getPlayerId() {
        return playerId;
    }
}

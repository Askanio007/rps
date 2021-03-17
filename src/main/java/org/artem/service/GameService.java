package org.artem.service;

import org.artem.model.Pair;
import org.artem.model.Player;
import org.artem.util.ResponseUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.artem.model.Text.PLAYER_LEAVE;

public class GameService {

    private static final Map<String, Pair> games = new ConcurrentHashMap<>();

    public void add(String gameId, Pair pair) {
        games.put(gameId, pair);
    }

    public void remove(String gameId) {
        if (gameId == null) {
            return;
        }
        games.remove(gameId);
    }

    public Pair getPairPlayers(String gameId) {
        if (gameId == null) {
            return null;
        }

        return games.get(gameId);
    }

    public Player getEnemy(Player player) {
        if (player.getGameId() == null) {
            return null;
        }

        Pair pair = getPairPlayers(player.getGameId());
        if (pair == null) {
            return null;
        }
        return getEnemy(pair, player);
    }

    public void checkAndCleanGames(Player player) {
        Player enemy = getEnemy(player);
        if (enemy == null) {
            return;
        }

        remove(player.getGameId());
        ResponseUtil.sendResponse(PLAYER_LEAVE, enemy);
        enemy.getChannel().close();
    }

    private Player getEnemy(Pair pair, Player player) {
        if (pair.getOne().getPlayerId().equals(player.getPlayerId())) {
            return pair.getTwo();
        }
        return pair.getOne();
    }

}

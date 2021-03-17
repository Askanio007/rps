package org.artem.service;

import org.artem.model.Pair;
import org.artem.model.Player;
import org.artem.util.ResponseUtil;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.artem.model.Text.START_GAME;
import static org.artem.model.Text.WAITING_START_GAME;

public class WaitPlayerService {

    private static final AtomicReference<Player> waitPlayer = new AtomicReference<>();

    private GameService gameService;

    public WaitPlayerService(GameService gameService) {
        this.gameService = gameService;
    }

    public void startGame(Player player) {
        if (waitPlayer.get() == null) {
            waitPlayer.set(player);
            player.setGameId(UUID.randomUUID().toString());
            ResponseUtil.sendResponse(WAITING_START_GAME, player);
            return;
        }

        Player enemy = waitPlayer.getAndSet(null);
        player.setGameId(enemy.getGameId());
        gameService.add(enemy.getGameId(), Pair.of(enemy, player));
        ResponseUtil.sendResponse(START_GAME, enemy, player);
    }

    public void checkAndCleanWait(Player player) {
        Player wait = waitPlayer.get();
        if (wait != null && wait.getPlayerId().equals(player.getPlayerId())) {
            waitPlayer.set(null);
        }
    }


}

package org.artem.service;

import org.artem.model.GameElement;
import org.artem.model.Player;
import org.artem.util.ResponseUtil;

import static org.artem.model.Text.*;

public class GameProcessService {

    private GameService gameService;

    public GameProcessService(GameService gameService) {
        this.gameService = gameService;
    }

    public void start(Player player, String choice) {
        Player enemy = gameService.getEnemy(player);
        if (enemy == null || choice == null) {
            return;
        }

        choice = choice.trim();
        if (!GAME_ELEMENTS.contains(choice)) {
            ResponseUtil.sendResponse(ILLEGAL_CHOOSE, player);
            return;
        }

        player.setChoice(GameElement.getByValue(choice));

        if (enemy.getChoice() == null) {
            ResponseUtil.sendResponse(WAITING_DECISION_PLAYER, player);
            return;
        }

        int result = player.getChoice().compare(enemy.getChoice());
        player.setChoice(null);
        enemy.setChoice(null);

        ResponseUtil.sendGameResults(player, enemy, result);

        if (result != 0) {
            gameService.remove(player.getGameId());
            player.getChannel().close();
            enemy.getChannel().close();
        }

    }
}

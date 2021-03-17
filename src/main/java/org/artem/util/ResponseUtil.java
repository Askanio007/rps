package org.artem.util;

import org.artem.model.Player;

import java.util.stream.Stream;

import static org.artem.model.Text.*;

public class ResponseUtil {

    public static void sendResponse(String text, Player... players) {
        Stream.of(players)
                .forEach((p) -> sendResponse(text, p));
    }

    public static void sendResponse(String text, Player player) {
        player.getChannel().writeAndFlush(text);
    }

    public static void sendGameResults(Player one, Player two, int result) {
        String resultOne = GAME_OVER;
        String resultTwo = GAME_OVER;

        if (result != 0) {
            resultOne = result > 0 ? GAME_OVER_WIN : GAME_OVER_LOSS;
            resultTwo = result < 0 ? GAME_OVER_WIN : GAME_OVER_LOSS;
        }

        sendResponse(resultOne, one);
        sendResponse(resultTwo, two);
    }

}

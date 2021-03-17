package org.artem.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;
import org.artem.model.Player;
import org.artem.service.*;
import org.artem.util.ResponseUtil;

import java.util.UUID;

import static org.artem.model.Text.ENTER_NAME;
import static org.artem.model.Text.WELCOME;

public class PlayerHandler extends SimpleChannelInboundHandler<String> {

    private final GameService gameService = new GameService();
    private final PlayerService playerService = new PlayerService();
    private final WaitPlayerService waitPlayerService = new WaitPlayerService(gameService);
    private final GameProcessService gameProcessService = new GameProcessService(gameService);
    private Player currentPlayer;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        currentPlayer = Player.of(UUID.randomUUID().toString(), ctx.channel());
        ResponseUtil.sendResponse(WELCOME + ENTER_NAME, currentPlayer);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        if (StringUtils.isBlank(currentPlayer.getName())) {
            boolean success = playerService.updateName(s, currentPlayer);
            if (success) {
                waitPlayerService.startGame(currentPlayer);
            }
            return;
        }
        gameProcessService.start(currentPlayer, s);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        waitPlayerService.checkAndCleanWait(currentPlayer);
        gameService.checkAndCleanGames(currentPlayer);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}

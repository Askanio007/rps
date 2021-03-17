package org.artem.service;

import org.apache.commons.lang3.StringUtils;
import org.artem.model.Player;
import org.artem.util.ResponseUtil;

import static org.artem.model.Text.ENTER_NAME;
import static org.artem.model.Text.NAME_SAVED;

public class PlayerService {

    public boolean updateName(String name, Player player) {
        if (StringUtils.isBlank(name)) {
            ResponseUtil.sendResponse(ENTER_NAME, player);
            return false;
        }
        player.setName(name.trim());
        ResponseUtil.sendResponse(String.format(NAME_SAVED, player.getName()), player);
        return true;
    }
}

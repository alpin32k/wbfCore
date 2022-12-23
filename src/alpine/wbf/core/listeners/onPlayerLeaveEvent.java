package alpine.wbf.core.listeners;

import alpine.wbf.core.utils.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerLeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        event.setQuitMessage(MessageUtils.colorize("&8[&c-&8] &c" + event.getPlayer().getDisplayName()));
    }

}

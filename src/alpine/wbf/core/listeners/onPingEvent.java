package alpine.wbf.core.listeners;

import alpine.wbf.core.Core;
import alpine.wbf.core.utils.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class onPingEvent implements Listener {

    private String motdString;

    public onPingEvent() {
        motdString = Core.getCoreConfig().getMotd();
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(MessageUtils.colorize(motdString));
    }
}

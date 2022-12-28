package alpine.wbf.core.listeners;

import alpine.wbf.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class onPlayerRespawnEvent implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event)
    {
        event.setRespawnLocation(Core.getTeleportManager().spawnLocation);
    }

}

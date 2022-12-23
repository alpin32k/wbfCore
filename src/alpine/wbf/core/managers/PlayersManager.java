package alpine.wbf.core.managers;

import alpine.wbf.core.Core;
import alpine.wbf.core.data.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayersManager {

    public HashMap<UUID, CorePlayer> uuidMap;

    public PlayersManager() {
        this.uuidMap = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            registerPlayer(player);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                HashMap<UUID, CorePlayer> newMap = new HashMap<>(uuidMap);
                for (UUID uuid : uuidMap.keySet()) {
                    if (Bukkit.getPlayer(uuid) == null) {
                        uuidMap.get(uuid).savePlayerFile();
                        newMap.remove(uuid);
                    }
                }
                uuidMap = newMap;
            }
        }.runTaskTimer(Core.getInstance(), 20 * 60 * 10, 20 * 60 * 10);
    }

    public CorePlayer registerPlayer(Player player) {
        if (!uuidMap.containsKey(player.getUniqueId())) {
            uuidMap.put(player.getUniqueId(), new CorePlayer(player));
        }
        return uuidMap.get(player.getUniqueId());
    }

    public void disable() {
        for (CorePlayer user : uuidMap.values()) {
            user.savePlayerFile();
        }

        Core.getInstance().logToConsole("&2[PlayerManager] Save the player data files.");

    }

    public CorePlayer getUser(Player player) {
        return uuidMap.get(player.getUniqueId());
    }

}

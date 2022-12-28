package alpine.wbf.core.managers;

import alpine.wbf.core.Core;
import alpine.wbf.core.commands.teleport.TeleportWrapper;
import alpine.wbf.core.data.CFile;
import alpine.wbf.core.utils.Messages;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {

    private final CFile configFile;
    @Getter
    public HashMap<UUID, TeleportWrapper>   teleportQueue = new HashMap<UUID, TeleportWrapper>();
    @Getter
    public HashMap<UUID, Location>          lastTeleports = new HashMap<UUID, Location>();

    public Location spawnLocation;


    public TeleportManager()
    {
        this.configFile = Core.getCoreConfig().getConfigFile();
        loadSpawn();
        createTeleportTask();
    }

    public void teleportPlayer(String teleportName, Player player, Location toTeleport, Integer cooldown)
    {
        lastTeleports.putIfAbsent(player.getUniqueId(), player.getLocation());

        TeleportWrapper tw = new TeleportWrapper(teleportName, player, player.getLocation(), toTeleport, cooldown);

        this.getTeleportQueue().putIfAbsent(player.getUniqueId(), tw);
    }


    private void createTeleportTask()
    {
        Bukkit.getScheduler().runTaskTimer(Core.getInstance(), () ->
        {
            for (Map.Entry<UUID, TeleportWrapper> entry : teleportQueue.entrySet())
            {
                final Player player = Bukkit.getPlayer(entry.getKey());
                final TeleportWrapper teleportWrapper = entry.getValue();
                assert player != null;

                if (!teleportWrapper.hasMoved())
                {
                    if (teleportWrapper.getCountdown() == 0)
                    {
                        player.teleport(teleportWrapper.getToTeleport());
                        Messages.TELEPORT_WRAPPER_TELEPORTED.send(player, teleportWrapper.teleportName);
                        teleportQueue.remove(player.getUniqueId());
                    }
                    else
                    {
                        Messages.TELEPORT_WRAPPER_QUEUE.send(player, teleportWrapper.getCountdown().toString(), teleportWrapper.teleportName);
                        teleportWrapper.decreaseCountdown();
                    }
                }
                else
                {
                    Messages.TELEPORT_WRAPPER_MOVED.send(player, teleportWrapper.teleportName);
                    teleportQueue.remove(player.getUniqueId());
                }
            }
        }, 0L, 20L);
    }

    private void loadSpawn()
    {
        if(configFile.getFile().isSet("spawn")){
            ConfigurationSection section = configFile.getFile().getConfigurationSection("spawn");
            this.spawnLocation = new Location(
                    Bukkit.getWorld(section.getString("world")),
                    section.getDouble("x"),
                    section.getDouble("y"),
                    section.getDouble("z"),
                    (float) section.getDouble("yaw"),
                    (float) section.getDouble("pitch")
            );
            Core.getInstance().logToConsole("&2[TeleportManager] Spawn load successful");

        }
    }

    public void setSpawnLocation(Location spawn)
    {
        this.spawnLocation = spawn;
        ConfigurationSection section = configFile.getFile();
        section.set("spawn.world", spawn.getWorld().getName());
        section.set("spawn.x", spawn.getX());
        section.set("spawn.y", spawn.getY());
        section.set("spawn.z", spawn.getZ());
        section.set("spawn.yaw", spawn.getYaw());
        section.set("spawn.pitch", spawn.getPitch());

        configFile.saveFile();
    }
}

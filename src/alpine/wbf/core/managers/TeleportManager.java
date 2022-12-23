package alpine.wbf.core.managers;

import alpine.wbf.core.Core;
import alpine.wbf.core.data.CFile;
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
    public HashMap<UUID, Long>     teleportQueue = new HashMap<UUID, Long>();
    @Getter
    public HashMap<UUID, Location> lastTeleports = new HashMap<UUID, Location>();

    public Location spawnLocation;


    public TeleportManager()
    {
        this.configFile = Core.getCoreConfig().getConfigFile();
        loadSpawn();
    }

    public void teleportPlayer(Player player, Location playerLoc, Location toTeleport, Integer cooldown)
    {
        lastTeleports.putIfAbsent(player.getUniqueId(), player.getLocation());
        this.getTeleportQueue().putIfAbsent(player.getUniqueId(), System.currentTimeMillis());
    }


    private void createTeleportTask()
    {
        Core.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getConsoleSender().sendMessage();
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

package alpine.wbf.core.data;

import alpine.wbf.core.Core;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class CorePlayer {

    @Getter @Setter
    private final String nick;
    @Getter @Setter
    private final Long nextFix;
    private final UUID uuid;

    private @Getter
    HashMap<String, Location> homes;
    @Getter @Setter
    private CFile playerFile;

    public CorePlayer(Player player)
    {
        UUID uuid = player.getUniqueId();
        this.uuid = uuid;
        this.setPlayerFile(new CFile(Core.getInstance(), "players/" + player.getUniqueId(), false));

        if (playerFile.getFile().isSet("nick")) {
            this.nick = playerFile.getFile().getString("nick");
        } else {
            this.nick = player.getDisplayName();
        }
        if (playerFile.getFile().isSet("nick")) {
            this.nextFix = playerFile.getFile().getLong("nextFix");
        } else {
            this.nextFix = 0L;
        }

        loadHomes();

    }

    public void savePlayerFile()
    {
        playerFile.getFile().set("nick", getNick());
        playerFile.getFile().set("nextFix", getNextFix());

        saveHomes();
        playerFile.saveFile();
    }
    private void loadHomes() {
        homes = new HashMap<>();
        if (playerFile.getFile().isSet("homes")) {
            for (String string : Objects.requireNonNull(playerFile.getFile().getConfigurationSection("homes")).getKeys(false)) {
                String[] split = Objects.requireNonNull(playerFile.getFile().getString("homes." + string)).split(";");
                String worldName;
                double x, y, z;
                float yaw, pitch;
                Location location;
                try {
                    worldName = split[0];
                    x = Double.parseDouble(split[1]);
                    y = Double.parseDouble(split[2]);
                    z = Double.parseDouble(split[3]);

                    yaw = Float.parseFloat(split[4]);
                    pitch = Float.parseFloat(split[5]);
                    location = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
                } catch (Exception ex) {
                    Bukkit.getLogger().info("Player " + uuid + " has a corrupted home (" + string + ").");
                    Bukkit.getLogger().info("Log:");
                    ex.printStackTrace();
                    continue;
                }
                homes.put(string.toLowerCase(), location);

            }
        }
    }

    private void saveHomes() {
        playerFile.getFile().set("homes", null);
        for (String homeName : homes.keySet()) {
            Location location = homes.get(homeName);
            playerFile.getFile().set("homes." + homeName + "", location.getWorld().getName() + ";" +
                    location.getX() + ";" +
                    location.getY() + ";" +
                    location.getZ() + ";" +
                    location.getYaw() + ";" +
                    location.getPitch());
        }
    }


    public static int getHomeAmount(Player player) {
        for (int i = 100; i > 0; i--) {
            if (player.hasPermission("wbfCore.teleport.home." + i)) return i;
        }
        return 0;
    }
}

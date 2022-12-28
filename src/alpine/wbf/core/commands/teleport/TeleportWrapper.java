package alpine.wbf.core.commands.teleport;

import alpine.wbf.core.managers.TeleportManager;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportWrapper {

    public String teleportName;
    @Getter
    public Player player;
    @Getter
    public Location playerLocation;
    @Getter
    public Location toTeleport;
    @Getter
    public Integer countdown;

    public TeleportWrapper(String teleportName, Player player, Location playerLocation, Location toTeleport, Integer countdown)
    {
        this.teleportName = teleportName;
        this.player = player;
        this.playerLocation = playerLocation;
        this.toTeleport = toTeleport;
        this.countdown = countdown;
    }

    public void decreaseCountdown()
    {
        this.countdown = getCountdown()-1;
    }

    public boolean hasMoved()
    {
        double xFrom = playerLocation.getX();
        double yFrom = playerLocation.getY();
        double zFrom = playerLocation.getZ();

        double xActual = player.getLocation().getX();
        double yActual = player.getLocation().getY();
        double zActual = player.getLocation().getZ();

        if((xFrom != xActual) || (yFrom != yActual) || (zFrom != zActual)){
            return true;
        }

        return false;
    }
}

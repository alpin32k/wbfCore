package alpine.wbf.core.commands.teleport;

import alpine.wbf.core.Core;
import alpine.wbf.core.utils.Messages;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("wbfCore.teleport.back")) {
            Messages.PERMISSION_DENIED.send((Player) sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
            return true;
        }
        if (args.length != 0) {
            Messages.INVALID_ARGS.send((Player) sender, "/" + label);
            return true;
        }

        Player player = (Player) sender;
        Location lastLocation = Core.getTeleportManager().lastTeleports.get(player.getUniqueId());
        if (lastLocation == null) {
            Messages.BACK_NOTFOUND.send(player);
            return true;
        }
//        TODO: Dopisać teleport jak skończę teleport managera
        Messages.BACK_SUCCESS.send(player);
        return false;
    }
}

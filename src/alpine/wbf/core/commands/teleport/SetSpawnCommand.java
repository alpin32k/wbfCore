package alpine.wbf.core.commands.teleport;

import alpine.wbf.core.Core;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("wbfCore.teleport.setspawn")) {
            Messages.PERMISSION_DENIED.send((Player) sender);
            return true;
        }
        if (args.length > 0) {
            Messages.INVALID_ARGS.send((Player) sender, "/" + label);
            return true;
        }
        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
            return true;
        }
        Player player = (Player) sender;
        Core.getTeleportManager().setSpawnLocation(player.getLocation());
        Messages.SETSPAWN_SUCCESS.send(player);

        return true;
    }
}

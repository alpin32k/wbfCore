package alpine.wbf.core.commands.teleport;

import alpine.wbf.core.Core;
import alpine.wbf.core.managers.TeleportManager;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            Messages.INVALID_ARGS.send((Player) sender, "/" + label + " [player]");
            return true;
        }
        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
            return true;
        }
        if (Core.getTeleportManager().spawnLocation == null) {
            Messages.SPAWN_MISSING.send((Player) sender);
            return true;
        }
        Player player = (Player) sender;

        TeleportManager tm = Core.getTeleportManager();

        tm.teleportPlayer("spawn", player, tm.spawnLocation, 5);
        return true;
    }
}
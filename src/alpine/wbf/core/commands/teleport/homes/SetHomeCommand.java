package alpine.wbf.core.commands.teleport.homes;

import alpine.wbf.core.Core;
import alpine.wbf.core.data.CorePlayer;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
            return true;
        }
        if (args.length != 1) {
            Messages.INVALID_ARGS.send((Player) sender, "/" + label + " <home name>");
            return true;
        }
        Player player = (Player) sender;
        CorePlayer user = Core.getPlayersManager().getUser(player);

        int homeAmount = user.getHomeAmount(player);
        String homeName = args[0].toLowerCase();

        user.getHomes().remove(homeName);

        if (user.getHomes().size() >= homeAmount) {
            Messages.SETHOME_LIMIT.send((Player) sender, homeAmount + "");
            return true;
        }

        user.getHomes().put(homeName, player.getLocation());
        Messages.SETHOME_SUCCESS.send((Player) sender, homeName);
        return true;
    }
}
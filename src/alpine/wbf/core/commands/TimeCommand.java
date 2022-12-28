package alpine.wbf.core.commands;

import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("wbfCore.time")) {
            Messages.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;

        if(args.length != 1){
            Messages.INVALID_ARGS.send(player, "/" + label + " <day/noon/night/#>");
            return true;
        }

        if (args[0].equalsIgnoreCase("day")) {
            player.getWorld().setTime(0);
            Messages.TIME_SET.send(player, "dzień (0)");
            return true;
        }

        if (args[0].equalsIgnoreCase("noon")) {
            player.getWorld().setTime(6000);
            Messages.TIME_SET.send(player, "wieczór (6000)");
            return true;
        }

        if (args[0].equalsIgnoreCase("night")) {
            player.getWorld().setTime(13000);
            Messages.TIME_SET.send(player, "noc (13000)");
            return true;
        }

        int time;
        try {
            time = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            Messages.INVALID_ARGS_NOPREFIX.send(player, "/" + label + " <day/noon/night/#>");
            return true;
        }
        player.getWorld().setTime(time);
        Messages.TIME_SET.send(player, time + "");

        return true;
    }
}

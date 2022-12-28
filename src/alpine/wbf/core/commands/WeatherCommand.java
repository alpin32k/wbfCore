package alpine.wbf.core.commands;

import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("wbfCore.weather")) {
            Messages.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;

        if(args.length != 1){
            Messages.INVALID_ARGS.send(sender, "/" + label + " <clear/rain>");
            return true;
        }

        if (args[0].equalsIgnoreCase("rain")) {
            player.getWorld().setWeatherDuration(60);
            Messages.WEATHER_SET.send(player, "deszczową");
            return true;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            player.getWorld().setClearWeatherDuration(600);
            Messages.WEATHER_SET.send(player, "słoneczną");
            return true;
        }

        Messages.INVALID_ARGS.send(sender, "/" + label + " <clear/rain>");


        return true;
    }
}

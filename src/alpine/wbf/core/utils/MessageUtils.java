package alpine.wbf.core.utils;

import alpine.wbf.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static String colorize(String msg)
    {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendMessage(Player player, String message)
    {
        player.sendMessage(colorize(message));
    }

    public static void sendPrefixedMessage(Player player, String message) {
        player.sendMessage(colorize(Core.getCoreConfig().prefix + " " + message));

    }
}

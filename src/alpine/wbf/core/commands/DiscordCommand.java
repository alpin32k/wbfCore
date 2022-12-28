package alpine.wbf.core.commands;

import alpine.wbf.core.Core;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String... args) {
        Messages.DISCORD_SERVER.send(sender, Core.getCoreConfig().getConfigFile().getFile().getString("discord-inv-link"));
        return true;
    }
}
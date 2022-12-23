package alpine.wbf.core.commands;

import alpine.wbf.core.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player)
        {

            MessageUtils.sendMessage((Player) sender, "");
            MessageUtils.sendMessage((Player) sender, "                 &c&l-=| PLAYER SECTION |=-");
            MessageUtils.sendMessage((Player) sender, "");
            MessageUtils.sendMessage((Player) sender, "&6/help        &7- tutaj jesteś");
            MessageUtils.sendMessage((Player) sender, "&6/spawn      &7- teleport na spawn (nie możesz się ruszać)");
            MessageUtils.sendMessage((Player) sender, "&6/craft      &7- otwiera przenośny crafting table");
            MessageUtils.sendMessage((Player) sender, "");
            MessageUtils.sendMessage((Player) sender, "");

            if(sender.hasPermission("wbfCore.admin")){
                MessageUtils.sendMessage((Player) sender, "");
                MessageUtils.sendMessage((Player) sender, "                 &c&l-=| ADMIN SECTION |=-");
                MessageUtils.sendMessage((Player) sender, "&c/openinv      &3<player>&7- otwiera ekwipunek gracza");
                MessageUtils.sendMessage((Player) sender, "&c/openechest  &3<player>&7- otwiera enderchest gracza");
                MessageUtils.sendMessage((Player) sender, "");
            }

            return true;
        }

        return false;
    }
}

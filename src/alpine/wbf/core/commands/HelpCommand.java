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

            MessageUtils.sendMessage(sender, "");
            MessageUtils.sendMessage(sender, "                 &c&l-=| PLAYER SECTION |=-");
            MessageUtils.sendMessage(sender, "");
            MessageUtils.sendMessage(sender, "&6/help        &7- tutaj jesteś");
            MessageUtils.sendMessage(sender, "&6/spawn      &7- teleport na spawn (nie możesz się ruszać)");
            MessageUtils.sendMessage(sender, "&6/craft      &7- otwiera przenośny crafting table");
            MessageUtils.sendMessage(sender, "");
            MessageUtils.sendMessage(sender, "");

            if(sender.hasPermission("wbfCore.admin")){
                MessageUtils.sendMessage(sender, "");
                MessageUtils.sendMessage(sender, "                 &c&l-=| ADMIN SECTION |=-");
                MessageUtils.sendMessage(sender, "&c/openinv        &3<player>&7- otwiera ekwipunek gracza");
                MessageUtils.sendMessage(sender, "&c/openechest   &3<player>&7- otwiera enderchest gracza");
                MessageUtils.sendMessage(sender, "&c/time   &3<day/noon/night>&7- ustawia godzinę na serwerze");
                MessageUtils.sendMessage(sender, "&c/weather   &3<clear/rain>&7- ustawia pogodę na serwerze");
                MessageUtils.sendMessage(sender, "");
            }

            return true;
        }

        return false;
    }
}

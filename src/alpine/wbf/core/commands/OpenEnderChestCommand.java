package alpine.wbf.core.commands;

import alpine.wbf.core.utils.MessageUtils;
import alpine.wbf.core.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenEnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if(!sender.hasPermission("wbfCore.admin.open.enderchest")){
//                MessageUtils.sendMessage((Player) sender, Messages.PERMISSION_DENIED.getValue());
                return true;
            }

            Player p = (Player) sender;

            if(args.length == 0) p.openInventory(p.getEnderChest());

            if(args.length == 1){
                if(!p.hasPermission("wbfCore.admin.open.enderchest.other")) {
//                    MessageUtils.sendMessage(p, Messages.PERMISSION_DENIED.getValue());
                    Messages.PERMISSION_DENIED.send(p);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    Messages.ECHEST_INVALID_PLAYER.send(p, args[0]);
                    return false;
                }

                p.openInventory(target.getEnderChest());
                Messages.ECHEST_OPEN_OTHER.send((Player) sender, target.getName());
                return true;
            }
            return true;
        }else{
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
        }

        Messages.INVALID_ARGS.send((Player) sender, "/" + label + " &3[player]");
        return false;
    }
}

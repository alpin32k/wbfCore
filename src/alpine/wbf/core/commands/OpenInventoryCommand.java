package alpine.wbf.core.commands;

import alpine.wbf.core.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(!sender.hasPermission("wbfCore.admin.open.inventory")){
                Messages.PERMISSION_DENIED.send(sender);

                return true;
            }



            if(args.length == 0) p.openInventory(p.getInventory());

            if(args.length == 1){
                if(!p.hasPermission("wbfCore.admin.open.inventory.other")) {
                    Messages.PERMISSION_DENIED.send(p);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    Messages.INVSEE_OPEN.send(p, args[0]);
                    return false;
                }

                p.openInventory(target.getInventory());
                Messages.INVSEE_OPEN.send(sender, target.getName());
                return true;
            }
            return true;
        }else{
            Messages.CONSOLE_SENDER_ERROR.send(sender);
        }

        Messages.INVALID_ARGS.send(sender, "/" + label + " &3[player]");
        return false;
    }
}

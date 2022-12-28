package alpine.wbf.core.commands;

import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("wbfCore.craft")){
            Messages.PERMISSION_DENIED.send(sender);
            return false;
        }

        if(!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send(sender);
            return false;
        }

        Player p = (Player) sender;

        if(args.length != 0){
            Messages.INVALID_ARGS.send(p, "/" + label);
            return false;
        }

        p.openWorkbench(null, true);
        Messages.CRAFT_SUCCESS.send(p);

        return true;
    }

}

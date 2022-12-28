package alpine.wbf.core.commands;

import alpine.wbf.core.Core;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class testChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("wbfCore.admin.chatoff")) {
            Messages.PERMISSION_DENIED.send(sender);
        }
        Core.getChatManager().setChatEnable(false);

        sender.sendMessage("Wyłaczono chat");
        return false;
    }

}

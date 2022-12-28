package alpine.wbf.core.commands.teleport.homes;

import alpine.wbf.core.Core;
import alpine.wbf.core.data.CorePlayer;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
            return true;
        }

        if (args.length == 0 || args.length > 2) {
            Messages.INVALID_ARGS.send(player, "/" + label + " <nazwa domu | add/dodaj | del/usun | list/lista> [name]");
            return true;
        }

        CorePlayer user = Core.getPlayersManager().getUser(player);

        if(args[0].toLowerCase().equals("list") || args[0].toLowerCase().equals("lista")) {
            StringBuilder sb = new StringBuilder();
            for (String string : user.getHomes().keySet()) {
                sb.append(string).append("&7,&6 ");
            }
            if (sb.length() <= 0) {
                Messages.HOME_NONE.send(player);
                return true;
            }

            String built = sb.toString().trim();
            built = built.substring(0, built.length() - 3);
            Messages.HOME_LIST.send(player, built);

            return true;
        }

        if(args[0].toLowerCase().equals("add") || args[0].toLowerCase().equals("dodaj")) {
            if(args.length == 1){
                Messages.INVALID_ARGS.send(player, "/" + label + " <nazwa domu | add/dodaj | del/usun | list/lista> [name]");
                return true;
            }
            int homeAmount = user.getHomeAmount(player);
            String homeName = args[1].toLowerCase();

            if(homeName.equals("add") || homeName.equals("list") || homeName.equals("del")) {
                Messages.SETHOME_INVALID_NAME.send(player, homeName);
                return true;
            }

            if (user.getHomes().containsKey(homeName)) {
//                user.getHomes().remove(homeName);
                Messages.SETHOME_BUSY_NAME.send(player, homeName);
                return true;
            }

            if (user.getHomes().size() >= homeAmount) {
                Messages.SETHOME_LIMIT.send(player, homeAmount + "");
                return true;
            }

            user.getHomes().put(homeName, player.getLocation());
            Messages.SETHOME_SUCCESS.send(player, homeName);
            return true;
        }

        if(args[0].toLowerCase().equals("del") || args[0].toLowerCase().equals("usun") || args[0].toLowerCase().equals("remove")) {
            if(args.length == 1){
                Messages.INVALID_ARGS.send(player, "/" + label + " <nazwa domu | add/dodaj | del/usun | list/lista> [name]");
                return true;
            }
            String homeName = args[1].toLowerCase();

            if (user.getHomes().containsKey(homeName)) {
                user.getHomes().remove(homeName);
                Messages.DELHOME_SUCCESS.send(player, homeName);
                return true;
            }
            Messages.DELHOME_INVALID.send(player, homeName);
            return true;
        }

        String homeName = args[0].toLowerCase();
        if (user.getHomes().containsKey(homeName)) {
            Core.getTeleportManager().teleportPlayer("Dom: " + homeName, player, user.getHomes().get(homeName), 5);
            return true;
        }

        return true;
    }
}

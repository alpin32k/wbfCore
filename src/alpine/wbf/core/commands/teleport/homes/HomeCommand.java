package alpine.wbf.core.commands.teleport.homes;

import alpine.wbf.core.Core;
import alpine.wbf.core.commands.teleport.TeleportWrapper;
import alpine.wbf.core.data.CorePlayer;
import alpine.wbf.core.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Messages.CONSOLE_SENDER_ERROR.send((Player) sender);
            return true;
        }
        if (args.length == 0 || args.length > 2) {
            Messages.INVALID_ARGS.send((Player) sender, "/" + label + " <nazwa domu | add | del | list> [name]");
            return true;
        }

        Player p = (Player) sender;

        CorePlayer cp = Core.getPlayersManager().getUser(p);
        if(args[0].toLowerCase().equals("list")) {
            StringBuilder sb = new StringBuilder();
            for (String string : cp.getHomes().keySet()) {
                sb.append(string).append("&7,&6 ");
            }
            if (sb.length() <= 0) {
                Messages.HOME_NONE.send(p);
                return true;
            }

            String built = sb.toString().trim();
            built = built.substring(0, built.length() - 3);
            Messages.HOME_LIST.send(p, built);

            return true;
        }

        Player player = (Player) sender;
        CorePlayer user = Core.getPlayersManager().getUser(player);

        if(args[0].toLowerCase().equals("add")) {



            int homeAmount = user.getHomeAmount(player);
            String homeName = args[1].toLowerCase();

            if(homeName.equals("add") || homeName.equals("list") || homeName.equals("del")) {
                Messages.SETHOME_INVALID_NAME.send(player, homeName);
                return true;
            }

            if (user.getHomes().containsKey(homeName)) {
                user.getHomes().remove(homeName);
            }

            if (user.getHomes().size() >= homeAmount) {
                Messages.SETHOME_LIMIT.send((Player) sender, homeAmount + "");
                return true;
            }

            user.getHomes().put(homeName, player.getLocation());
            Messages.SETHOME_SUCCESS.send((Player) sender, homeName);
            return true;
        }

        if(args[0].toLowerCase().equals("del")) {
            String homeName = args[1].toLowerCase();

            if (user.getHomes().containsKey(homeName)) {
                user.getHomes().remove(homeName);
                Messages.DELHOME_SUCCESS.send((Player) sender, homeName);
                return true;
            }
            Messages.DELHOME_INVALID.send((Player) sender, homeName);
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

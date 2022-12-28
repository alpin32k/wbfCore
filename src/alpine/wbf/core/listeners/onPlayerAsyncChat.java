package alpine.wbf.core.listeners;

import alpine.wbf.core.Core;
import alpine.wbf.core.utils.MessageUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.messenger.message.Message;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;
import java.util.UUID;

public class onPlayerAsyncChat implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAsyncChat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();

        if(!Core.getChatManager().chatEnable){
            event.setCancelled(true);
            p.sendMessage(MessageUtils.colorize("&c BŁĄD CZAT WYŁACZONY"));

        }


        event.setFormat(MessageUtils.colorize("&7" + p.getDisplayName() + ": " + event.getMessage()));

        if(checkRank(p, "mody-dzik")){
            event.setFormat(MessageUtils.colorize("&7[&3DZIK&7] &3" + event.getPlayer().getDisplayName() + "&7: " + event.getMessage()));
        }

        if (checkRank(p, "dzik")) {
            event.setFormat(MessageUtils.colorize("&7[&6DZIK&7] &4" + event.getPlayer().getDisplayName() + "&7: " + event.getMessage()));
        }

    }


    public static boolean checkRank(Player player, String group) {
        return player.hasPermission("group." + group);
    }

}

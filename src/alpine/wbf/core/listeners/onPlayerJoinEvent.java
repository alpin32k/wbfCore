package alpine.wbf.core.listeners;

import alpine.wbf.core.Core;
import alpine.wbf.core.data.CorePlayer;
import alpine.wbf.core.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class onPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoinPreEvent(PlayerLoginEvent event)
    {
        Player p = event.getPlayer();
        if(p instanceof Player){
            try {
                CorePlayer pd = Core.getPlayersManager().registerPlayer(p);
                Core.getInstance().logToConsole(pd.getNick());
                p.setDisplayName(MessageUtils.colorize(pd.getNick()));
            } catch (Exception ex) {
                event.setKickMessage(MessageUtils.colorize("There was an error loading your player data. Please contact an admin immediately."));
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
//        TODO: tutaj ustawienie spawna
//        if (!player.hasPlayedBefore()) {
//            player.setBedSpawnLocation(Core.getInstance().spawn);
//            player.teleport(Core.getInstance().spawn);
//        }

        event.setJoinMessage(MessageUtils.colorize("&l&7[&a+&l&7] " + event.getPlayer().getDisplayName()));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                for (int i = 0; i < 25; i++) {
                    MessageUtils.sendMessage(player, "");
                }

                for (int i = 0; i < Core.getCoreConfig().welcomeMessages.size(); i++) {

                    String string = Core.getCoreConfig().welcomeMessages.get(i).replaceAll("%PREFIX%", Core.getCoreConfig().prefix).replaceAll("%PLAYERNAME%",event.getPlayer().getDisplayName());
                    MessageUtils.sendMessage(player, string);
                }
            }
        }.runTaskLater(Core.getInstance(), 2);
    }

}

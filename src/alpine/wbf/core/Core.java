package alpine.wbf.core;

import alpine.wbf.core.commands.*;
import alpine.wbf.core.commands.tabCompleters.HomeTabCompleter;
import alpine.wbf.core.commands.tabCompleters.TimeTabCompleter;
import alpine.wbf.core.commands.tabCompleters.WeatherTabCompleter;
import alpine.wbf.core.commands.teleport.BackCommand;
import alpine.wbf.core.commands.teleport.SetSpawnCommand;
import alpine.wbf.core.commands.teleport.SpawnCommand;
import alpine.wbf.core.commands.teleport.homes.HomeCommand;
import alpine.wbf.core.commands.teleport.homes.SetHomeCommand;
import alpine.wbf.core.data.CoreConfig;
import alpine.wbf.core.listeners.*;
import alpine.wbf.core.managers.ChatManager;
import alpine.wbf.core.managers.PlayersManager;
import alpine.wbf.core.managers.TeleportManager;
import alpine.wbf.core.utils.MessageUtils;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {


    private static @Getter
    Core instance;

    private static @Getter
    CoreConfig coreConfig;

    private static @Getter
    PlayersManager playersManager;

    private static @Getter
    TeleportManager teleportManager;

    private static @Getter
    ChatManager chatManager;

    private @Getter
    String version;

    public @Getter LuckPerms lpAPI;

    @Override
    public void onEnable()
    {
        this.version = getDescription().getVersion();

        instance = this;
        coreConfig = new CoreConfig(this);
        this.logToConsole("&2[CoreConfig] load successful");
        playersManager = new PlayersManager();
        this.logToConsole("&2[PlayerManager] load successful");
        teleportManager = new TeleportManager();
        this.logToConsole("&2[TeleportManager] load successful");
        chatManager = new ChatManager();
        this.logToConsole("&2[ChatManager] load successful");

        registerEvents();
        registerCommands();

// LuckPerms initiate
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            this.lpAPI = provider.getProvider();
        }


        this.logToConsole("");
        this.logToConsole("&6  ????????????????????????????????? ");
        this.logToConsole("&6  ?????????????????????????????????  &4WBFCore &3v" + version);
        this.logToConsole("&6  ??????????????????????????????   &7Running on Bukkit - Paper");
        this.logToConsole("&6  ???????????????????????????   ");
        this.logToConsole("");

    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(String.format(MessageUtils.colorize(coreConfig.getPrefix() + " v%s plugin disabled") , version));
        coreConfig.saveConfig();
        playersManager.disable();
    }

    private void registerEvents()
    {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new onPlayerJoinEvent(), this);
        pluginManager.registerEvents(new onPlayerLeaveEvent(), this);
        pluginManager.registerEvents(new onPingEvent(), this);
        pluginManager.registerEvents(new onPlayerRespawnEvent(), this);
        pluginManager.registerEvents(new onPlayerAsyncChat(), this);

        this.logToConsole("&2Events load successful");
    }

    public void registerCommands()
    {
        // Players commands
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("discord").setExecutor(new DiscordCommand());

        // wbfCore.craft
        this.getCommand("craft").setExecutor(new CraftCommand());

        // wbfCore.teleport. (powr??t)
//        this.getCommand("back").setExecutor(new BackCommand());
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
//        Ilo???? domk??w zale??na od permisji wbfCore.teleport.homes.<ilosc> do 100 max
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("home").setTabCompleter(new HomeTabCompleter());


        this.getCommand("time").setExecutor(new TimeCommand());
        this.getCommand("time").setTabCompleter(new TimeTabCompleter());

        this.getCommand("weather").setExecutor(new WeatherCommand());
        this.getCommand("weather").setTabCompleter(new WeatherTabCompleter());

        this.getCommand("sethome").setExecutor(new SetHomeCommand());

        // Admin Commands
        this.getCommand("openechest").setExecutor(new OpenEnderChestCommand());
        this.getCommand("openinv").setExecutor(new OpenInventoryCommand());



        this.getCommand("chatoff").setExecutor(new testChat());

        this.logToConsole("&2Commands load successful");
    }

    public void logToConsole(String message) {
        logToConsole(message, false);
    }

    public void logToConsole(String message, boolean prefixed)
    {
        if(prefixed){
            Bukkit.getConsoleSender().sendMessage(MessageUtils.colorize(coreConfig.getPrefix() + " " + message));
        }else{
            Bukkit.getConsoleSender().sendMessage(MessageUtils.colorize(message));
        }
    }

}

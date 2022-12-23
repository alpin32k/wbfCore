package alpine.wbf.core.data;

import alpine.wbf.core.Core;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CoreConfig {

    private final Core plugin;

    @Getter @Setter
    public CFile configFile;
    @Getter @Setter
    public String prefix, discordInvLink, motd;

    @Getter @Setter
    public List<String> welcomeMessages;
    private int homesLimit;


    public CoreConfig(Core plugin)
    {
        this.plugin = plugin;
        this.configFile = new CFile(this.plugin, "config", true);

        this.loadConfig();
    }

    private void loadConfig() {
        this.prefix = configFile.getFile().getString("prefix");
        this.discordInvLink = configFile.getFile().getString("discord-inv-link");
        this.welcomeMessages = configFile.getFile().getStringList("welcomeMessages");
        this.motd = configFile.getFile().getString("motd");
        this.homesLimit = configFile.getFile().getInt("homesLimit");
    }

    public void saveConfig()
    {
        this.configFile.getFile().set("prefix", this.prefix);
        this.configFile.getFile().set("welcomeMessages", this.welcomeMessages);
        this.configFile.getFile().set("discord-inv-link", this.discordInvLink);
        this.configFile.getFile().set("motd", this.motd);
        this.configFile.getFile().set("homesLimit", this.homesLimit);
        this.configFile.saveFile();
        this.plugin.logToConsole("&2[CoreConfig] Config save successful");
    }

}

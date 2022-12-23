package alpine.wbf.core.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class CFile {

    private final Plugin plugin;
    private final String id;
    private final File file;
    private YamlConfiguration yaml;

    public CFile(Plugin plugin, String id, boolean copyFile) {
        this.plugin = plugin;
        this.id = id;
        this.file = new File(plugin.getDataFolder(), id + ".yml");

        if (copyFile) {
            plugin.saveResource(id + ".yml", false);
        } else {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Bukkit.getLogger().info("Could not create file '" + id + ".yml' for plugin " + plugin.getName() + ".");
                Bukkit.getLogger().warning(ex.getMessage());
            }
        }

        yaml = YamlConfiguration.loadConfiguration(file);

    }

    public YamlConfiguration getFile() {
        return yaml;
    }

    public void saveFile() {
        try {
            yaml.save(file);
        } catch (IOException ex) {
            Bukkit.getLogger().info("Could not save file '" + id + ".yml' for plugin " + plugin.getName() + ".");
        }
    }

    public ConfigurationSection getSection(String sectionName)
    {
        return this.getFile().getConfigurationSection(sectionName);
    }

    public void reloadFile() {
        yaml = YamlConfiguration.loadConfiguration(file);
    }
}
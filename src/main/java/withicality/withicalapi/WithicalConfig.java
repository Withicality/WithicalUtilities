package withicality.withicalapi;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WithicalConfig {
    private JavaPlugin plugin;

    private HashMap<String, File> configFile;
    private HashMap<String, FileConfiguration> config;


    public WithicalConfig(JavaPlugin plugin, String... configs) {
        this.plugin = plugin;
        configFile = new HashMap<>();
        config = new HashMap<>();
        createCustomConfig(configs);
    }

    public FileConfiguration getCustomConfig(String filename) {
        return config.get(filename);
    }
    public File getConfigFile(String filename) { return configFile.get(filename); }
    private void createCustomConfig(String... filenames) {
        for (String filename : filenames) {
            configFile.put(filename, new File(plugin.getDataFolder(), filename + ".yml"));
            if (!configFile.get(filename).exists()) {
                configFile.get(filename).getParentFile().mkdirs();
                plugin.saveResource(filename + ".yml", false);
            }

            config.put(filename, new YamlConfiguration());
            try {
                config.get(filename).load(configFile.get(filename));
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadConfig(String filename) {
        config.put(filename, new YamlConfiguration());
        try {
            config.get(filename).load(configFile.get(filename));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
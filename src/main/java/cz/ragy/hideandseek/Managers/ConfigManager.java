package cz.ragy.hideandseek.Managers;

import cz.ragy.hideandseek.Game.Arena;
import cz.ragy.hideandseek.HideAndSeek;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {
    private final File configFile = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    private final File arenasFile = new File(HideAndSeek.instance.getDataFolder(), "arenas.yml");

    public static YamlConfiguration arenas;
    public static YamlConfiguration config;
    public void startup(){
        if(!configFile.exists()) {
            config = new YamlConfiguration();
            HideAndSeek.instance.saveResource("config.yml", true);
        }
        if(!arenasFile.exists()){
            arenas = new YamlConfiguration();
            HideAndSeek.instance.saveResource("arenas.yml", true);
        }
    }
    public void saveArenas(List<Arena> arenaList){
        arenas = new YamlConfiguration();
        FileConfiguration arenaConfig = YamlConfiguration.loadConfiguration(arenasFile);
        arenaConfig.getConfigurationSection("items").set
        arenaList.forEach(arena -> arenaConfig.set("arenas", arena));

        try {
            arenaConfig.save(arenasFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

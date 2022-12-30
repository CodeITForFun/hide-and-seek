package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.HideAndSeek;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
        FileConfiguration arenacfg = YamlConfiguration.loadConfiguration(arenasFile);
        //FileConfiguration arenaConfig = YamlConfiguration.loadConfiguration(arenasFile);
        ConfigurationSection settings = arenacfg.getConfigurationSection("arenas");
        arenaList.forEach(arena -> settings.addDefault(arena.arenaName, arena));

        try {
            arenas.save(arenasFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

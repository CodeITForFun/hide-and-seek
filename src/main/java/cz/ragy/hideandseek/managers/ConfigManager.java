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
        FileConfiguration arenaConfig = YamlConfiguration.loadConfiguration(arenasFile);

        if (arenaConfig.getConfigurationSection("arenas") == null) {
            arenaConfig.createSection("arenas");
        }
        try {
            arenaConfig.save(arenasFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get the parent ConfigurationSection
        ConfigurationSection parentSection = arenaConfig.getConfigurationSection("arenas");

        arenaList.forEach(arena -> {
            ConfigurationSection childSection = parentSection.createSection(arena.arenaName);
            childSection.set("ArenaName", arena.arenaName);
            childSection.set("ArenaWorld", arena.arenaWorldName);
            childSection.set("ArenaMaxPlayers", arena.maxPlayers);
            childSection.set("ArenaMinPlayers", arena.minPlayers);
            childSection.set("ArenaSeekersCount", arena.seekersCount);
        });
        /*ConfigurationSection finalSettings = settings;
        //finalSettings.set("arenas", );
        arenaList.forEach(arena -> finalSettings.set("arenas", arena.arenaName));*/

        try {
            arenaConfig.save(arenasFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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
        //TODO: fix creating same arenas, load arena on server startup

        FileConfiguration arenaConfig = YamlConfiguration.loadConfiguration(arenasFile);

        if (arenaConfig.getConfigurationSection("arenas") == null) {
            arenaConfig.createSection("arenas");
            try {
                arenaConfig.save(arenasFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ConfigurationSection parentSection = arenaConfig.getConfigurationSection("arenas");
        for (Arena arena : arenaList) {
            ConfigurationSection childSection = parentSection.createSection(arena.arenaName);
            //fix creating same arena



            if (parentSection.getConfigurationSection(arena.arenaName) != null) {
                System.out.println("error, naser si!");
                break; //stopuje for loop
            }









            childSection.set("ArenaWorld", arena.arenaWorldName);
            childSection.set("ArenaMaxPlayers", arena.maxPlayers);
            childSection.set("ArenaMinPlayers", arena.minPlayers);
            childSection.set("ArenaSeekersCount", arena.seekersCount);
        }
        try {
            arenaConfig.save(arenasFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

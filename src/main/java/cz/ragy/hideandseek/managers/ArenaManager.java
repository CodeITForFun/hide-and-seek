package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    public List<Arena> arenas = new ArrayList<Arena>();
    public void addArenaToList(Arena arena, CommandSender sender){
        arenas.add(arena);
        new ConfigManager().saveArenasToConfig(arenas, sender);
    }
    public void removeArenaFromList(Arena arena) {
        arenas.remove(arena);
    }

    public List<String> getArenasKeysFromConfig(FileConfiguration arenaCconfiguration, File file) {
        arenaCconfiguration = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = arenaCconfiguration.getConfigurationSection("arenas");
        List<String> arenaList = new ArrayList<>();

        for (String key : section.getKeys(false)) {
            arenaList.add(key);
        }
        return arenaList;
    }
    public List<Arena> getListOfArenas() {
        return arenas;
    }
    public void renameArena(String oldName, String newName) {
        ConfigurationSection parentSection = ConfigManager.arenas.getConfigurationSection("arenas");
        ConfigurationSection childSection = parentSection.getConfigurationSection(oldName);
        ConfigurationSection childNewSection = parentSection.createSection(newName);
        List<Object> stuff = new ArrayList<>();

        if (parentSection.getConfigurationSection(oldName) == null)  return;

        childNewSection.set("ArenaWorld", childSection.get("ArenaWorld"));
        childNewSection.set("ArenaMaxPlayers", childSection.get("ArenaMaxPlayers"));
        childNewSection.set("ArenaMinPlayers", childSection.get("ArenaMinPlayers"));
        childNewSection.set("ArenaSeekersCount", childSection.get("ArenaSeekersCount"));

        parentSection.set(oldName, null);
        try {
            ConfigManager.arenas.save(ConfigManager.arenasFile);
        } catch (IOException error) {
            error.printStackTrace();
        }
        new ConfigManager().startup();
    }
}

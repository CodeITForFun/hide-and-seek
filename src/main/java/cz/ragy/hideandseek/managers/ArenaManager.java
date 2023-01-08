package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
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
        for (String s : ConfigManager.arenas.getConfigurationSection("arenas." + oldName).getKeys(false)) {
            ConfigManager.arenas.set("arenas." + newName + "." + s, ConfigManager.arenas.get("arenas." + newName + "."+s));
        }

    }
}

package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.HideAndSeek;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {
    private final File configFile = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    private final File arenasFile = new File(HideAndSeek.instance.getDataFolder(), "arenas.yml");

    public File file = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    public FileConfiguration confik = YamlConfiguration.loadConfiguration(file);

    public String creating = (String) confik.get("Create-Arena.Creating-Arena");
    public String arenaCreated = (String) confik.get("Create-Arena.Created");

    public String arenaExists = (String) confik.get("Create-Arena.Arena-Exists");

    public String setArena;

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
    public void saveArenas(List<Arena> arenaList, CommandSender sender){
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
            if (parentSection.getConfigurationSection(arena.arenaName) == null) {
                ConfigurationSection childSection = parentSection.createSection(arena.arenaName);
                setArena = arenaCreated;
                setArena = setArena.replace("%arena%", arena.arenaName);
                childSection.set("ArenaWorld", arena.arenaWorldName);
                childSection.set("ArenaMaxPlayers", arena.maxPlayers);
                childSection.set("ArenaMinPlayers", arena.minPlayers);
                childSection.set("ArenaSeekersCount", arena.seekersCount);

                sender.sendMessage("Arena: " + arena.arenaName);
                sender.sendMessage("World: " + arena.arenaWorldName);
                sender.sendMessage("Max Players: " + arena.maxPlayers);
                sender.sendMessage("Min Players: " + arena.minPlayers);
                sender.sendMessage("Seekers: " + arena.seekersCount);

                sender.sendMessage(creating);
                sender.sendMessage(setArena);
            } else {
                sender.sendMessage(arenaExists);
                continue;
            }
        }
        try {
            arenaConfig.save(arenasFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

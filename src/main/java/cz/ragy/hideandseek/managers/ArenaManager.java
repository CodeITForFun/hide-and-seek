package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    public List<Arena> arenas = new ArrayList<Arena>();
    public void addArenaToList(Arena arena, CommandSender sender){
        arenas.add(arena);
        new ConfigManager().saveArenas(arenas, sender);
    }
    public void removeArenaFromList(Arena arena) {
        arenas.remove(arena);
    }
    public List<Arena> getListOfArenas() {
        return arenas;
    }
}

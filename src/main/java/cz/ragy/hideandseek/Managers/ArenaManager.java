package cz.ragy.hideandseek.Managers;

import cz.ragy.hideandseek.Game.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    public List<Arena> arenas = new ArrayList<Arena>();
    public void addArenaToList(Arena arena){
        System.out.println(arena);
        arenas.add(arena);
        //TODO: Fix null problem
        System.out.println(arenas);
        //TODO: save arena to config as well
        new ConfigManager().saveArenas(arenas);
    }
    public void removeArenaFromList(Arena arena) {
        arenas.remove(arena);
    }
    public List<Arena> getListOfArenas() {
        return arenas;
    }
}

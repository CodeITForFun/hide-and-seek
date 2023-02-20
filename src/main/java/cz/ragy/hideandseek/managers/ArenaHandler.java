package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ArenaHandler {
    public void joinArena(Player player, Arena arena) {
        WorldManager.teleportToWorld(arena.arenaWorldName, arena.lobbyLocation.getX(), arena.lobbyLocation.getY(), arena.lobbyLocation.getZ(), arena.lobbyLocation.getPitch(), arena.lobbyLocation.getYaw(), player);
    }
}

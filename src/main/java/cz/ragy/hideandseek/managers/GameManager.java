package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.entity.Player;

public class GameManager {
    public void joinArena(Player player, Arena arena) {
        if (!arena.players.contains(player.getUniqueId())) {
            if(!arena.playing) {
                WorldManager.teleportToWorld(arena.arenaWorldName, arena.lobbyLocation.getX(), arena.lobbyLocation.getY(), arena.lobbyLocation.getZ(), arena.lobbyLocation.getPitch(), arena.lobbyLocation.getYaw(), player);
                player.getInventory().clear();
                player.sendTitle(Colors.translate("&cJoining arena"), Colors.translate("&c" + arena.arenaName));
                arena.addPlayer(player);
                //give compass to chose teams

            } else {
                player.sendMessage("The game already started!");
            }
        } else {
            player.sendMessage("You are already connected in one arena");
        }
    }
}

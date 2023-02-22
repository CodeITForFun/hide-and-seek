package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.utilities.Colors;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public void joinArena(Player player, Arena arena) {
        if (!arena.players.contains(player.getUniqueId())) {
            if(!arena.playing) {
                WorldManager.teleportToWorld(arena.arenaWorldName, arena.lobbyLocation.getX(), arena.lobbyLocation.getY(), arena.lobbyLocation.getZ(), arena.lobbyLocation.getPitch(), arena.lobbyLocation.getYaw(), player);
                player.getInventory().clear();
                player.sendTitle(Colors.translate("&cJoining arena"), Colors.translate("&c" + arena.arenaName));
                arena.addPlayer(player);
                //give compass to chose teams
                player.setGameMode(GameMode.SURVIVAL);
                FastBoard board = new FastBoard(player);
                board.updateTitle(ChatColor.GOLD + "bruh");
                board.updateLines(
                        "", // Empty line
                        "hahahahhahaa",
                        "",
                        "Second line"
                );
                if(arena.players.size() > arena.minPlayers) {
                    //start countdown for 30s if it is not already running
                    new MessageManager().printInfo("ZAPINAM!!");
                }
            } else {
                player.sendMessage("The game already started!");
            }
        } else {
            player.sendMessage("You are already connected in one arena");
        }
    }
}

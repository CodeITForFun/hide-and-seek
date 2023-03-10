package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.utilities.Colors;
import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GameManager {

    public String arenaLeave = (String) ConfigManager.config.get("Arena.LeaveArena");

    public String prefix = (String) ConfigManager.config.get("Core.Prefix");
    public String arenaNotConnected = (String) ConfigManager.config.get("Arena.NotConnected");
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
                        "",
                        "hahahahhahaa",
                        "",
                        "Second line"
                );
                arena.boards.put(player.getUniqueId(), board);
                if(arena.players.size() > arena.minPlayers) {
                    new MessageManager().printInfo("ZAPINAM!!");
                }
            } else {
                player.sendMessage("The game already started!");
            }
        } else {
            player.sendMessage("You are already connected in one arena");
        }
    }
    public void leaveArena(Player player) {
        loop: for(Arena arena : ArenaManager.STATICARENAS) {
            if(arena.players.contains(player.getUniqueId()))
            {
                arena.removeArenaPlayer(player);
                FastBoard board = arena.boards.remove(player.getUniqueId());
                if (board != null) {
                    board.delete();
                }
                player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(prefix + arenaLeave)));
                break;
            } else player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(prefix + arenaNotConnected)));
        }
    }
}

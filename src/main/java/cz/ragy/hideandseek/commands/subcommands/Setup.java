package cz.ragy.hideandseek.commands.subcommands;

import cz.ragy.hideandseek.commands.SubCommand;
import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.managers.ArenaManager;
import cz.ragy.hideandseek.managers.ConfigLoader;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.utilities.Colors;
import cz.ragy.hideandseek.utilities.Digit;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Setup extends SubCommand {
    @Override
    public String getName() {
        return "setup";
    }

    @Override
    public String getDescription() {
        return "Setups all kind of stuff like arena, lobby";
    }

    @Override
    public String getUsage() {
        return "/has setup <arena | lobby>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(args.length > 7 && args[1].equals("arena")) { player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(ConfigLoader.prefix + ConfigLoader.arenaInvalidMessage))); return; }
        if (args.length == 2) {
            if (args[1].equals("lobby")) {
                player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(ConfigLoader.lobbySet)));
                ConfigManager.config.set("Lobby.LobbyLocation", player.getLocation());
                try {
                    ConfigManager.config.save(ConfigManager.configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!ConfigLoader.lobbySpawnStatus) {
                    player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(ConfigLoader.lobbyWarning)));
                }
                new ConfigManager().startup();
            }
        }
        else if (args.length == 7) {
                if (args[1].equals("arena")) {
                    if (!player.hasPermission("has.createarena") || !player.hasPermission("has.*")) { player.sendMessage(Colors.translate(ConfigLoader.noPerms)); return; }
                    Digit digit = new Digit();
                    if(!(digit.containsDigits(args[2])) &&
                            !(digit.containsDigits(args[3])) &&
                            digit.containsDigits(args[4]) &&
                            digit.containsDigits(args[5]) &&
                            digit.containsDigits(args[6])) {

                        String arenaName = args[2];
                        String arenaWorldName = args[3];
                        int maxPlayers = Integer.parseInt(args[4]);
                        int minPlayers = Integer.parseInt(args[5]);
                        int seekersCount = Integer.parseInt(args[6]);
                        Location loc = player.getLocation();

                        Arena createdArena = new Arena(arenaName, arenaWorldName, maxPlayers, minPlayers, seekersCount, loc, loc, loc);
                        ArenaManager arenaManager = new ArenaManager();
                        arenaManager.addArenaToList(createdArena, player);

                        new ConfigManager().startup();
                    } else {
                        player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(ConfigLoader.prefix + ConfigLoader.arenaInvalidMessage)));
                        return;
                    }
                } else {
                    player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player,Colors.translate(ConfigLoader.prefix + ConfigLoader.arenaInvalidMessage)));
                    return;
                }
            } else {
             player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(ConfigLoader.prefix + ConfigLoader.arenaInvalidMessage)));
             return;
        }
    }
}

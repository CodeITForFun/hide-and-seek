package cz.ragy.hideandseek.commands;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.managers.ArenaManager;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.managers.MessageManager;
import cz.ragy.hideandseek.utilities.Colors;
import cz.ragy.hideandseek.utilities.Digit;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    public String prefix = (String) ConfigManager.config.get("Core.Prefix");
    public String noPerms = (String) ConfigManager.config.get("Core.No-Permission");
    public String invalidMessage = (String) ConfigManager.config.get("Create-Arena.Invalid-Message");
    public String notEntity = (String) ConfigManager.config.get("Core.notEntity");
    public String Reload = (String) ConfigManager.config.get("Reload.Reload-Message");
    public String sucReloaded = (String) ConfigManager.config.get("Reload.Successfully-Reloaded");
    public String lobbySet = (String) ConfigManager.config.get("Lobby.Success");
    public boolean LobbySpawnStatus = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
    public String LobbyWarning = (String) ConfigManager.config.get("Lobby.Warning");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                switch (command.getName()) {
                    case "has":
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("It works!")));
                        break;
                }
            }
            if (args.length == 1) {
                switch (args[0]) {
                    case "help":
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("pOMOC JE NA CESTE")));
                        break;
                    case "reload":
                        if (!sender.hasPermission("has.reload") || !sender.hasPermission("has.*")) { sender.sendMessage(Colors.translate(prefix + noPerms)); return true;}
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(prefix + Reload)));
                        File configFile = new File("config.yml");
                        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                        // Reload the config file
                        config = YamlConfiguration.loadConfiguration(configFile);
                        // Save the config file
                        try { config.save(configFile); } catch (IOException e) { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("&cError! Please look into console!"))); new MessageManager().printWarn(String.valueOf(e));
                        }
                        HideAndSeek.instance.reloadConfig();
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(prefix + sucReloaded)));
                        break;
                    case "setup":
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("pruvodce more")));
                        break;
                    default:
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("help message")));
                }
            }
            if(args.length == 2) {
                if (!(sender.hasPermission("has.setLobby"))) { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(noPerms))); return true; }

                if(args[0].equals("setup")) {
                    if(args[1].equals("lobby")) {
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(lobbySet)));
                        ConfigManager.config.set("Lobby.onJoinX", player.getLocation().getX());
                        ConfigManager.config.set("Lobby.onJoinY", player.getLocation().getY());
                        ConfigManager.config.set("Lobby.onJoinZ", player.getLocation().getZ());
                        ConfigManager.config.set("Lobby.onJoinPitch", player.getLocation().getPitch());
                        ConfigManager.config.set("Lobby.onJoinYaw", player.getLocation().getYaw());
                        try {
                            ConfigManager.config.save(ConfigManager.configFile);
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                        if (!LobbySpawnStatus) {
                            sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(LobbyWarning)));
                        }
                        return true;
                    }
                    if (args[1].equals("arena")) { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage))); }
                }
            }
            if (args.length == 7) {
                if (args[0].equals("setup")) {
                    if (args[1].equals("arena")) {
                        if (!player.hasPermission("has.createarena") || !sender.hasPermission("has.*")) { sender.sendMessage(Colors.translate(noPerms)); return true; }
                        Digit digit = new Digit();

                        if( !(digit.containsDigits(args[2])) &&
                                !(digit.containsDigits(args[3])) &&
                                digit.containsDigits(args[4]) &&
                                digit.containsDigits(args[5]) &&
                                digit.containsDigits(args[6])){

                            String arenaName = args[2];
                            String arenaWorldName = args[3];
                            int maxPlayers = Integer.parseInt(args[4]);
                            int minPlayers = Integer.parseInt(args[5]);
                            int seekersCount = Integer.parseInt(args[6]);

                            Arena createdArena = new Arena(arenaName, arenaWorldName, maxPlayers, minPlayers, seekersCount);
                            ArenaManager arenaManager = new ArenaManager();
                            arenaManager.addArenaToList(createdArena, sender);
                        } else {
                            sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage)));
                        }
                    } else {
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage)));
                    }
                } else {
                    sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage)));
                }
            }
            if(args.length > 7 && args[1].equals("arena") && args[0].equals("setup")) { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage))); return true; }
            return true;
        } else { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(notEntity))); return true; }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("help");
            arguments.add("joinarena");
            if (sender.hasPermission("has.tab") || sender.hasPermission("has.*")) {
                arguments.add("reload");
                arguments.add("setup");
            }
            return arguments;
        }
        if(args.length == 2) {
            List<String> arguments = new ArrayList<>();
            arguments.add("arena");
            arguments.add("lobby");
            return arguments;
        } else {
            return null;
        }
    }
}
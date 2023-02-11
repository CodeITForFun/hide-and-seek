package cz.ragy.hideandseek.commands;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.managers.ArenaManager;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.managers.MessageManager;
import cz.ragy.hideandseek.menusystem.menus.EditMenu;
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

    public static final File arenasFile = new File(HideAndSeek.instance.getDataFolder(), "arenas.yml");
    public YamlConfiguration arenas = new YamlConfiguration().loadConfiguration(arenasFile);
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
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("Help Message")));
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
                        new ConfigManager().reloadAllConfigs();
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(prefix + sucReloaded)));
                        break;
                    case "setup":
                        if(!(sender.hasPermission("has.setup") || sender.hasPermission("has.*"))) { sender.sendMessage(prefix + noPerms); break; }
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("&7Hello, welcome to &eHide & Seek Plugin by Radekminecraft and FungY911.\nThis is Setup Wizard for this plugin.\n\nIf you need any help, you can join to [&9Discord server](https://discord.gg/EgqNXXcx2q)")));
                        //player.chat("/has setup lobby"); //automatically execute command
                        break;
                    default:
                        sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate("Help Message")));
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
                        new ConfigManager().reloadAllConfigs();
                        return true;
                    }
                    if (args[1].equals("arena")) { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage))); }
                }
                if(args[0].equals("editarena")) {
                    if(!(sender.hasPermission("has.editArena"))) { sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, Colors.translate(noPerms))); return true; }
                    if(new ConfigManager().arenaExists(args[1])) {
                        new EditMenu(HideAndSeek.getPlayerMenuUtility(player), args[1]).open();
                        sender.sendMessage(new ConfigManager().getStringFromConfig("Arena.NowEditingArena").replace("%arena%", args[1]));
                    } else {
                        player.sendMessage(new ConfigManager().getStringFromConfig("Arena.ArenaDoesntExist").replace("%arena%", args[1]));
                    }
                    return true;
                }
            }
            if(args.length > 2) {
                if(args[0].equals("setup") && args[1].equals("lobby")) {
                    sender.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) sender,Colors.translate(prefix + invalidMessage)));
                }
            }
            if (args.length == 7) {
                if (args[0].equals("setup")) {
                    if (args[1].equals("arena")) {
                        if (!player.hasPermission("has.createarena") || !sender.hasPermission("has.*")) { sender.sendMessage(Colors.translate(noPerms)); return true; }
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
                arguments.add("editarena");
            }
            return arguments;
        } else if(args.length == 2) {
            switch(args[0].toLowerCase()) {
                case "setup":
                    List<String> arguments = new ArrayList<>();
                    arguments.add("arena");
                    arguments.add("lobby");
                    return arguments;
                case "editarena":
                    File arenaFile = new File(HideAndSeek.instance.getDataFolder(), "arenas.yml");
                    YamlConfiguration arena = YamlConfiguration.loadConfiguration(arenaFile);
                    List<String> arenaList = new ArrayList<>();
                    if(arena.getConfigurationSection("arenas").getKeys(false) == null){
                        return arenaList;
                    } else {
                    for (String key : arena.getConfigurationSection("arenas").getKeys(false)) {
                        arenaList.add(key);
                        }
                    }
                    return arenaList;
                case "joinarena":
                    File arenaFilee = new File(HideAndSeek.instance.getDataFolder(), "arenas.yml");
                    YamlConfiguration arenaa = YamlConfiguration.loadConfiguration(arenaFilee);
                    List<String> arenaListt = new ArrayList<>();
                    if(arenaa.getConfigurationSection("arenas").getKeys(false) == null){
                        return arenaListt;
                    } else {
                        for (String key : arenaa.getConfigurationSection("arenas").getKeys(false)) {
                            arenaListt.add(key);
                        }
                    }
                    return arenaListt;
            }
        }
        return null;
    }
}
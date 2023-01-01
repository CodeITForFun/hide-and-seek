package cz.ragy.hideandseek.commands;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.managers.ArenaManager;
import cz.ragy.hideandseek.utilities.Digit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    public File file = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public String prefix = (String) config.get("Core.Prefix");
    public String noPerms = (String) config.get("Core.No-Permission");
    public String invalidMessage = (String) config.get("Create-Arena.Invalid-Message");
    public String notEntity = (String) config.get("Core.notEntity");
    public String Reload = (String) config.get("Reload.Reload-Message");
    public String sucReloaded = (String) config.get("Reload.Successfully-Reloaded");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                switch (command.getName()) {
                    case "has":
                        sender.sendMessage("It works!");
                        break;
                }
            }
            if (args.length == 1) {
                switch (args[0]) {
                    case "help":
                        sender.sendMessage("pOMOC JE NA CESTE");
                        break;
                    case "reload":
                        if (!sender.hasPermission("has.reload") || sender.hasPermission("has.*")) { sender.sendMessage(prefix + noPerms); }
                        sender.sendMessage(prefix + Reload);
                        HideAndSeek.instance.reloadConfig();
                        sender.sendMessage(prefix + sucReloaded);
                        break;
                    /*case "createarena":
                        sender.sendMessage(prefix + invalidMessage);
                        break;*/
                    case "setup":
                        sender.sendMessage("pruvodce more");
                        break;
                }
            }
            if(args.length > 2 && args.length == 7){
                switch(args[0]) {
                    case "arena":
                        if (!player.hasPermission("has.createarena") || sender.hasPermission("has.*")) { sender.sendMessage(noPerms); return true; }
                        Digit digit = new Digit();
                        if( !(digit.containsDigits(args[1])) &&
                                !(digit.containsDigits(args[2])) &&
                                digit.containsDigits(args[3]) &&
                                digit.containsDigits(args[4]) &&
                                digit.containsDigits(args[5])){

                            String arenaName = args[1];
                            String arenaWorldName = args[2];
                            int maxPlayers = Integer.parseInt(args[3]);
                            int minPlayers = Integer.parseInt(args[4]);
                            int seekersCount = Integer.parseInt(args[5]);

                            Arena createdArena = new Arena(arenaName, arenaWorldName, maxPlayers, minPlayers, seekersCount);
                            ArenaManager arenaManager = new ArenaManager();
                            arenaManager.addArenaToList(createdArena, sender);
                        } else {
                            sender.sendMessage(prefix + invalidMessage);
                        }
                        break;
                    case "lobby":
                        sender.sendMessage("lobby more seettings");
                        break;
                }
            }
            /*if (args.length > 1 && args.length == 6) {
                switch(args[0]) {
                    case "createarena":
                        if (!player.hasPermission("has.createarena") || sender.hasPermission("has.*")) { sender.sendMessage(noPerms); return true; }
                        if (!player.hasPermission("has.createarena")) { sender.sendMessage(noPerms); return true; }
                        Digit digit = new Digit();
                        if( !(digit.containsDigits(args[1])) &&
                            !(digit.containsDigits(args[2])) &&
                            digit.containsDigits(args[3]) &&
                            digit.containsDigits(args[4]) &&
                            digit.containsDigits(args[5])){

                            String arenaName = args[1];
                            String arenaWorldName = args[2];
                            int maxPlayers = Integer.parseInt(args[3]);
                            int minPlayers = Integer.parseInt(args[4]);
                            int seekersCount = Integer.parseInt(args[5]);

                            Arena createdArena = new Arena(arenaName, arenaWorldName, maxPlayers, minPlayers, seekersCount);
                            ArenaManager arenaManager = new ArenaManager();
                            arenaManager.addArenaToList(createdArena, sender);
                        } else {
                            sender.sendMessage(prefix + invalidMessage);
                        }
                        break;
                }
            }*/
            if(args.length > 7 && args[0] == "createarena") { sender.sendMessage(prefix + invalidMessage); return true; }
            return true;
        } else { sender.sendMessage(notEntity); return true; }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            if(args.length == 1){
                List<String> arguments = new ArrayList<>();
                arguments.add("help");
                if (sender.hasPermission("has.tab") || sender.hasPermission("has.*")) {
                    arguments.add("reload");
                    arguments.add("joinarena");
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

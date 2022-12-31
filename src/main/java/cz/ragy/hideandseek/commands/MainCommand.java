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
    public String creating = (String) config.get("Create-Arena.Creating-Arena");
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
                }
            }
            if (args.length > 1 && args.length == 6) {
                switch(args[0]) {
                    case "createarena":
                        if (!player.hasPermission("has.createarena")) { sender.sendMessage(noPerms); return true; }
                        //    /has createarena [arenaName] [arenaWorldName] [maxplayers] [minplayers] [seekersCount]
                        Digit digit = new Digit();
                        if(!(digit.containsDigits(args[1])) && !(digit.containsDigits(args[2])) && digit.containsDigits(args[3]) && digit.containsDigits(args[4]) && digit.containsDigits(args[5])){
                            sender.sendMessage(creating);

                            String arenaName = args[1];
                            String arenaWorldName = args[2];
                            int maxPlayers = Integer.parseInt(args[3]);
                            int minPlayers = Integer.parseInt(args[4]);
                            int seekersCount = Integer.parseInt(args[5]);

                            Arena createdArena = new Arena(arenaName, arenaWorldName, maxPlayers, minPlayers, seekersCount);

                            sender.sendMessage("Arena: " + arenaName);
                            sender.sendMessage("World: " + arenaWorldName);
                            sender.sendMessage("Max Players: " + maxPlayers);
                            sender.sendMessage("Min Players: " + minPlayers);
                            sender.sendMessage("Seekers: " + seekersCount);
                            ArenaManager arenaManager = new ArenaManager();
                            arenaManager.addArenaToList(createdArena);


                        } else {
                            sender.sendMessage(prefix + invalidMessage);
                        }
                        break;
                    case "reload":
                        if (!sender.hasPermission("has.reload")) {
                            sender.sendMessage(prefix + invalidMessage);
                        }
                        sender.sendMessage(prefix + "Please wait, reloading plugin!");
                        HideAndSeek.instance.reloadConfig();
                        sender.sendMessage(prefix + "Files arenas.yml, config.yml has beed reloaded!");
                        return true;
                }
            } else {
                sender.sendMessage(prefix + invalidMessage);
            }
            return true;
        } else {
            sender.sendMessage(notEntity);
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            List<String> arguments = new ArrayList<>();
            arguments.add("help");
            if (sender.hasPermission("has.tab")) {
                arguments.add("relaod");
                arguments.add("createarena");
            }
            return arguments;
    }
}

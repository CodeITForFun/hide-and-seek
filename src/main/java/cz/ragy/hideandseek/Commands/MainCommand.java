package cz.ragy.hideandseek.Commands;

import cz.ragy.hideandseek.Game.Arena;
import cz.ragy.hideandseek.Managers.ArenaManager;
import cz.ragy.hideandseek.Utilities.Digit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    public String notPlayer = "Dojdi na server jozo";
    public String errorUsageMessage = "Error! use command like this: /has createarena [maxplayers] [minplayers] [seekersCount] [hidersCount] [arenaName] [arenaWorldName]";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0) {
                switch (command.getName()) {
                    case "has":
                        sender.sendMessage("It works!");
                        break;
                }
            }
            if(args.length == 1){
                switch (args[0]) {
                    case "help":
                        sender.sendMessage("pOMOC JE NA CESTE");
                        break;
                    /*case "createarena":
                        //has createarena
                        new Arena(5, 3, 1, 4, "Test", "world");
                        break;*/
                }
            }
            if(args.length > 1 && args.length == 7){
                switch(args[0]){
                    case "createarena":
                        //    /has createarena args1 args2 args3 args4 args5 args6
                        Digit digit = new Digit();
                        if(digit.containsDigits(args[1]) && digit.containsDigits(args[2]) && digit.containsDigits(args[3]) && digit.containsDigits(args[4]) && !(digit.containsDigits(args[5]) && !(digit.containsDigits(args[6])))){
                            sender.sendMessage("Creating arena");
                            int maxPlayers = Integer.parseInt(args[1]);
                            int minPlayers = Integer.parseInt(args[2]);
                            int seekersCount = Integer.parseInt(args[3]);
                            int hidersCount = Integer.parseInt(args[4]);

                            Arena createdArena = new Arena(maxPlayers, minPlayers, seekersCount, hidersCount, args[5], args[6]);
                            ArenaManager arenaManager = new ArenaManager();
                            arenaManager.addArenaToList(createdArena);
                        } else {
                            sender.sendMessage(errorUsageMessage);
                        }
                        break;
                }
            } else {
                sender.sendMessage(errorUsageMessage);
            }
            return true;
        } else {
            sender.sendMessage(notPlayer);
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            List<String> arguments = new ArrayList<>();
            arguments.add("help");
            arguments.add("createarena");
            return arguments;
    }
}

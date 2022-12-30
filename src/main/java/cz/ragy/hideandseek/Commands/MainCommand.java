package cz.ragy.hideandseek.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    public String notPlayer = "Dojdi na server jozo";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
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
                }
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

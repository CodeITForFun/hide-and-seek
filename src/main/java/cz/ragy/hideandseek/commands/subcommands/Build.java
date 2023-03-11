package cz.ragy.hideandseek.commands.subcommands;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Build extends SubCommand {
    @Override
    public String getName() {
        return "build";
    }

    @Override
    public String getDescription() {
        return "Enables build mode";
    }

    @Override
    public String getUsage() {
        return "/has build <player>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(args.length == 2) {
            if(!(player.hasPermission("has.build") || player.hasPermission("has.*"))) return;
            Player playerArg = Bukkit.getPlayer(args[1]);
            if(HideAndSeek.buildPlayers.contains(playerArg.getUniqueId())) {
                HideAndSeek.buildPlayers.remove(playerArg.getUniqueId());
                player.sendMessage("Build mode disabled");
            } else {
                HideAndSeek.buildPlayers.add(playerArg.getUniqueId());
                player.sendMessage("Build mode enabled");
            }
        }
        else if(args.length == 1) {
            if(!(player.hasPermission("has.build") || player.hasPermission("has.*"))) return;
            if(HideAndSeek.buildPlayers.contains(player.getUniqueId())) {
                HideAndSeek.buildPlayers.remove(player.getUniqueId());
                player.sendMessage("Build mode disabled");
            } else {
                HideAndSeek.buildPlayers.add(player.getUniqueId());
                player.sendMessage("Build mode enabled");
            }
        }
    }
}

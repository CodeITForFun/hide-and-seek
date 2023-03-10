package cz.ragy.hideandseek.commands.subcommands;

import cz.ragy.hideandseek.commands.SubCommand;
import cz.ragy.hideandseek.utilities.Colors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Help extends SubCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Get command usages";
    }

    @Override
    public String getUsage() {
        return "/has help";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(args.length == 1) {
            player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate("pOMOC JE NA CESTE")));
        }
    }
}

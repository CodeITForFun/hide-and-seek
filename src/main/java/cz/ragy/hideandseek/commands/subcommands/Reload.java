package cz.ragy.hideandseek.commands.subcommands;

import cz.ragy.hideandseek.commands.SubCommand;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.utilities.Colors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {

    public String Reload = (String) ConfigManager.config.get("Reload.Reload-Message");
    public String sucReloaded = (String) ConfigManager.config.get("Reload.Successfully-Reloaded");

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads minecraft plugin";
    }

    @Override
    public String getUsage() {
        return "/has reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.hasPermission("has.reload") || !player.hasPermission("has.*")) { player.sendMessage(Colors.translate(prefix + noPerms)); return; }
        player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(prefix + Reload)));
        new ConfigManager().startup();
        player.sendMessage(PlaceholderAPI.setPlaceholders((OfflinePlayer) player, Colors.translate(prefix + sucReloaded)));
    }
}

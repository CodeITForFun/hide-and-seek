package cz.ragy.hideandseek.commands.subcommands;

import cz.ragy.hideandseek.commands.SubCommand;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.managers.GameManager;
import cz.ragy.hideandseek.utilities.Colors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Leave extends SubCommand {

    public String arenaLeave = (String) ConfigManager.config.get("Arena.LeaveArena");

    public String arenaNotConnected = (String) ConfigManager.config.get("Arena.NotConnected");
    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "You can leave arena";
    }

    @Override
    public String getUsage() {
        return "/has leave";
    }

    @Override
    public void perform(Player player, String[] args) {
        new GameManager().leaveArena(player);
    }
}

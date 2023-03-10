package cz.ragy.hideandseek.commands.subcommands;

import cz.ragy.hideandseek.commands.SubCommand;
import cz.ragy.hideandseek.managers.GameManager;
import org.bukkit.entity.Player;

public class Leave extends SubCommand {
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

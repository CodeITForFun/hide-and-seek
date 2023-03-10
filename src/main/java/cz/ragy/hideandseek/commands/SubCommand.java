package cz.ragy.hideandseek.commands;

import cz.ragy.hideandseek.managers.ConfigManager;
import org.bukkit.entity.Player;

public abstract class SubCommand {
    public String prefix = (String) ConfigManager.config.get("Core.Prefix");

    public String noPerms = (String) ConfigManager.config.get("Core.No-Permission");
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();
    public abstract void perform(Player player, String[] args);
}

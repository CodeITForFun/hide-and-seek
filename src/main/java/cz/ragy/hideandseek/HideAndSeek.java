package cz.ragy.hideandseek;

import cz.ragy.hideandseek.commands.TestCmd;
import cz.ragy.hideandseek.managers.StartupManager;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import games.negative.framework.BasePlugin;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getPluginManager;

public final class HideAndSeek extends BasePlugin {
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public List<UUID> buildPlayers = new ArrayList<>();
    public static HideAndSeek instance;
    public FallingBlock fallingBlock;
    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();
        getLogger().info("Getting depends");
        Plugin placeholderAPI = getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderAPI != null && placeholderAPI.isEnabled()) {
            new StartupManager().startup(getServer(), this, this);
            registerCommands(new TestCmd());
        } else {
            getLogger().warning("Please install and enable PlaceholderAPI for the Hide And Seek Plugin to work.");
            getPluginManager().disablePlugin(this);
        }
    }
    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        } else { return playerMenuUtilityMap.get(p); }

    }
}
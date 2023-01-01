package cz.ragy.hideandseek;

import cz.ragy.hideandseek.managers.StartupManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

public final class HideAndSeek extends JavaPlugin implements Listener {
    public static HideAndSeek instance;
    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Getting depends");
        Plugin placeholderAPI = getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderAPI != null && placeholderAPI.isEnabled()) {
            getPluginManager().registerEvents(this, this);
        } else {
            getLogger().info("Please install and enable PlaceholderAPI for the Hide And Seek Plugin to work.");
            getPluginManager().disablePlugin(this);
        }
        new StartupManager().startup(getServer(), this, this);
    }
    @Override
    public void onDisable() {
    }
}

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
            new StartupManager().startup(getServer(), this, this);
        } else {
            getLogger().warning("Please install and enable PlaceholderAPI for the Hide And Seek Plugin to work.");
            getPluginManager().disablePlugin(this);
        }
    }
    public void printInfo(String s) { getLogger().info(s); }
    public void printWarn(String s) { getLogger().warning(s); }
    @Override
    public void onDisable() {}
}

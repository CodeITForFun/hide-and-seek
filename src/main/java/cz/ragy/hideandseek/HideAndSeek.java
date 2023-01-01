package cz.ragy.hideandseek;

import cz.ragy.hideandseek.managers.MessageManager;
import cz.ragy.hideandseek.managers.StartupManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static org.bukkit.Bukkit.getPluginManager;

public final class HideAndSeek extends JavaPlugin implements Listener {
    private static HideAndSeek plugin;
    public static HideAndSeek instance;
    @Override
    public void onEnable() {
        plugin = this;
        instance = this;
        getLogger().info("Getting depends");
        Plugin placeholderAPI = getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderAPI != null && placeholderAPI.isEnabled()) {
            new StartupManager().startup(getServer(), this, this);
            autobroadcast();
        } else {
            getLogger().warning("Please install and enable PlaceholderAPI for the Hide And Seek Plugin to work.");
            getPluginManager().disablePlugin(this);
        }
    }
    private void autobroadcast() {
        File file = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (getServer().getOnlinePlayers().size() >= 1) {
            int interval = (int) config.getInt("interval") * 20;
            new MessageManager(this).runTaskTimer(this, 0, interval);
        }
    }
    public void printInfo(String s) { getLogger().info(s); }
    public void printWarn(String s) { getLogger().warning(s); }
    @Override
    public void onDisable() {}
}

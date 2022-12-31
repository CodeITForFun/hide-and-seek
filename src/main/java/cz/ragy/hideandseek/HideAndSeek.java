package cz.ragy.hideandseek;

import cz.ragy.hideandseek.managers.StartupManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HideAndSeek extends JavaPlugin {

    public static HideAndSeek instance;
    @Override
    public void onEnable() {
        instance = this;
        new StartupManager().startup(getServer(), this, this);
    }

    @Override
    public void onDisable() {
    }

    public void print(String s) {
        getLogger().info(s);
    }
}

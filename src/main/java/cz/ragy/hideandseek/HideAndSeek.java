package cz.ragy.hideandseek;

import cz.ragy.hideandseek.Managers.StartupManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideAndSeek extends JavaPlugin {

    @Override
    public void onEnable() {
        new StartupManager().startup(getServer());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package cz.ragy.hideandseek;

import cz.ragy.hideandseek.Managers.StartupManager;
import org.bukkit.plugin.java.JavaPlugin;

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
}

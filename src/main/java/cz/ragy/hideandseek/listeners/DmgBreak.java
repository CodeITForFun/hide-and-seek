package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;

public class DmgBreak implements Listener {
    public String worldName = ConfigManager.config.getString("Lobby.onJoinWorldName");
    public boolean Damage = ConfigManager.config.getBoolean("Lobby.Damage");
    @EventHandler
    public void Lobby(EntityDamageEvent event) {
        World world = Bukkit.getWorld(worldName);
        if (event.getEntity() instanceof Player) {
            if (event.getEntity().getWorld().equals(world)) {
                if (((Player) event.getEntity()).getGameMode().name().matches("creative")) {
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
}

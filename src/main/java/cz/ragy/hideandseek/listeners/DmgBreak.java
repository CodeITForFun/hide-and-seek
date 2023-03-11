package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DmgBreak implements Listener {
    public String worldName = ConfigManager.config.getString("Lobby.LobbyWorld");
    public boolean damage = ConfigManager.config.getBoolean("Lobby.Damage");
    @EventHandler
    public void Lobby(EntityDamageEvent event) {
        World world = Bukkit.getWorld(worldName);
        if (event.getEntity() instanceof Player && damage) {
            if (event.getEntity().getWorld().equals(world)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
}

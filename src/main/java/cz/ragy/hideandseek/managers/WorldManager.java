package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.HideAndSeek;
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

public class WorldManager implements Listener {
    public File file = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public String worldName = (String) config.get("Lobby.onJoinWorldName");

    public boolean Damage = (boolean) config.get("Lobby.Damage");
    World world = Bukkit.getWorld(worldName);

    @EventHandler
    public void Lobby(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
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

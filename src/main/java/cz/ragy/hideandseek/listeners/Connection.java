package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Connection implements Listener {
    public boolean tpOnJoin = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
    public boolean gvCompass = ConfigManager.config.getBoolean("Lobby.arenaSelectorEnabled");
    public double onJoinX = ConfigManager.config.getDouble("Lobby.onJoinX");
    public double onJoinY = ConfigManager.config.getDouble("Lobby.onJoinY");
    public double onJoinZ = ConfigManager.config.getDouble("Lobby.onJoinZ");

    public double onJoinPitch = ConfigManager.config.getDouble("Lobby.onJoinPitch");
    public double onJoinYaw = ConfigManager.config.getDouble("Lobby.onJoinYaw");
    public String worldName = ConfigManager.config.getString("Lobby.onJoinWorldName");

    public String cmpsName = ConfigManager.config.getString("Lobby.arenaSelectorItemName");
    public List<String> cmpsLore = ConfigManager.config.getStringList("Lobby.arenaSelectorItemLore");
    public String cmpsItem = ConfigManager.config.getString("Lobby.arenaSelectorItemType");
    public Integer cmpsSlot = ConfigManager.config.getInt("Lobby.arenaSelectorSlot");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(tpOnJoin) {
            World world = Bukkit.getWorld(worldName);
            player.teleport(new Location(world, onJoinX, onJoinY, onJoinZ, (float) onJoinYaw, (float) onJoinPitch));
        } else
        if(gvCompass) {
            ItemStack item = new ItemStack(Material.matchMaterial(cmpsItem.toUpperCase()));
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(Colors.translate(cmpsName));
            List<String> jasny = new ArrayList<>();
            cmpsLore.forEach(lor -> {
                jasny.add(Colors.translate(lor));
            });
            itemMeta.setLore(jasny);
            item.setItemMeta(itemMeta);
            event.getPlayer().getInventory().setItem(cmpsSlot - 1, item);
        }
    }
}

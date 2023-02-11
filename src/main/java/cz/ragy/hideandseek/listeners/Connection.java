package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.managers.MessageManager;
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
import java.util.Arrays;
import java.util.List;

public class Connection implements Listener {
    public boolean tpOnJoin = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
    public boolean gvCompass = ConfigManager.config.getBoolean("Items.arenaSelectorEnabled");
    public boolean clearInv = ConfigManager.config.getBoolean("Lobby.clearInventoryOnJoin");
    public String joinMessage = ConfigManager.config.getString("Lobby.onJoinMessage");
    public double onJoinX = ConfigManager.config.getDouble("Lobby.onJoinX");
    public double onJoinY = ConfigManager.config.getDouble("Lobby.onJoinY");
    public double onJoinZ = ConfigManager.config.getDouble("Lobby.onJoinZ");

    public double onJoinPitch = ConfigManager.config.getDouble("Lobby.onJoinPitch");
    public double onJoinYaw = ConfigManager.config.getDouble("Lobby.onJoinYaw");
    public String worldName = ConfigManager.config.getString("Lobby.onJoinWorldName");
    public String cmpsName = ConfigManager.config.getString("Items.arenaSelectorItemName");
    public String cmpsItem = ConfigManager.config.getString("Items.arenaSelectorItemType");
    public Integer cmpsSlot = ConfigManager.config.getInt("Items.arenaSelectorSlot");
    public List<String> lore = ConfigManager.config.getStringList("Items.arenaSelectorItemLore");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        joinMessage = joinMessage.replace("%player%", event.getPlayer().getName()).trim();
        event.setJoinMessage(Colors.translate(joinMessage));
        Player player = event.getPlayer();
        if (clearInv) player.getInventory().clear();
        if(tpOnJoin) {
            World world = Bukkit.getWorld(worldName);
            player.teleport(new Location(world, onJoinX, onJoinY, onJoinZ, (float) onJoinYaw, (float) onJoinPitch));
        }
        if(gvCompass) {
            ItemStack neco = new ItemStack(Material.matchMaterial(cmpsItem.toUpperCase()));
            ItemMeta necoMeta = neco.getItemMeta();
            for (int i = 0; i < lore.size(); i++) {
                String line = lore.get(i);
                lore.set(i, Colors.translate(line));
            }
            necoMeta.setLore(lore);
            necoMeta.setDisplayName(Colors.translate(cmpsName));
            neco.setItemMeta(necoMeta);
            player.getInventory().setItem(cmpsSlot, neco);
        }
    }
}

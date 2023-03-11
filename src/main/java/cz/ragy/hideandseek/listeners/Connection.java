package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.managers.ConfigLoader;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Connection implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String joinMessage = ConfigLoader.joinMessage.replace("%player%", event.getPlayer().getName()).trim();
        event.setJoinMessage(Colors.translate(joinMessage));
        Player player = event.getPlayer();
        if (ConfigLoader.lobbyClearInv) player.getInventory().clear();
        if(ConfigLoader.lobbyTpOnJoin) {
            if(ConfigLoader.lobbyLoc == null)
            player.teleport(new Location(player.getWorld(), 0, 90, 0, 30, 30));
        }
        if(ConfigLoader.lobbyGvCompass) {
            ItemStack neco = new ItemStack(Material.matchMaterial(ConfigLoader.lobbyCmpsItem.toUpperCase()));
            ItemMeta necoMeta = neco.getItemMeta();
            for (int i = 0; i < ConfigLoader.lobbyCpmsLore.size(); i++) {
                String line = ConfigLoader.lobbyCpmsLore.get(i);
                ConfigLoader.lobbyCpmsLore.set(i, Colors.translate(line));
            }
            necoMeta.setLore(ConfigLoader.lobbyCpmsLore);
            necoMeta.setDisplayName(Colors.translate(ConfigLoader.lobbyCmpsName));
            neco.setItemMeta(necoMeta);
            player.getInventory().setItem(ConfigLoader.lobbyCmpsSlot, neco);
        }
    }
}

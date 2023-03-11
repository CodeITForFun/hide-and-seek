package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Connection implements Listener {
    public boolean tpOnJoin = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
    public boolean gvCompass = ConfigManager.config.getBoolean("ArenaSelector.arenaSelectorEnabled");
    public boolean clearInv = ConfigManager.config.getBoolean("Lobby.clearInventoryOnJoin");
    public String joinMessage = ConfigManager.config.getString("Lobby.onJoinMessage");
    public String cmpsName = ConfigManager.config.getString("ArenaSelector.arenaSelectorItemName");
    public String cmpsItem = ConfigManager.config.getString("ArenaSelector.arenaSelectorItemType");
    public Integer cmpsSlot = ConfigManager.config.getInt("ArenaSelector.arenaSelectorSlot");
    public List<String> lore = ConfigManager.config.getStringList("ArenaSelector.arenaSelectorItemLore");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        joinMessage = joinMessage.replace("%player%", event.getPlayer().getName()).trim();
        event.setJoinMessage(Colors.translate(joinMessage));
        Player player = event.getPlayer();
        if (clearInv) player.getInventory().clear();
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

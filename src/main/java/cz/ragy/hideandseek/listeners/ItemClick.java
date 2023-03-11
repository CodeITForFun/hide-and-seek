package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.managers.ConfigLoader;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.menusystem.menus.ArenaSelector;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemClick implements Listener {
    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        ItemStack itemClicked = event.getPlayer().getItemInHand();
        if(itemClicked.getType() == Material.matchMaterial(ConfigLoader.arenaSelectorItemType.toUpperCase()) &&
                itemClicked.getItemMeta().getDisplayName().equals(Colors.translate(ConfigLoader.arenaSelectorItemName))
        ) {
            new ArenaSelector(HideAndSeek.getPlayerMenuUtility(event.getPlayer())).open();
        }
    }
}

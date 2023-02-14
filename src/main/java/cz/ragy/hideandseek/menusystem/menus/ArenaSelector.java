package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.game.Arena;
import cz.ragy.hideandseek.managers.ArenaManager;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.menusystem.Menu;
import cz.ragy.hideandseek.menusystem.PaginatedMenu;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import cz.ragy.hideandseek.utilities.Colors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Material.STONE;

public class ArenaSelector extends PaginatedMenu {
    public ArenaSelector(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Colors.translate(ConfigManager.config.getString("ArenaSelector.arenaSelectorTitle"));
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        switch (item.getType()) {
            case STONE:
                e.getWhoClicked().sendMessage("stone");
                break;
        }
    }

    @Override
    public void setMenuItems() {
        super.addMenuBorder();
        System.out.println(new ArenaManager().arenas);

        if(new ArenaManager().arenas != null && !new ArenaManager().arenas.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= new ArenaManager().arenas.size()) break;
                if (new ArenaManager().arenas.get(index) != null){
                    ///////////////////////////

                    //Create an item from our collection and place it into the inventory
                    ItemStack playerItem = new ItemStack(Material.STONE, 1);
                    ItemMeta playerMeta = playerItem.getItemMeta();
                    playerMeta.setDisplayName(ChatColor.RED + new ArenaManager().arenas.get(index).arenaName);
                    System.out.println(new ArenaManager().arenas.get(index).arenaName);

                    //playerMeta.getPersistentDataContainer().set(new NamespacedKey(MenuManagerSystem.getPlugin(), "uuid"), PersistentDataType.STRING, players.get(index).getUniqueId().toString());
                    playerItem.setItemMeta(playerMeta);

                    inventory.addItem(playerItem);

                    ////////////////////////
                }
            }
        }

    }
}

package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.menusystem.Menu;
import cz.ragy.hideandseek.menusystem.PaginatedMenu;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
        ItemStack testItem = new ItemStack(STONE);
        ItemMeta itemMeta = testItem.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<String>();
        itemMeta.setDisplayName("jozo");
        testItem.setItemMeta(itemMeta);
        super.addMenuBorder();
        inventory.setItem(4, testItem);
    }
}

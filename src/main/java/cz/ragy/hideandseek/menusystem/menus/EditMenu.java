package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.menusystem.Menu;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditMenu extends Menu {
    public EditMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Edit Arena";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setMenuItems() {

    }
}

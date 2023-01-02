package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.listeners.ChatReader;
import cz.ragy.hideandseek.listeners.ChatEvent;
import cz.ragy.hideandseek.menusystem.Menu;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import cz.ragy.hideandseek.utilities.Colors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EditMenu extends Menu {
    /*
    * This menu won't be items editable because it is plugins settings
    * */
    public EditMenu(PlayerMenuUtility playerMenuUtility, String arenaName) {
        super(playerMenuUtility);
        this.arenaname = arenaName;
    }
    private String arenaname;

    @Override
    public String getMenuName() {
        return "Edit Arena " + arenaname;
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch (e.getCurrentItem().getType()) {
            case OAK_SIGN:
                if(ChatEvent.targetPlayer != null) {
                    e.getWhoClicked().sendMessage("Nekdo uz edituje");
                    e.getView().close();
                } else {
                    new ChatReader().createChatConfig("Ahoj", "nn", 30, (Player) e.getWhoClicked(), System.currentTimeMillis(), arenaname);
                }
                arenaname = null;
                e.getView().close();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack sign = new ItemStack(Material.OAK_SIGN);
        ItemMeta signItemMeta = sign.getItemMeta();
        ArrayList<String> signLore = new ArrayList<String>();
        signLore.add(Colors.translate("&7Edits the arena name"));
        signItemMeta.setDisplayName("&cEdit arena name");
        sign.setItemMeta(signItemMeta);
        signItemMeta.setLore(signLore);

        inventory.setItem(13, sign);
    }
}

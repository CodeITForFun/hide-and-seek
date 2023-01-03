package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.listeners.ChatReader;
import cz.ragy.hideandseek.listeners.ChatEvent;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.menusystem.Menu;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import cz.ragy.hideandseek.utilities.Colors;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;

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
                new AnvilGUI.Builder()
                        .onClose(player -> {
                            player.sendMessage("You closed the inventory.");
                        })
                        .onComplete((completion) -> {
                            completion.getPlayer().sendMessage(completion.getText());
                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                        })
                        .preventClose()
                        .text("Arena Name")
                        .itemLeft(new ItemStack(Material.DIAMOND))
                        .onLeftInputClick(player -> player.sendMessage("test"))
                        .onRightInputClick(player -> player.sendMessage("jozoharvat"))
                        .title("Editing Arena Name")
                        .plugin(HideAndSeek.instance)
                        .open((Player) e.getWhoClicked());                                                   //opens the GUI for the player provided
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

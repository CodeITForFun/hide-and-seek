package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.HideAndSeek;
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

import java.io.IOException;
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

    private String newArenaName;

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
                            newArenaName = completion.getText();
                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                        })
                        .preventClose()
                        .text("Arena Name")
                        .itemLeft(new ItemStack(Material.OAK_SIGN))
                        .title("Editing Arena Name")
                        .plugin(HideAndSeek.instance)
                        .open((Player) e.getWhoClicked());
                ConfigurationSection parentSection = ConfigManager.arenas.getConfigurationSection("arenas");
                new ConfigManager().writeToArenaFile();
                ConfigurationSection arenaSection = parentSection.getConfigurationSection(arenaname);

                //TODO: fix error -> arenaSection.set(newArenaName, arenaSection.getValues(true));
                newArenaName = null;
                try {
                    ConfigManager.arenas.save(ConfigManager.arenasFile);
                } catch (IOException error) {
                    error.printStackTrace();
                }
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

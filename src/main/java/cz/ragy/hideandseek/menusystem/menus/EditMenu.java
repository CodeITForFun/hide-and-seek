package cz.ragy.hideandseek.menusystem.menus;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.managers.ArenaManager;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.menusystem.Menu;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import cz.ragy.hideandseek.utilities.Colors;
import cz.ragy.hideandseek.utilities.Digit;
import games.negative.framework.gui.SignGUI;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;

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
                            newArenaName = completion.getText();

                            new ConfigManager().writeToArenaFile();
                            new ArenaManager().renameArena(arenaname, newArenaName);
                            newArenaName = null;
                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                        })
                        .preventClose()
                        .text("Arena Name")
                        .itemLeft(new ItemStack(Material.OAK_SIGN))
                        .title("Editing Arena Name")
                        .plugin(HideAndSeek.instance)
                        .open((Player) e.getWhoClicked());
                break;
            case BEACON:
                new AnvilGUI.Builder()
                        .onClose(player -> {
                            player.sendMessage("You closed the inventory.");
                        })
                        .onComplete((completion) -> {
                            if(new Digit().containsDigits(completion.getText())) {
                                new ArenaManager().changeArenaMaxPlayers(arenaname, Integer.parseInt(completion.getText()));
                                return Arrays.asList(AnvilGUI.ResponseAction.close());
                            } else {
                                completion.getPlayer().sendMessage("You need to use number input and not text input!");
                                return Arrays.asList(AnvilGUI.ResponseAction.close());
                            }
                        })
                        .preventClose()
                        .text("Max players")
                        .itemLeft(new ItemStack(Material.BEACON))
                        .title("Editing max players count")
                        .plugin(HideAndSeek.instance)
                        .open((Player) e.getWhoClicked());
                break;
            case END_PORTAL_FRAME:
                new AnvilGUI.Builder()
                        .onClose(player -> {
                            player.sendMessage("You closed the inventory.");
                        })
                        .onComplete((completion) -> {
                            new ArenaManager().changeArenaWorld(arenaname, completion.getText());
                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                        })
                        .preventClose()
                        .text("Arena world")
                        .itemLeft(new ItemStack(Material.BEACON))
                        .title("Editing arena world")
                        .plugin(HideAndSeek.instance)
                        .open((Player) e.getWhoClicked());
                break;
            case BELL:
                new AnvilGUI.Builder()
                        .onClose(player -> {
                            player.sendMessage("You closed the inventory.");
                        })
                        .onComplete((completion) -> {
                            if(new Digit().containsDigits(completion.getText())) {
                                new ArenaManager().changeMinPlayers(arenaname, Integer.parseInt(completion.getText()));
                                return Arrays.asList(AnvilGUI.ResponseAction.close());
                            } else {
                                completion.getPlayer().sendMessage("You need to use number input and not text input!");
                                return Arrays.asList(AnvilGUI.ResponseAction.close());
                            }
                        })
                        .preventClose()
                        .text("Arena min players")
                        .itemLeft(new ItemStack(Material.BELL))
                        .title("Editing arena min players")
                        .plugin(HideAndSeek.instance)
                        .open((Player) e.getWhoClicked());
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack sign = new ItemStack(Material.OAK_SIGN);
        ItemStack beacon = new ItemStack(Material.BEACON);
        ItemStack end = new ItemStack(Material.END_PORTAL_FRAME);
        ItemStack bell = new ItemStack(Material.BELL);

        ItemMeta signItemMeta = sign.getItemMeta();
        ItemMeta beaconItemMeta = beacon.getItemMeta();
        ItemMeta endItemMeta = end.getItemMeta();
        ItemMeta bellMeta = bell.getItemMeta();

        ArrayList<String> signLore = new ArrayList<>();
        ArrayList<String> beaconLore = new ArrayList<>();
        ArrayList<String> endLore = new ArrayList<>();
        ArrayList<String> bellLore = new ArrayList<>();

        signLore.add(Colors.translate("&7Arena Name"));
        signItemMeta.setDisplayName(Colors.translate("&cEdit arena name"));
        signItemMeta.setLore(signLore);
        sign.setItemMeta(signItemMeta);

        beaconLore.add(Colors.translate("&7Max Players"));
        beaconItemMeta.setDisplayName(Colors.translate("&cEdit max player count"));
        beaconItemMeta.setLore(beaconLore);
        beacon.setItemMeta(beaconItemMeta);

        endLore.add(Colors.translate("&7Arena World"));
        endItemMeta.setDisplayName(Colors.translate("&cEdit the arena world name"));
        endItemMeta.setLore(endLore);
        end.setItemMeta(endItemMeta);

        bellLore.add(Colors.translate("&7Min players"));
        bellMeta.setDisplayName(Colors.translate("&cEdit the min players"));
        bellMeta.setLore(bellLore);
        bell.setItemMeta(bellMeta);

        inventory.setItem(13, sign);
        inventory.setItem(11, beacon);
        inventory.setItem(9, end);
        inventory.setItem(15, bell);
    }
}

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
        if(new ArenaManager().STATICARENAS != null && !new ArenaManager().STATICARENAS.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= new ArenaManager().STATICARENAS.size()) break;
                if (new ArenaManager().STATICARENAS.get(index) != null){
                    ///////////////////////////

                    //Create an item from our collection and place it into the inventory
                    ItemStack playerItem = new ItemStack(Material.STONE, 1);
                    ItemMeta playerMeta = playerItem.getItemMeta();
                    playerMeta.setDisplayName(ChatColor.RED + new ArenaManager().STATICARENAS.get(index).arenaName);
                    ArrayList<String> jj = new ArrayList<>();
                    jj.add(Colors.translate(new ArenaManager().STATICARENAS.get(index).arenaWorldName));
                    jj.add(Colors.translate(String.valueOf(new ArenaManager().STATICARENAS.get(index).maxPlayers)));
                    playerMeta.setLore(jj);

                    //playerMeta.getPersistentDataContainer().set(new NamespacedKey(MenuManagerSystem.getPlugin(), "uuid"), PersistentDataType.STRING, players.get(index).getUniqueId().toString());
                    playerItem.setItemMeta(playerMeta);

                    inventory.addItem(playerItem);

                    ////////////////////////
                }
            }
        }

    }
}

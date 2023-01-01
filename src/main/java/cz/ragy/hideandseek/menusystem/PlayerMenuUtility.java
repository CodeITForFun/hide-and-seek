package cz.ragy.hideandseek.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {
    private Player owner;
    private Player playerToKill;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
}

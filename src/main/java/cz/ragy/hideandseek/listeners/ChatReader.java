package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.managers.MessageManager;
import cz.ragy.hideandseek.menusystem.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class ChatReader implements Listener {

    public UUID playerMenu;
    public int timeOut;

    public long timeMenu;
    public String arenaName;

    public void createChatConfig(String messageTitle, String messageSubtitle, Integer timeoutSeconds, Player playerMenu, long CurrentTime, String arenaName) {
        timeOut = timeoutSeconds;
        timeMenu = CurrentTime;
        this.playerMenu = playerMenu.getUniqueId();
        this.arenaName = arenaName;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UUID playerChat = event.getPlayer().getUniqueId();
        if(System.currentTimeMillis() - timeMenu < timeOut  && playerMenu == playerChat) {
            new MessageManager().printInfo(event.getMessage());
            //new ConfigManager().editArena(event.getMessage(), arenaName, );
            playerMenu = null;
            timeMenu = 0;
            arenaName = null;
            event.setCancelled(true);
        } else {
            new MessageManager().printInfo("you aint editin");
            playerMenu = null;
            timeMenu = 0;
            arenaName = null;
            event.setCancelled(false);
        }
    }

}

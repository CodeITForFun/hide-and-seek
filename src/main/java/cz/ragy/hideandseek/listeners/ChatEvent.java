package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.chatreading.ChatReader;
import cz.ragy.hideandseek.managers.ConfigManager;
import cz.ragy.hideandseek.managers.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class ChatEvent implements Listener {

    public static UUID targetPlayer;
    public static long time;

    public static String arenaName;
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();
        new MessageManager().printInfo("Defautl message: " + event.getMessage());
        new MessageManager().printInfo("Current: " + System.currentTimeMillis());
        new MessageManager().printInfo("Time: " + time);
        new MessageManager().printInfo("target: " + targetPlayer);
        if(System.currentTimeMillis() - time < 30000 && playerUUID == targetPlayer) {
            new MessageManager().printInfo(event.getMessage());
            //new ConfigManager().editArena(event.getMessage(), arenaName, );
            targetPlayer = null;
            time = 0;
            event.setCancelled(true);
        } else {
            targetPlayer = null;
            time = 0;
            event.setCancelled(false);
            arenaName = null;
        }
    }
}

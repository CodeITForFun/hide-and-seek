package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.utilities.Colors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;


public class MessageManager extends BukkitRunnable {
    private int lastMessage;
    @Override
    public void run() {
        String message = "";
        if (!ConfigLoader.random) {
            if (lastMessage >= ConfigLoader.messages.size()) {
                lastMessage = 0;
            }
            message = ConfigLoader.messages.get(lastMessage);
            lastMessage++;
        } else {
            Random random = new Random();
            int nextMessage = random.nextInt(ConfigLoader.messages.size());
            while (nextMessage == lastMessage) {
                nextMessage = random.nextInt(ConfigLoader.messages.size());
            }
            message = ConfigLoader.messages.get(nextMessage);
            lastMessage = nextMessage;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(PlaceholderAPI.setPlaceholders(player.getPlayer(), Colors.translate(message)));
        }
    }
    public void printInfo(String s) { HideAndSeek.instance.getLogger().info(s); }
    public void printWarn(String s) { HideAndSeek.instance.getLogger().warning(s); }
}
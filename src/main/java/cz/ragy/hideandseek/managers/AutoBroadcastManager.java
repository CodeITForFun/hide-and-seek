package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.utilities.Colors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.List;
import java.util.Random;


public class AutoBroadcastManager extends BukkitRunnable {
    private List<String> messages;
    private boolean random;
    private int lastMessage;

    public AutoBroadcastManager(HideAndSeek plugin) {
        this.random = ConfigManager.config.getBoolean("Auto-Broadcast.random");
        this.messages = ConfigManager.config.getStringList("Auto-Broadcast.messages");
    }

    @Override
    public void run() {
        String message = "";
        if (!random)
        {
            try
            {
                message = messages.get(lastMessage + 1);
                lastMessage++;
            } catch (ArrayIndexOutOfBoundsException e)
            {
                message = messages.get(0);
                lastMessage = 0;
            }
        } else
        {
            Random random = new Random();
            int nextMessage = random.nextInt(messages.size());
            while (nextMessage == lastMessage)
            {
                nextMessage = random.nextInt(messages.size());
            }
            message = messages.get(nextMessage);
            lastMessage = nextMessage;
        }
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.sendMessage(PlaceholderAPI.setPlaceholders(player.getPlayer(), Colors.translate(message)));
        }
    }
}

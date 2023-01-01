package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class Connection implements Listener {
    public boolean tpOnJoin = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
    public double onJoinX = ConfigManager.config.getDouble("Lobby.onJoinX");
    public double onJoinY = ConfigManager.config.getDouble("Lobby.onJoinY");
    public double onJoinZ = ConfigManager.config.getDouble("Lobby.onJoinZ");

    public double onJoinPitch = ConfigManager.config.getDouble("Lobby.onJoinPitch");
    public double onJoinYaw = ConfigManager.config.getDouble("Lobby.onJoinYaw");
    public String worldName = ConfigManager.config.getString("Lobby.onJoinWorldName");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(tpOnJoin) {
            World world = Bukkit.getWorld(worldName);
            player.teleport(new Location(world, onJoinX, onJoinY, onJoinZ, (float) onJoinYaw, (float) onJoinPitch));
        }
    }
}

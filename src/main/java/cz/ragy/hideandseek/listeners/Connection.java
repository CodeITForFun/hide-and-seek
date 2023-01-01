package cz.ragy.hideandseek.listeners;

import cz.ragy.hideandseek.HideAndSeek;
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
    private final File configFile = new File(HideAndSeek.instance.getDataFolder(), "config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public boolean tpOnJoin = (boolean) config.get("Lobby.onJoinLobby");

    public double onJoinX = (double) config.get("Lobby.onJoinX");
    public double onJoinY = (double) config.get("Lobby.onJoinY");
    public double onJoinZ = (double) config.get("Lobby.onJoinZ");

    public double onJoinPitch = (double) config.get("Lobby.onJoinPitch");
    public double onJoinYaw = (double) config.get("Lobby.onJoinYaw");
    public String worldName = (String) config.get("Lobby.onJoinWorldName");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(tpOnJoin) {
            World world = Bukkit.getWorld(worldName);
            player.teleport(new Location(world, onJoinX, onJoinY, onJoinZ, (float) onJoinYaw, (float) onJoinPitch));
        }
    }
}

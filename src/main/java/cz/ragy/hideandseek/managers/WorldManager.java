package cz.ragy.hideandseek.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldManager {
    public static void teleportToWorld(String WorldName, double x, double y, double z, float yaw, float pitch, Player player){
        Location loc = new Location(Bukkit.getServer().getWorld(WorldName), x , y , z , yaw, pitch);
        player.teleport(loc);
    }
}

package cz.ragy.hideandseek.runnables;

import org.bukkit.Location;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockRunnable extends BukkitRunnable {

    FallingBlock block;
    Player player;

    public BlockRunnable(FallingBlock block, Player player){
        this.player = player;
        this.block = block;
    }

    @Override
    public void run() {
        block.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));
    }
}

package cz.ragy.hideandseek.Game;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class Arena {
    public int maxPlayers;
    public int minPlayers;
    public int seekersCount;
    public int hidersCount;
    public List<Player> arenaPlayers;
    public String arenaName;
    public String arenaWorldName;
    public boolean playing;

    public Arena(Integer maxPlayers, Integer minPlayers, Integer seekersCount, Integer hidersCount, String arenaName, String arenaWorldName){
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.seekersCount = seekersCount;
        this.hidersCount = hidersCount;
        this.arenaName = arenaName;
        this.arenaWorldName = arenaWorldName;
    }
    public void removeArenaPlayer(Player player){
        this.arenaPlayers.remove(player);
    }
    public void addArenaPlayers(List<Player> players){
        players.forEach(player -> this.arenaPlayers.add(player));
    }

}

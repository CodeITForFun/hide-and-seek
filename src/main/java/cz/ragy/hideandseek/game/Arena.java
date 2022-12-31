package cz.ragy.hideandseek.game;

import org.bukkit.entity.Player;

import java.util.List;

public class Arena {
    public String arenaName;
    public String arenaWorldName;
    public int maxPlayers;
    public int minPlayers;
    public int seekersCount;
    public int hidersCount;
    private List<Player> arenaPlayers;
    private boolean playing;

    public Arena(String arenaName, String arenaWorldName, Integer maxPlayers, Integer minPlayers, Integer seekersCount){
        this.arenaName = arenaName;
        this.arenaWorldName = arenaWorldName;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.seekersCount = seekersCount;
        this.hidersCount = this.maxPlayers - this.seekersCount;
    }
    public void removeArenaPlayer(Player player){
        this.arenaPlayers.remove(player);
    }
    public void addArenaPlayers(List<Player> players){
        players.forEach(player -> this.arenaPlayers.add(player));
    }

}

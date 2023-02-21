package cz.ragy.hideandseek.game;

import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {
    public String arenaName;
    public String arenaWorldName;
    public int maxPlayers;
    public int minPlayers;
    public int seekersCount;
    public int hidersCount;
    public Location lobbyLocation;
    public Location hidersLocation;
    public Location seekersLocation;
    public List<UUID> players = new ArrayList<>();
    public boolean playing;
    public List<MiscDisguise> hiders = new ArrayList<>();
    public List<UUID> seekers = new ArrayList<>();

    public Arena(String arenaName, String arenaWorldName, Integer maxPlayers, Integer minPlayers, Integer seekersCount, Location lobbyLocation, Location hidersLocation, Location seekersLocation){
        this.arenaName = arenaName;
        this.arenaWorldName = arenaWorldName;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.seekersCount = seekersCount;
        this.hidersCount = this.maxPlayers - this.seekersCount;
        this.lobbyLocation = lobbyLocation;
        this.hidersLocation = hidersLocation;
        this.seekersLocation = seekersLocation;
    }
    public void removeArenaPlayer(Player player){
        this.players.remove(player);
    }
    public void addPlayer(Player player){
        players.add(player.getUniqueId());
    }
    public void setPlaying(boolean p){
        this.playing = p;
    }
    public void addHider(MiscDisguise disg) {
        hiders.add(disg);
    }

    public boolean isPlayerPlaying(Player player) {
        if(players.contains(player)) {
            return true;
        }
        else return false;
    }
}

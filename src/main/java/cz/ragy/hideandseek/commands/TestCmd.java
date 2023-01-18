package cz.ragy.hideandseek.commands;

import cz.ragy.hideandseek.HideAndSeek;
import cz.ragy.hideandseek.helpers.RunnableHelper;
import cz.ragy.hideandseek.runnables.BlockRunnable;
import games.negative.framework.command.Command;
import games.negative.framework.command.annotation.CommandInfo;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.getServer;

@CommandInfo(
            name = "change",
            description = "Changes player to block!",
            permission = "shit.blah",
            aliases = {"ch", "dadadadd"},
            playerOnly = true,
            disabled = false,
            args = {"block"})
public class TestCmd extends Command {
    private FallingBlock fallingBlock;
        @Override
        public void onCommand(CommandSender sender, String[] args) {
            if(Material.matchMaterial(args[0].toUpperCase()) != null){
                Player player = (Player) sender;
                Material mat = Material.matchMaterial(args[0].toUpperCase());
                ItemStack item = new ItemStack(mat);
                MiscDisguise miscDisguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, item.getType());
                miscDisguise.setEntity(player);
                miscDisguise.setNotifyBar(null);
                miscDisguise.startDisguise();
                RunnableHelper.players.add(((Player) sender).getUniqueId());

            } else {
                sender.sendMessage("Not valid material");
            }
        }

}

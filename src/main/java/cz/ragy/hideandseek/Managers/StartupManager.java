package cz.ragy.hideandseek.Managers;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class StartupManager {
    public void startup(Server server, Plugin plugin){
        String devs = Arrays.asList(plugin.getDescription().getAuthors()).toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        server.getLogger().info("\n\n\n" +
                "\n" +
                "  _    _ _     _                         _  _____           _    \n" +
                " | |  | (_)   | |        /\\             | |/ ____|         | |   \n" +
                " | |__| |_  __| | ___   /  \\   _ __   __| | (___   ___  ___| | __\n" +
                " |  __  | |/ _` |/ _ \\ / /\\ \\ | '_ \\ / _` |\\___ \\ / _ \\/ _ \\ |/ /\n" +
                " | |  | | | (_| |  __// ____ \\| | | | (_| |____) |  __/  __/   < \n" +
                " |_|  |_|_|\\__,_|\\___/_/    \\_\\_| |_|\\__,_|_____/ \\___|\\___|_|\\_\\\n" +
                "                                                                 \n" +
                "                                                                 \n" +
                " -> Successfully enabled " + plugin.getName() + "\n" +
                " -> Version: " + plugin.getDescription().getVersion() + "\n" +
                " -> Developers: " + devs + "\n\n\n");
    }
}

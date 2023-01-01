package cz.ragy.hideandseek.managers;

import cz.ragy.hideandseek.commands.MainCommand;
import cz.ragy.hideandseek.HideAndSeek;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class StartupManager {
    public void startup(Server server, Plugin plugin, HideAndSeek instance){
        String devs = Arrays.asList(plugin.getDescription().getAuthors()).toString()
                .replace("[", "")
                .replace("]", "")
                .trim();

        server.getLogger().info("Loading config files...");
        new ConfigManager().startup();

        server.getLogger().info("Loading commands...");
        instance.getCommand("has").setExecutor(new MainCommand());

        server.getLogger().info("Loading events...");

        for(Class<?> clazz : new Reflections("cz.ragy.hideandseek.listeners")
                .getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz
                        .getDeclaredConstructor()
                        .newInstance();
                server.getPluginManager().registerEvents(listener, plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
            {
                e.printStackTrace();
            }
        }
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
                " -> Developers: " + devs +
                "\n" +
                "\n\n\n");
    }
}


package cz.ragy.hideandseek.managers;

import org.bukkit.Location;

import java.util.List;

public class ConfigLoader {
    public static String arenaLeave;
    public static String arenaNotConnected;
    public static String arenaDoesntExist;
    public static String arenaNowEditing;
    public static String arenaSelectorItemType;
    public static String arenaSelectorItemName;
    public static String reload;
    public static String sucReloaded;
    public static String lobbySet;
    public static boolean lobbySpawnStatus;
    public static String lobbyWarning;
    public static String arenaInvalidMessage;
    public static String notEntity;

    public static String arenaSelectorTitle;
    public static String prefix;
    public static String noPerms;
    public static boolean lobbyTpOnJoin;
    public static Location lobbyLoc;
    public static boolean lobbyGvCompass;
    public static boolean lobbyClearInv;
    public static String joinMessage;
    public static String lobbyCmpsName;
    public static String lobbyCmpsItem;
    public static Integer lobbyCmpsSlot;
    public static List<String> lobbyCpmsLore;
    public static String lobbyWorldName;
    public static boolean lobbyDamage;
    public static String databaseType;
    public static String databaseHost;
    public static String database;
    public static String databaseUsername;
    public static String connectedToDB;
    public static String failedToConnect;
    public static String databasePassword;
    public static List<String> messages;
    public static boolean random;
    public static String arenaItem;

    public ConfigLoader() {
        arenaLeave = ConfigManager.config.getString("Arena.LeaveArena");
        arenaNotConnected = ConfigManager.config.getString("Arena.NotConnected");
        reload = ConfigManager.config.getString("Reload.Reload-Message");
        sucReloaded = ConfigManager.config.getString("Reload.Successfully-Reloaded");
        lobbySet = ConfigManager.config.getString("Lobby.Success");
        lobbySpawnStatus = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
        lobbyWarning = ConfigManager.config.getString("Lobby.Warning");
        arenaInvalidMessage = ConfigManager.config.getString("Create-Arena.Invalid-Message");
        notEntity = ConfigManager.config.getString("Core.notEntity");
        prefix = ConfigManager.config.getString("Core.Prefix");
        noPerms = ConfigManager.config.getString("Core.No-Permission");
        lobbyTpOnJoin = ConfigManager.config.getBoolean("Lobby.onJoinLobby");
        lobbyLoc = ConfigManager.config.getLocation("Lobby.LobbyLocation");
        lobbyGvCompass = ConfigManager.config.getBoolean("ArenaSelector.arenaSelectorEnabled");
        lobbyClearInv = ConfigManager.config.getBoolean("Lobby.clearInventoryOnJoin");
        joinMessage = ConfigManager.config.getString("Lobby.onJoinMessage");
        lobbyCmpsName = ConfigManager.config.getString("ArenaSelector.arenaSelectorItemName");
        lobbyCmpsItem = ConfigManager.config.getString("ArenaSelector.arenaSelectorItemType");
        lobbyCmpsSlot = ConfigManager.config.getInt("ArenaSelector.arenaSelectorSlot");
        lobbyCpmsLore = ConfigManager.config.getStringList("ArenaSelector.arenaSelectorItemLore");
        lobbyWorldName = ConfigManager.config.getString("Lobby.LobbyWorld");
        lobbyDamage = ConfigManager.config.getBoolean("Lobby.Damage");
        databaseType = ConfigManager.config.getString("Database.Type");
        databaseHost = ConfigManager.config.getString("Database.host");
        database = ConfigManager.config.getString("Database.database");
        databaseUsername = ConfigManager.config.getString("Database.username");
        databasePassword = ConfigManager.config.getString("Database.password");
        messages = ConfigManager.config.getStringList("Auto-Broadcast.messages");
        random = ConfigManager.config.getBoolean("Auto-Broadcast.random");
        arenaDoesntExist = ConfigManager.config.getString("Arena.ArenaDoesntExist");
        arenaNowEditing = ConfigManager.config.getString("Arena.NowEditingArena");
        arenaSelectorItemName = ConfigManager.config.getString("ArenaSelector.arenaSelectorItemName");
        arenaSelectorItemType = ConfigManager.config.getString("ArenaSelector.arenaSelectorItemType");
        failedToConnect = ConfigManager.config.getString("Database.Failed");
        connectedToDB = ConfigManager.config.getString("Database.Connected");
        arenaSelectorTitle = ConfigManager.config.getString("ArenaSelector.arenaSelectorTitle");
        arenaItem = ConfigManager.config.getString("ArenaSelector.arenaItem");
    }
}

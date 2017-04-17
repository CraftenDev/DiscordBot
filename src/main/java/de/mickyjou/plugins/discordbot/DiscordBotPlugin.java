package de.mickyjou.plugins.discordbot;

import de.craften.plugins.playerdatastore.api.PlayerDataStoreService;
import de.mickyjou.plugins.discordbot.commands.discord.InfoCommand;
import de.mickyjou.plugins.discordbot.commands.discord.StatsCommand;
import de.mickyjou.plugins.discordbot.listeners.discord.SimpleCommandListener;
import de.mickyjou.plugins.discordbot.listeners.minecraft.PlayerDeathListener;
import de.mickyjou.plugins.discordbot.listeners.minecraft.PlayerJoinListener;
import de.mickyjou.plugins.discordbot.listeners.minecraft.PlayerQuitListener;
import de.mickyjou.plugins.discordbot.utils.DiscordCommandHandler;
import de.mickyjou.plugins.discordbot.utils.EventTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class DiscordBotPlugin extends JavaPlugin {

    public static IDiscordClient client;
    public static ArrayList<EventTeam> teams;
    public String BOT_TOKEN;
    public File file = new File(getDataFolder(), "config.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private boolean validToken;
    public static String prefix = ChatColor.GOLD + "[PvPEvent] " + ChatColor.GRAY;
    private static PlayerDataStoreService pds;




    @Override
    public void onEnable() {

        //register all necessary Bukkit-Services
        createConfigIfNotExists();
        initializeToken();
        registerBukkitServices();
        registerBukkitCommands();
        registerBukkitListener();

        //Build the DiscordBot
        if (BOT_TOKEN == null) {

            Bukkit.broadcastMessage("token null");
            getLogger().warning("Please enter a valid Bot-Token to use this plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        validToken = true;

        //Client-Builder
        try {
            client = new ClientBuilder().withToken(BOT_TOKEN).build();
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().severe("Failed to build client");
        }


        //register listeners and commands
        registerDiscordListener();
        registerDiscordCommands();


        try {
            client.login();
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().severe("Failed to login");
        }



    }

    @Override
    public void onDisable() {

        if(validToken) {
            try {
                client.logout();
            } catch (DiscordException ignored) {
                getLogger().warning("Could not logout");
            }
        }
    }

    private void registerBukkitCommands() {

    }

    private void registerBukkitListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerQuitListener(),this);
    }

    private void registerBukkitServices() {
        pds = Bukkit.getServer().getServicesManager()
                .getRegistration(PlayerDataStoreService.class).getProvider();
    }

    private void registerDiscordListener() {
        client.getDispatcher().registerListener(new SimpleCommandListener(this));
    }

    private void registerDiscordCommands() {
        DiscordCommandHandler.commands.put("info", new InfoCommand(this));
        DiscordCommandHandler.commands.put("stats", new StatsCommand(this));

    }

    private void initializeToken() {
        if (cfg.getString("DISCORD.BOT_TOKEN") != null) {
            BOT_TOKEN = cfg.getString("DISCORD.BOT_TOKEN");
        }
    }

    private void createConfigIfNotExists() {
        if (!file.exists()) {
            ConfigurationSection discordSection = cfg.createSection("DISCORD");
            discordSection.set("BOT_TOKEN", "");
            discordSection.set("BOT_CHANNEL" , "");
            discordSection.set("ADMIN_COMMAND_CHANNEL", "");
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static PlayerDataStoreService getPDSService() {
        return pds;
    }
}

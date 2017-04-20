package de.mickyjou.plugins.discordbot.commands.discord;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import de.mickyjou.plugins.discordbot.utils.StatsGetter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;

public class StatsCommand implements Command {

    private DiscordBotPlugin plugin;

    public StatsCommand(DiscordBotPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        IChannel privateChannel = event.getAuthor().getOrCreatePMChannel();
        boolean nameExists = false;
        if (args.length == 0) {
            for (OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                if (all.getName().equalsIgnoreCase(event.getAuthor().getName())) {
                    nameExists = true;

                    StatsGetter stats = new StatsGetter(Bukkit.getOfflinePlayer(event.getAuthor().getName()));

                    String line = System.getProperty("line.separator");

                    privateChannel.sendMessage("Team: " + stats.getTeam() + line +
                            "Team-Mate: " + Bukkit.getOfflinePlayer(stats.getTeamMate()).getName() + line +
                            "Kills: " + stats.getKills() + line +
                            "Verwarnungen: " + stats.getWarnings()
                    );
                    break;

                }
            }
            if(!nameExists){
                privateChannel.sendMessage("Could not find the User with the name " + event.getAuthor().getName());
            }
        } else if (args.length == 1) {
            String name = args[0];

            for (OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                if (all.getName().equalsIgnoreCase(name)) {
                    nameExists = true;

                    StatsGetter stats = new StatsGetter(Bukkit.getOfflinePlayer(name));

                    String line = System.getProperty("line.separator");

                    privateChannel.sendMessage("Team: " + stats.getTeam() + line +
                            "Team-Mate: " + Bukkit.getOfflinePlayer(stats.getTeamMate()).getName() + line +
                            "Kills: " + stats.getKills() + line +
                            "Verwarnungen: " + stats.getWarnings()
                    );
                    break;

                }
            }
            if(!nameExists){
                privateChannel.sendMessage("Could not find the User with the name " + name);
            }
        } else {
            privateChannel.sendMessage("Wrong Usage. Please use !stats or !stats [name].");
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        plugin.getLogger().info("Executed " + this.getClass().getName() + " Command.");
    }

    @Override
    public String help() {
        return null;
    }
}

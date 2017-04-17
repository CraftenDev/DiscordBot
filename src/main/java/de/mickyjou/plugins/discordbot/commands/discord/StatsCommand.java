package de.mickyjou.plugins.discordbot.commands.discord;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import de.mickyjou.plugins.discordbot.messages.PrivateDiscordMessage;
import de.mickyjou.plugins.discordbot.utils.StatsGetter;
import org.bukkit.Bukkit;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

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
        if (args.length == 1) {
            String name = args[1];
            StatsGetter stats = new StatsGetter(Bukkit.getOfflinePlayer(name));

            String line = System.getProperty("line.separator");
            new PrivateDiscordMessage("Team: " + stats.getTeam() + line +
                    "TeamMate: " + Bukkit.getOfflinePlayer(stats.getTeamMate()).getName() + line +
                    "Kills: " + stats.getKills() + line +
                    "Verwarnungen: " + stats.getWarnings()


                    , event).queue();


        } else if (args.length == 0) {


            String name = event.getAuthor().getName().trim();
            StatsGetter stats = new StatsGetter(Bukkit.getOfflinePlayer(name));

            String line = System.getProperty("line.separator");

            new PrivateDiscordMessage("Team: " + stats.getTeam() + line +
                    "TeamMate: " + Bukkit.getOfflinePlayer(stats.getTeamMate()).getName() + line +
                    "Kills: " + stats.getKills() + line +
                    "Verwarnungen: " + stats.getWarnings()


                    , event).queue();

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

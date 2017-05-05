package de.mickyjou.plugins.discordbot.commands.discord;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import de.mickyjou.plugins.discordbot.utils.EventTeam;
import de.mickyjou.plugins.discordbot.utils.StatsGetter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CreateTeamCommand implements Command {
    private DiscordBotPlugin plugin;

    public CreateTeamCommand(DiscordBotPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length == 4) {
            String author = event.getAuthor().getName();
            for (OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                if (all.getName() == author) {
                    if (all.isOp()) {
                        String name = args[1];
                        OfflinePlayer p1 = Bukkit.getOfflinePlayer(args[2]);
                        StatsGetter stats1 = new StatsGetter(p1);
                        StatsGetter stats2 = new StatsGetter(p1);
                        OfflinePlayer p2 = Bukkit.getOfflinePlayer(args[3]);
                        if (stats1.getTeam() == null) {
                            if (stats2.getTeam() == null) {
                                EventTeam team = new EventTeam(name, p1.getUniqueId().toString(), p2.getUniqueId().toString());
                                team.save();
                                event.getAuthor().getOrCreatePMChannel().sendMessage("Du hast Erfolgreich das Team erstellt.");
                            } else {
                                event.getAuthor().getOrCreatePMChannel().sendMessage("Der Spieler §6" + p1.getName() + " §7ist bereits in einem Team.");
                            }
                        } else {
                            event.getAuthor().getOrCreatePMChannel().sendMessage("Der Spieler §6" + p2.getName() + " §7ist bereits in einem Team.");
                        }
                        break;
                    }
                }
            }

            event.getAuthor().getOrCreatePMChannel().sendMessage("Could not find the user with the name " + args[1] + " who has OP on the Server.");
        }else{
            event.getAuthor().getOrCreatePMChannel().sendMessage("!team create (name) (player1) (player2)");
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

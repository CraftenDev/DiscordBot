package de.mickyjou.plugins.discordbot.commands.discord;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class InfoCommand implements Command {

    private final DiscordBotPlugin plugin;

    public InfoCommand(DiscordBotPlugin plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String line = System.getProperty("line.separator");

        event.getAuthor().getOrCreatePMChannel().sendMessage("Dies ist der Bot vom Craften PvP-Event." + line + "Weitere Infos findest du im Forum!");
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

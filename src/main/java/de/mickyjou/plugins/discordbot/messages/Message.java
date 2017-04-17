package de.mickyjou.plugins.discordbot.messages;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.UUID;
import java.util.logging.Logger;

public abstract class Message {
    public String message;
    public UUID playerUUID;
    public MessageReceivedEvent event;
    public Logger logger = DiscordBotPlugin.getPlugin(DiscordBotPlugin.class).getLogger();


    public abstract void queue();
    public String getMessage() {return message;}
}

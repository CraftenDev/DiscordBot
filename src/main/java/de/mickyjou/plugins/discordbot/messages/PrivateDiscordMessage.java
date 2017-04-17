package de.mickyjou.plugins.discordbot.messages;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PrivateDiscordMessage extends Message{

    public PrivateDiscordMessage(String content, MessageReceivedEvent event){
        this.message = content;
        this.event = event;
    }


    @Override
    public void queue() {
        event.getAuthor().getOrCreatePMChannel().sendMessage(message);
    }
}

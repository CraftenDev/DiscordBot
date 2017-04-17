package de.mickyjou.plugins.discordbot.messages;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import sx.blah.discord.handle.obj.IChannel;

import java.util.List;

public class DiscordMessage extends Message {
    private DiscordBotPlugin plugin = DiscordBotPlugin.getPlugin(DiscordBotPlugin.class);

    public DiscordMessage(String content) {
        this.message = message;
    }

    public void queue() {
        List<IChannel> channels = DiscordBotPlugin.client.getChannels();
        for(IChannel channel: channels){
            if(channel.getName().equalsIgnoreCase(plugin.cfg.getConfigurationSection("DISCORD").getString("BOT_CHANNEL"))){
                channel.sendMessage(message);
            }
        }
    }


}

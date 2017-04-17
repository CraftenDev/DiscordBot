package de.mickyjou.plugins.discordbot.listeners.discord;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import de.mickyjou.plugins.discordbot.utils.DiscordCommandHandler;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SimpleCommandListener {

    private DiscordBotPlugin plugin;
    private String BOT_CHANNEL;

    public SimpleCommandListener(DiscordBotPlugin plugin) {
        this.plugin = plugin;
        this.BOT_CHANNEL = plugin.cfg.getConfigurationSection("DISCORD").getString("BOT_CHANNEL");
    }


    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent e) {
        if(BOT_CHANNEL == null){
            plugin.getLogger().warning("Please enter a valid Channel-Name");
            return;
        }
        if(e.getChannel().getName().equalsIgnoreCase(BOT_CHANNEL) || e.getChannel().isPrivate()){
            if (e.getMessage().getContent().startsWith("!") ) {
                DiscordCommandHandler.handleCommand(DiscordCommandHandler.parser.parse(e.getMessage().getContent(), e));
            }
        }
    }

}

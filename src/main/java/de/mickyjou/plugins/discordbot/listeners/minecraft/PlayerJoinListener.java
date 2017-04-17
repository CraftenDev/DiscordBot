package de.mickyjou.plugins.discordbot.listeners.minecraft;

import de.mickyjou.plugins.discordbot.messages.DiscordMessage;
import de.mickyjou.plugins.discordbot.utils.StatsGetter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        StatsGetter stats = new StatsGetter(e.getPlayer());
        new DiscordMessage("Der Spieler " + e.getPlayer().getName()  + " aus dem Team " + stats.getTeam() +  " hat den Server betreten.").queue();
    }
}

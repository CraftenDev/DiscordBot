package de.mickyjou.plugins.discordbot.listeners.minecraft;

import de.mickyjou.plugins.discordbot.messages.DiscordMessage;
import de.mickyjou.plugins.discordbot.utils.StatsGetter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        StatsGetter stats = new StatsGetter(e.getEntity());
        new DiscordMessage("Der Spieler " + e.getEntity().getName() + " aus dem Team "  + stats.getTeam()+ " ist gestorben und somit aus den Projekt ausgeschieden.").queue();
    }
}

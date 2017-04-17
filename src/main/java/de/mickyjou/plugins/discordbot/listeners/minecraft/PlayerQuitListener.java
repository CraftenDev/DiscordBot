package de.mickyjou.plugins.discordbot.listeners.minecraft;

import de.mickyjou.plugins.discordbot.messages.DiscordMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        new DiscordMessage("Der Spieler " + e.getPlayer().getName() + " hat den Server verlassen.").queue();
    }
}

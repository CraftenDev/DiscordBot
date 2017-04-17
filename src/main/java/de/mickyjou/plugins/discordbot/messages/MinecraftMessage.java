package de.mickyjou.plugins.discordbot.messages;

import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import org.bukkit.Bukkit;

import java.util.UUID;

public class MinecraftMessage extends Message {

    public MinecraftMessage(String content, UUID playerUUID) {
        this.message = DiscordBotPlugin.prefix + message;
        this.playerUUID = playerUUID;
    }

    public void queue() {
         if(playerUUID == null){
             Bukkit.broadcastMessage(message);
         }else{
             if(Bukkit.getPlayer(playerUUID) != null && Bukkit.getPlayer(playerUUID).isOnline()){
                 Bukkit.getPlayer(playerUUID).sendMessage(message);
             }
         }
    }
}

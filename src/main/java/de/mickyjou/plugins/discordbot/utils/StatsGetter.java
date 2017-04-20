package de.mickyjou.plugins.discordbot.utils;

import de.craften.plugins.playerdatastore.api.PlayerDataStore;
import de.mickyjou.plugins.discordbot.DiscordBotPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsGetter {

    private UUID uuid;

    public StatsGetter(Player p) {
        this.uuid = p.getUniqueId();
    }

    public StatsGetter(OfflinePlayer p) {
        this.uuid = p.getUniqueId();
    }

    public StatsGetter(UUID uuid) {
        this.uuid = uuid;
    }

    public PlayerDataStore getPlayerStore() {
        return DiscordBotPlugin.getPDSService().getStore(uuid);
    }

    public int getKills() {
        return getPlayerStore().get("kills") != null ? Integer.valueOf(getPlayerStore().get("kills")) : 0;
    }

    public void setTeam(String team) {
        getPlayerStore().put("team", team);
    }

    public boolean isBanned() {
        return getPlayerStore().get("banned") != null ? Boolean.valueOf(getPlayerStore().get("banned")) : false;
    }

    public void banPlayer() {
        getPlayerStore().put("banned", String.valueOf(true));
    }


    public UUID getTeamMate() {
        return UUID.fromString(getPlayerStore().get("teammate")) != null ? UUID.fromString(getPlayerStore().get("teammate")) : null;
    }


    public boolean hasTeam() {
        if (getPlayerStore().get("team") != null) return true;
        return false;
    }

    public String getTeam() {
        return getPlayerStore().get("team");
    }

    public boolean isAlive() {
        if (getPlayerStore().get("alive") == null) {
            return true;
        }
        return getPlayerStore().get("alive") == "true" ? true : false;
    }


    public void addKill() {
        getPlayerStore().put("kills", String.valueOf(getKills() + 1));
    }

    public void addWarning() {
        getPlayerStore().put("warnings", String.valueOf(getWarnings() + 1));
    }

    public int getWarnings() {
        return getPlayerStore().get("warnings") == null ? 0 : Integer.valueOf(getPlayerStore().get("warnings"));
    }

    public int getSessions() {
        return getPlayerStore().get("sessions") == null ? 0 : Integer.valueOf(getPlayerStore().get("sessions"));
    }

    public void setSessions(int sessions) {
        getPlayerStore().put("sessions", String.valueOf(sessions));
    }
}

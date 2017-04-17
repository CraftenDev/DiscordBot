package de.mickyjou.plugins.discordbot.utils;

import de.mickyjou.plugins.discordbot.commands.discord.Command;

import java.util.HashMap;

public class DiscordCommandHandler {

    public static DiscordCommandParser parser = new DiscordCommandParser();
    public static HashMap<String, Command> commands = new HashMap<>();

    public static void handleCommand(DiscordCommandParser.commandContainer cmd) {
        if (commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }

        }

    }
}

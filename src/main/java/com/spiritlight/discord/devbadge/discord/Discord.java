package com.spiritlight.discord.devbadge.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Discord {
    private final JDA instance;

    public Discord(String token) {
        this.instance = JDABuilder.createDefault(token).build();

        // Register our slash command!
        instance.updateCommands()
                .addCommands(
                        Commands.slash("update", "Uses this command!")
                ).queue(null, Throwable::printStackTrace);

        // Adds a listener so that the command usage is actually handled.
        instance.addEventListener(new CommandListener());
    }

    public JDA getInstance() {
        return instance;
    }
}

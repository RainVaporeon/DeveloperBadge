package com.spiritlight.discord.devbadge;

import com.spiritlight.discord.devbadge.discord.Discord;
import com.spiritlight.discord.devbadge.preferences.Config;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.nio.file.Files;

public class Main {
    private static final File config = new File("config.yaml");

    public static Config configurations;

    public static Discord instance;

    public static void main(String[] args) {
        if(!readConfig()) {
            try {
                newConfig();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("An unexpected error had occurred, the program will now quit.");
            }
        }
        instance = new Discord(configurations.getToken());
    }

    /**
     * Reads from config (Default named config.yaml)
     * @return true if it has been read and set successfully, false if file is not found.
     */
    private static boolean readConfig() {
        try {
            InputStream is = new FileInputStream(config);
            configurations = new Yaml(new Constructor(Config.class)).load(is);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Generates a new config. This will terminate the program.
     * @throws IOException if any IO errors occurred during the operation
     */
    private static void newConfig() throws IOException {
        try (InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yaml")) {
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            Files.write(config.toPath(), buffer);
        }
        System.out.println("A config file has been generated as `config.yaml`.");
        System.exit(0);
    }
}

package me.spirafy.engine.managers;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private File configFile;
    private YamlConfiguration config;

    public ConfigManager(String fileName, Plugin instance){
        configFile = new File(instance.getDataFolder(), fileName);

        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            instance.saveResource(fileName, false);
        }

        config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("Cannot load YAML file, Reason: " + e.toString());
            e.printStackTrace();
        }
    }

    public void reloadConfig(){
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("Cannot load YAML file, Reason: " + e.toString());
            e.printStackTrace();
        }
    }

    public void saveConfig(){
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}

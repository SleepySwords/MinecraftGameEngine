package me.spirafy.engine;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.arenas.Arena;
import me.spirafy.engine.arenas.ArenaManager;
import me.spirafy.engine.managers.ConfigManager;
import me.spirafy.engine.managers.EventManager;
import org.bukkit.plugin.Plugin;

public class Engine {
    private ArenaManager arenaManager;
    private EventManager eventManager;
    private ConfigManager ArenaConfigManager;
    private String name;
    private Plugin instance;

    public EventManager getEventManager() {
        return eventManager;
    }

    public Engine(String name, Plugin instance){
        this.instance = instance;
        this.name = name;
        ArenaConfigManager = new ConfigManager(name + "Arenas.yml", instance);
        //this.em = new EventManager(instance);

        eventManager = new EventManager();
        arenaManager = new ArenaManager(this);
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public ConfigManager getArenaConfigManager() {
        return ArenaConfigManager;
    }

    public String getName() {
        return name;
    }

    public Plugin getInstance() {
        return instance;
    }
}

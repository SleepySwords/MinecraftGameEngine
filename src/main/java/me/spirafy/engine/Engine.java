package me.spirafy.engine;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.arenas.ArenaManager;
import me.spirafy.engine.managers.ConfigManager;
import me.spirafy.engine.managers.EventManager;
import org.bukkit.plugin.Plugin;

public class Engine {

    private GameMethods gm;
    private ArenaManager am;
    private EventManager em;
    private ConfigManager ArenaCm;
    private String name;

    public EventManager getEm() {
        return em;
    }

    public Engine(GameMethods gm, String name, Plugin instance){
        this.name = name;
        ArenaCm = new ConfigManager(name + "Arenas.yml", instance);
        //this.em = new EventManager(instance);

        this.gm = gm;
        am = new ArenaManager(this);
    }

    public ArenaManager getAm() {
        return am;
    }

    public ConfigManager getArenaCm() {
        return ArenaCm;
    }

    public GameMethods getGm() {
        return gm;
    }

    public String getName() {
        return name;
    }
}

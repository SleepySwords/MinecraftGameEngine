package me.spirafy.engine.phase;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.Engine;
import me.spirafy.engine.arenas.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class Phase implements Toggleable, Completable, Listener {

    boolean enabled;
    protected Engine engine;
    Arena arena;
    private long timePerSecond = 20L;

    public <T extends Phase> T setEngine(Engine engine) {
        this.engine = engine;
        return (T) this;
    }

    public <T extends Phase> T setArena(Arena arena) {
        this.arena = arena;
        return (T) this;
    }

    public <T extends Phase> T setTime(long timePerRound){
        this.timePerSecond = timePerRound;
        return (T) this;
    }

    @Override
    public boolean isCompleted() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnable(boolean enabled) {
        this.enabled = enabled;
        if (!enabled){
            executeWhenCompleted();
        }
    }

    public void start(){
        Bukkit.getScheduler().runTaskTimer(engine.getInstance(), this::content, 0L, timePerSecond);
    }

    public abstract void content();

    public abstract void executeOnStart();
}
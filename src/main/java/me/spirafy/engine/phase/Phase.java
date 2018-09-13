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
import org.bukkit.scheduler.BukkitTask;

public abstract class Phase implements Toggleable, Completable, Listener {
    boolean enabled;
    protected Engine engine;
    protected Arena arena;
    private long timePerSecond = 20L;
    protected BukkitTask task;

    public Phase() {
    }

    public abstract String getName();

    public Phase setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Phase setArena(Arena arena) {
        this.arena = arena;
        return this;
    }

    public Phase setTime(long timePerRound) {
        this.timePerSecond = timePerRound;
        return this;
    }

    public boolean isCompleted() {
        return this.enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) {
            this.executeWhenCompleted();
        }

    }

    public void start() {
        this.task = Bukkit.getScheduler().runTaskTimer(this.engine.getInstance(), this::content, 0L, this.timePerSecond);
    }

    public void stop() {
        if (this.task != null) {
            this.task.cancel();
        }
    }

    public abstract void content();

    public abstract void executeOnStart();
}

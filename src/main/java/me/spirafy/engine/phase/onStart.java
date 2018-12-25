package me.spirafy.engine.phase;

/*
 * This code was originally developed by Sword1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.managers.EventType;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.function.Consumer;

public class onStart extends Phase {
    public void executeWhenCompleted() {
    }

    public String getName() {
        return "Starting";
    }

    public void content() {
        if (this.enabled) {
            EventType type = this.engine.getEventManager().listen((Consumer<PlayerJoinEvent>) e -> {
                e.setJoinMessage("s");
            }, this.engine.getInstance());
            type.unregisterAllEvent();
        }

    }

    public void executeOnStart() {
    }

    public onStart() {
        this.setTime(20L);
    }

    public void check() {
        if (this.arena.getPlayers().size() != 0) {
            this.setEnable(false);
            this.arena.registerPhase(this);
        }

    }

    public void hi() {
        this.setTime(20L);
    }
}

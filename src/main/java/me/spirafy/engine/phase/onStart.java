package me.spirafy.engine.phase;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import org.bukkit.event.player.PlayerJoinEvent;

public class onStart extends Phase {

    @Override
    public void executeWhenCompleted() {

    }

    @Override
    public void content() {
        if (enabled){
            engine.getEventManager().listen((PlayerJoinEvent e) -> {
                e.setJoinMessage("hi");
                arena.addPlayer(e.getPlayer());
                check();
            }, engine.getInstance(), "joinEvent");
            engine.getEventManager().removeListener(PlayerJoinEvent.getHandlerList(), "joinEvent");
        }
    }

    @Override
    public void executeOnStart() {

    }

    public onStart() {
        this.setTime(20L);
    }

    @Override
    public void check() {
        if (arena.getPlayers().size() != 0) {
            setEnable(false);
            arena.registerPhase(this, "onStart");
        }
    }
}

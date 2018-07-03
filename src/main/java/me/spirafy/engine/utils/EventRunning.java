package me.spirafy.engine.utils;

/*
 * This code was originally designed and coded by Swords1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import org.bukkit.event.Event;

@FunctionalInterface
public interface EventRunning {
    public abstract void run(Event e);
}

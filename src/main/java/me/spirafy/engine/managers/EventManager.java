package me.spirafy.engine.managers;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

;

public class EventManager implements Listener {

    public List<Map<Object, Object>> maps = new ArrayList<>();
    public Map<String, Listener> listeners = new HashMap<>();

    public <T extends Event> void listen(Consumer<T> type, Plugin main, String name){

        //getting the class from the consumer.
        Class<T> tClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, type.getClass());

        //making the consumer an event
        Consumer<Event> eventConsumer = (Consumer<Event>) type;

        //registering a listener to be user
        //Listener listener = new Listener(this, ((listener1, event) -> eventConsumer.accept(event)), EventPriority.LOW, main, false);

        Bukkit.getPluginManager().registerEvent(tClass, this, EventPriority.NORMAL, ((listener1, event) -> eventConsumer.accept(event)), main);
        Listener listener = new Listener(){

        };
        listeners.put(name, listener);

        Bukkit.getPluginManager().registerEvent(tClass, listener, EventPriority.NORMAL, ((listener1, event) -> eventConsumer.accept(event)), main);
    }

    public void removeListener(HandlerList list, String name) {
        if (listeners.containsKey(name)) {
            list.unregister(listeners.get(name));
            listeners.remove(name);
        }
    }
}
package me.spirafy.engine.managers;

/*
 * This code was originally developed by Sword1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import me.spirafy.game.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.function.Consumer;

;

public class EventManager implements Listener {

    public List<Map<Object, Object>> maps = new ArrayList<>();
    public List<EventType> eventTypes = new ArrayList<>();

    public <T extends Event> EventType listen( Consumer<T> type, Plugin main, EventPriority priority){

        //getting the class from the consumer.
        Class<T> tClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, type.getClass());

        //making the consumer an event
        Consumer<Event> eventConsumer = (Consumer<Event>) type;

        //registering a listener to be user
        //Listener listener = new Listener(this, ((listener1, event) -> eventConsumer.accept(event)), EventPriority.LOW, main, false);

        //Bukkit.getPluginManager().registerEvent(tClass, this, EventPriority.NORMAL, ((listener1, event) -> eventConsumer.accept(event)), main);
        EventType event = new EventType();

        Bukkit.getPluginManager().registerEvent(tClass, event, priority, ((listener1, events) -> {
            eventConsumer.accept(events);
        }), main);

        eventTypes.add(event);
        return event;
    }

    public <T extends Event> EventType listen(Consumer<T> type, Plugin main) {
        return listen(type, main, EventPriority.NORMAL);
    }

    public <T extends Event> EventType listen(Consumer<T> type, EventType eventType, Plugin main){
        eventType.listen(type,  EventPriority.NORMAL, main);
        return eventType;
    }

    public <T extends Event> EventType listen(Consumer<T> type, EventType eventType, Plugin main, EventPriority eventPriority){
        eventType.listen(type,  eventPriority, main);
        return eventType;
    }

    public void unregisterAllEvents() {
        for (EventType eventType : eventTypes) {
            eventType.unregisterAllEvent();
        }
    }
}
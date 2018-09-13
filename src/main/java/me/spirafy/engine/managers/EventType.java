package me.spirafy.engine.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public class EventType implements Listener{

    public Listener getListener() {
        return this;
    }

    public <T extends Event> void unregisterAllEvent(){
        HandlerList.unregisterAll(this);
    }

    public <T extends Event> EventType listen(Consumer<T> type, EventPriority eventPriority, Plugin plugin) {
        //getting the class from the consumer.
        Class<T> tClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, type.getClass());

        //making the consumer an event
        Consumer<Event> eventConsumer = (Consumer<Event>) type;

        //registering a listener to be user
        //Listener listener = new Listener(this, ((listener1, event) -> eventConsumer.accept(event)), EventPriority.LOW, main, false);

        //Bukkit.getPluginManager().registerEvent(tClass, this, EventPriority.NORMAL, ((listener1, event) -> eventConsumer.accept(event)), main);

        Bukkit.getPluginManager().registerEvent(tClass, this, eventPriority, ((listener1, events) -> {
            eventConsumer.accept(events);
        }), plugin);

        return this;
    }
}

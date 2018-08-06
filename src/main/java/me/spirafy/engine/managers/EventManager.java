package me.spirafy.engine.managers;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

;

public class EventManager implements Listener {

    public List<Map<Object, Object>> maps = new ArrayList<>();

    public <T extends Event> void listen(Consumer<T> type, Plugin main){
        Class<T> tClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, type.getClass());

        Consumer<Event> eventConsumer = (Consumer<Event>) type;
        tClass.cast(Event.class).getHandlers().register(new RegisteredListener(this, ((listener, event) -> eventConsumer.accept(event)), EventPriority.LOW, main, false));
    }
}
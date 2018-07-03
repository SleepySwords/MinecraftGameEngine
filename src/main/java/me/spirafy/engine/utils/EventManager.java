package me.spirafy.engine.utils;

/*
 * This code was originally designed and coded by Swords1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.game.main;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements Listener {
    Plugin plugin = main.getPlugin(main.class);
    public List<Map<Object, Object>> maps = new ArrayList<>();
    public Map<String, List<EventRunning>> eventList = new HashMap<>();

    public EventManager(main plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    EventRunning run = e -> System.out.println(((PlayerInteractEvent) e).getPlayer().getDisplayName());

    @EventHandler
    public void events(Event e){
        for(EventRunning run : eventList.get(e.getEventName())){
            run.run(e);
        }
    }
    public void addEvent(String eventName, EventRunning event){
        if(eventList.get(eventName) == null){
            List<EventRunning> em = new ArrayList<>();
            eventList.put(eventName, em);
        }
        eventList.get(eventName).add(event);
    }

    public boolean removeEvent(String event, EventRunning run){
        if(eventList.get(event).contains(run)) {
            eventList.get(event).remove(run);
            return true;
        }else {
            return false;
        }
    }
}

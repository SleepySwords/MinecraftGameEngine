package me.spirafy.engine.arenas;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.spirafy.engine.Engine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public class ArenaManager {

    private IdentityHashMap<String, Arena> arenas = new IdentityHashMap<>();

    public Multimap<Object, Object> getLists() {
        return lists;
    }

    private Engine engine;

    private List<Player> getArenaPlayer() {
        return arenaPlayer;
    }

    private List<Player> arenaPlayer = new ArrayList<>();

    private Multimap<Object, Object> lists = ArrayListMultimap.create();

    public IdentityHashMap<String, Arena> getArenas() {
        return arenas;
    }

    public ArenaManager(Engine e){
        this.engine = e;
    }

    public void addArena(String name, int minPlayers, int maxPlayers, Location spawn){
        Arena a = new Arena(name, minPlayers, maxPlayers, spawn, engine);
        arenas.put(name, a);
        lists.put(name, a);
    }

    public void removeArena(String name){
        arenas.remove(name);
        engine.getArenaConfigManager().getConfig().set("Arenas." + name, null);
    }

    public void addPlayer(Player p, String st){
        lists.put(p, lists.get(st));
        ((Arena)lists.get(st)).addPlayer(p);
    }

    public void storeArena(){
        for (String name : arenas.keySet()){
            Arena a = arenas.get(name);

            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".engine", engine.getName());

            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".minPlayers", a.getMinPLayers());
            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".minPlayers", a.getMaxPlayers());
            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".spawn.x", a.getSpawn().getX());
            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".spawn.y", a.getSpawn().getY());
            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".spawn.z", a.getSpawn().getZ());
            engine.getArenaConfigManager().getConfig().set("Arenas." + a.getName() + ".spawn.world", a.getWorldName());
        }
    }

    public void loadArena(){
        if (engine.getArenaConfigManager().getConfig().getConfigurationSection("Arenas") != null) {
            for (String st : engine.getArenaConfigManager().getConfig().getConfigurationSection("Arenas").getKeys(false)) {
                int minPlayer = engine.getArenaConfigManager().getConfig().getInt("Arenas." + st + ".minPlayers");
                int maxPlayer = engine.getArenaConfigManager().getConfig().getInt("Arenas." + st + ".maxPlayer");
                double spawnX = engine.getArenaConfigManager().getConfig().getDouble("Arenas." + st + ".spawn.x");
                double spawnY = engine.getArenaConfigManager().getConfig().getDouble("Arenas." + st + ".spawn.y");
                double spawnZ = engine.getArenaConfigManager().getConfig().getDouble("Arenas." + st + ".spawn.z");
                String spawnWorld = engine.getArenaConfigManager().getConfig().getString("Arenas." + st + ".spawn.world");
                Location spawn = new Location(Bukkit.getWorld(spawnWorld), spawnX, spawnY, spawnZ);

                Arena a = new Arena(st, minPlayer, maxPlayer, spawn, engine);
                arenas.put(st, a);
            }
        }
    }

    public void clear(){
        engine.getArenaConfigManager().getConfig().set("Arenas", null);
    }
}

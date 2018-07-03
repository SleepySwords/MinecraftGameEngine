package me.spirafy.engine.arenas;

/*
 * This code was originally designed and coded by Swords1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.spirafy.engine.Engine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    List<Arena> arenas = new ArrayList<Arena>();

    public Multimap<Object, Object> getLists() {
        return lists;
    }

    Engine engine;

    public List<Player> getArenaPlayer() {
        return arenaPlayer;
    }

    List<Player> arenaPlayer = new ArrayList<>();

    private Multimap<Object, Object> lists = ArrayListMultimap.create();

    public List<Arena> getArenas() {
        return arenas;
    }

    public ArenaManager(Engine e){
        this.engine = e;
    }

    public void addArena(String name, int minPlayers, int maxPlayers, Location spawn){
        Arena a = new Arena(name, minPlayers, maxPlayers, spawn, engine);
        arenas.add(a);
        lists.put(name, a);
    }

    public void addPlayer(Player p, String st){
        lists.put(p, lists.get(st));
        ((Arena)lists.get(st)).addPlayer(p);

    }
}

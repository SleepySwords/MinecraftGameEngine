package me.spirafy.engine.arenas;

/*
 * This code was originally designed and coded by Swords1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.spirafy.engine.Engine;
import me.spirafy.engine.utils.WorldManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Arena {
    private String name;


    private int minPLayers;
    private int maxPlayers;
    private Location spawn;

    private Multimap<Object, Object> lists = ArrayListMultimap.create();
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> spectators = new ArrayList<Player>();

    GameState state;
    Engine engInstance;
    WorldManager manager;

    public Arena(String name, int minPLayers, int maxPlayers, Location spawn, Engine engInstance){
        this.manager = new WorldManager();
        this.name = name;
        this.minPLayers = minPLayers;
        this.maxPlayers = maxPlayers;
        this.spawn = spawn;
        this.spawn.setWorld(manager.copyFolder(spawn.getWorld(), "tmp_" + name));
        this.spawn = spawn;
        this.engInstance = engInstance;
        engInstance.getGm().preLoad(this);
        state = GameState.LOBBY;
    }

    public void end(){
        engInstance.getGm().onEnd(this);
    }

    public void start(){
        engInstance.getGm().onStart(this);
        state = GameState.STARTING;
    }

    public void update(){
        engInstance.getGm().update(this);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player p){
        players.add(p);
        engInstance.getGm().playerJoin(p, this);
    }

    public void removePlayer(Player p){
        players.remove(p);
        spectators.add(p);
    }

    public void addItem(Object identifier, Object value){
        lists.put(identifier, value);
    }

    public void removeItem(Object identifier){
        lists.removeAll(identifier);
    }

    public Object getItem(Object identifier){
        return lists.get(identifier);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPLayers() {
        return minPLayers;
    }

    public void setMinPLayers(int minPLayers) {
        this.minPLayers = minPLayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getSpectators() {
        return spectators;
    }
}
enum GameState{
    LOBBY, STARTING, MIDGAME, ENDING
}
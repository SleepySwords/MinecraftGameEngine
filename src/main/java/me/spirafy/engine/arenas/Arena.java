package me.spirafy.engine.arenas;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.spirafy.engine.Engine;
import me.spirafy.engine.managers.Team;
import me.spirafy.engine.managers.WorldManager;
import me.spirafy.engine.phase.Phase;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.xml.ws.Holder;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Arena {
    private String name;
    private Engine engine;

    private int minPLayers;
    private int maxPlayers;
    private Location spawn;
    private String worldName;

    private Multimap<Object, Object> lists = ArrayListMultimap.create();
    private ArrayList<Player> onlinePlayer = new ArrayList<Player>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> spectators = new ArrayList<Player>();


    private Map<String, Phase> registeredPhases = new IdentityHashMap<>();
    private Phase currentPhase;

    private GameState state;
    private List<Team> teams = new ArrayList<>();
    private WorldManager manager;

    public Arena(String name, int minPLayers, int maxPlayers, Location spawn, Engine engInstance){
        this.engine = engInstance;
        this.worldName = spawn.getWorld().getName();
        this.manager = new WorldManager();
        this.name = name;
        this.minPLayers = minPLayers;
        this.maxPlayers = maxPlayers;
        this.spawn = spawn;
        this.spawn.setWorld(manager.copyFolder(spawn.getWorld(), "tmp_" + name));
        this.spawn = spawn;
        //todo replace
        state = GameState.LOBBY;
    }

    public ArrayList<Player> getPlayers(){
        return  players;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
        spectators.add(p);
    }

    public void removeSpectator(Player p){
        players.remove(p);
        spectators.remove(p);
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

    public String getWorldName() {
        return worldName;
    }

    public WorldManager getManager() {
        return manager;
    }

    public GameState getState() {
        return state;
    }


    public void registerPhase(Phase phase, String name){
        registeredPhases.put(name, phase);

    }

    public void setPhase(String name){
        currentPhase.setEnable(false);
        currentPhase = registeredPhases.get(name);
        currentPhase.setEngine(engine);
        currentPhase.setArena(this).setEnable(true);
        currentPhase.executeOnStart();
        currentPhase.start();
    }

    public void addTeam(String name, Color color){
        teams.add(new Team(name, color));
    }

    public void removeTeam(String name){
        Stream<Team> streams = teams.stream().filter(team -> team.getTeamName().equals(name));
        Iterator<Team> iterator = streams.iterator();
        while (iterator.hasNext()){
            teams.remove(iterator.next());
        }
    }

    public void addPlayerToTeam(Player player, String name) {
        Stream<Team> streams = teams.stream().filter(team -> team.getTeamName().equals(name));
        Iterator<Team> iterator = streams.iterator();
        iterator.next().addPlayer(player);
    }

    public void removePlayerFromTeams(Player player) {
        Stream<Team> streams = teams.stream().filter(team -> team.getPlayers().contains(player));
        Iterator<Team> iterator = streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().removePlayer(player);
        }
    }
}

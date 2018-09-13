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
import me.spirafy.engine.phase.onStart;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Stream;

public class Arena {
    private String name;
    private Engine engine;
    private int minPLayers;
    private int maxPlayers;
    private Location spawn;
    private String worldName;
    private Multimap<Object, Object> lists = ArrayListMultimap.create();
    private List<Player> onlinePlayer = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Player> spectators = new ArrayList<>();
    private Map<String, Phase> registeredPhases = new IdentityHashMap<>();
    private Map<String, Phase> activePhases = new IdentityHashMap<>();
    private GameState state;
    private List<Team> teams = new ArrayList<>();
    private WorldManager manager;

    public Arena(String name, int minPLayers, int maxPlayers, Location spawn, Engine engInstance) {
        this.engine = engInstance;
        this.worldName = spawn.getWorld().getName();
        this.manager = new WorldManager();
        this.name = name;
        this.minPLayers = minPLayers;
        this.maxPlayers = maxPlayers;
        this.spawn = spawn;
        this.spawn.setWorld(this.manager.copyFolder(spawn.getWorld(), "tmp_" + name));
        this.spawn = spawn;
        this.state = GameState.LOBBY;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public void removePlayer(Player p) {
        this.players.remove(p);
        this.spectators.add(p);
    }

    public void removeSpectator(Player p) {
        this.players.remove(p);
        this.spectators.remove(p);
    }

    public void addItem(Object identifier, Object value) {
        this.lists.put(identifier, value);
    }

    public void removeItem(Object identifier) {
        this.lists.removeAll(identifier);
    }

    public Object getItem(Object identifier) {
        return this.lists.get(identifier);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPLayers() {
        return this.minPLayers;
    }

    public void setMinPLayers(int minPLayers) {
        this.minPLayers = minPLayers;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<Player> getSpectators() {
        return this.spectators;
    }

    public String getWorldName() {
        return this.worldName;
    }

    public WorldManager getManager() {
        return this.manager;
    }

    public GameState getState() {
        return this.state;
    }

    public void registerPhase(Phase phase) {
        String name = phase.getName();
        if (name == null) {
            this.registeredPhases.put("phase" + this.registeredPhases.size(), phase);
        }

        this.registeredPhases.put(name, phase);
    }

    public void startPhase(String name) {
        this.activePhases.put(name, this.registeredPhases.get(name));
        this.activePhases.get(name).setEngine(this.engine);
        this.activePhases.get(name).setArena(this).setEnable(true);
        this.activePhases.get(name).executeOnStart();
        this.activePhases.get(name).start();
    }

    public void stopPhase(String name) {
        if (this.activePhases.get(name) != null) {
            this.activePhases.get(name).stop();
            this.activePhases.remove(name);
        }
    }

    public void unRegisterPhase(String name) {
        if (this.registeredPhases.get(name) != null) {
            this.stopPhase(name);
            this.registeredPhases.remove(name);
        }
    }

    public void addTeam(String name, Color color) {
        this.teams.add(new Team(name, color));
    }

    public void removeTeam(String name) {
        Stream<Team> streams = this.teams.stream().filter((team) -> team.getTeamName().equals(name));
        Iterator<Team> iterator = streams.iterator();

        while(iterator.hasNext()) {
            this.teams.remove(iterator.next());
        }

    }

    public void addPlayerToTeam(Player player, String name) {
        Stream<Team> streams = this.teams.stream().filter((team) -> team.getTeamName().equals(name));
        Iterator<Team> iterator = streams.iterator();
        iterator.next().addPlayer(player);
    }

    public void removePlayerFromTeams(Player player) {
        Stream<Team> streams = this.teams.stream().filter((team) -> team.getPlayers().contains(player));
        Iterator iterator = streams.iterator();

        while(iterator.hasNext()) {
            ((Team)iterator.next()).removePlayer(player);
        }

    }
}

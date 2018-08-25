package me.spirafy.engine.managers;

/*
 * This code was originally developed by Ibby.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private Color color;
    private List<Player> players = new ArrayList<>();

    public Team (String teamName, Color color){
        this.teamName = teamName;
        this.color = color;
    }

    public String getTeamName() {
        return teamName;
    }

    public Color getColor() {
        return color;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

    public List<Player> getPlayers(){
        return players;
    }
}

package me.spirafy.engine.utils;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardUtil {
    private boolean animated = false;
    private List<Player> players = new ArrayList<>();
    private Scoreboard scoreboard;
    private List<Team> teams = new ArrayList<>();
    Objective o;

    public ScoreboardUtil(String name){
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Team team = scoreboard.registerNewTeam(name);
        teams.add(team);

        o = scoreboard.registerNewObjective(name, "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(name);
    }

    public void addEntry(String name, int slot){
        o.getScore(name).setScore(slot);
    }

    public void removeEntry(String name){

    }
}

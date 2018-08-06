package me.spirafy.engine.utils;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreboardUtil {
    private int frame;
    private List<Player> players = new ArrayList<>();
    private Scoreboard scoreboard;
    private List<Team> teams = new ArrayList<>();
    private Objective o;
    private Plugin instance;

    private long speed;

    Map<Integer, Map<Integer, String>> frames = new HashMap<>();

    public ScoreboardUtil(String name, Plugin plugin){
        this.instance = plugin;
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Team team = scoreboard.registerNewTeam(name);
        teams.add(team);

        o = scoreboard.registerNewObjective(name, "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(name);
    }

    public ScoreboardUtil setFrameSlot(String name, int slot, int frame){
        if (!frames.containsKey(frame)){
            frames.put(frame, new HashMap<>());
        }

        frames.get(frame).put(slot, name);
        return this;
    }

    public ScoreboardUtil setSpeed(long speed){
        this.speed = speed;
        return this;
    }

    public void activate(){
        new BukkitRunnable(){
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(instance, 0L, 20L * speed);
    }

    private void update(){
        for (int i = 0; i < frames.get(frame).size(); i++){
            o.getScore(frames.get(frame).get(i)).setScore(i);
        }
        frame++;
        if (frames.size() > frame){
            frame = 0;
        }
    }
}

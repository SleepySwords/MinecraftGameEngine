package me.spirafy.game;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.Engine;
import me.spirafy.engine.arenas.Arena;
import me.spirafy.engine.arenas.GameState;
import me.spirafy.engine.utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class KitPVP implements Arena.GameMethods {

    private Engine eng;
    private Main main;
    private ScoreboardUtil util;

    public KitPVP(Main instance){
        this.main = instance;
        eng = new Engine(this, "KitPVP", instance);
        eng.getAm().loadArena();
        eng.getAm().addArena("Test arena", 2, 10, new Location(Bukkit.getWorld("world"), 0, 0, 0));

        util = new ScoreboardUtil("KitPVP", main);
        util.setFrameSlot("Testing server", 4, 0);
        util.setBlankSpot( 3, 0);
        util.setFrameSlot("Welcome to kitpvp!", 2, 0);
        util.setBlankSpot( 1, 0);
        util.setFrameSlot( "Spirafy", 0, 0);
    }

    public void disable(){
        eng.getAm().clear();
        eng.getAm().storeArena();
    }

    @Override
    public void preLoad(Arena a) {
        eng.getEm().listen((PlayerJoinEvent e) -> {
            if (a.getState() == GameState.LOBBY) {
                util.addPlayer(e.getPlayer());
                a.addPlayer(e.getPlayer());
                a.update(false);
            }
        }, main);

        //Place intitialization methods here
    }

    @Override
    public void onStart(Arena a) {

        for(Player p : a.getPlayers()){
            p.teleport(a.getSpawn());
        }

        new BukkitRunnable(){
            int count = 10;
            @Override
            public void run(){
                if (count <= 0){
                    a.start();
                    cancel();
                }
                for (Player p : a.getPlayers()){
                    p.sendMessage("Starting in " + count);
                }
                count--;
            }
        }.runTaskTimer(main, 0, 20L);
    }

    @Override
    public void midGame(Arena a) {
        eng.getEm().listen((EntityDamageByEntityEvent e) -> {
            if (!(e.getEntity() instanceof Player)){
                return;
            }
            Player p = (Player) e.getEntity();
            if (a.getPlayers().contains(p)){
                e.setCancelled(true);
                a.removePlayer(p);
                a.update(true);
            }
        }, main);
    }

    @Override
    public void onEnd(Arena a) {
        for(Player p : a.getSpectators()){
            p.sendMessage(ChatColor.GREEN + "The player " + a.getPlayers().get(0).getDisplayName() + " has won!");
        }

        for(Player p : a.getPlayers()){
            p.sendMessage(ChatColor.GREEN + "The player " + a.getPlayers().get(0).getDisplayName() + " has won!");
        }
        a.getManager().deleteWorld(Bukkit.getWorld(a.getWorldName()));
    }

    @Override
    public void update(Arena a, boolean started) {
        if(started) {
            if (a.getPlayers().size() == 1) {
                a.end();
            }
        } else {
            if (a.getPlayers().size() >= a.getMinPLayers()){
                a.preStart();
            }
        }
    }
}
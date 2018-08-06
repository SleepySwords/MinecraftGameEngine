package me.spirafy.game;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.Engine;
import me.spirafy.engine.arenas.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements me.spirafy.engine.GameMethods{

    private Engine eng;

    @Override
    public void onEnable(){
        eng = new Engine(this, "Test", this);
        eng.getAm().loadArena();
        eng.getAm().addArena("Test arena", 4, 8, new Location(Bukkit.getWorld("world"), 0, 0, 0));
    }

    @Override
    public void onDisable(){
        eng.getAm().clear();
        eng.getAm().storeArena();
    }

    @Override
    public void preLoad(Arena a) {
        eng.getEm().listen((PlayerJoinEvent e) -> {
            a.addPlayer(e.getPlayer());
            a.update(false);
        }, this);

        //Place intitialization methods here
    }

    @Override
    public void onStart(Arena a) {
        //start things
    }

    @Override
    public void midGame(Arena a) {
        eng.getEm().listen((PlayerInteractEvent e) -> {
            if (a.getPlayers().contains(e.getPlayer())){
                e.setCancelled(true);
                a.removePlayer(e.getPlayer());
                a.update(true);
            }
        }, this);
    }

    @Override
    public void onEnd(Arena a) {
        for(Player p : a.getSpectators()){
            p.sendMessage("The player " + a.getPlayers().get(0).getDisplayName() + "has won!");
        }

        for(Player p : a.getPlayers()){
            p.sendMessage("The player " + a.getPlayers().get(0).getDisplayName() + "has won!");
        }
    }

    @Override
    public void update(Arena a, boolean started) {
        if(started) {
            if (a.getPlayers().size() == 1) {
                a.end();
            }
        } else {
            if (a.getPlayers().size() > a.getMinPLayers()){
                a.preStart();
            }
        }
    }
}
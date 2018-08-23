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
import me.spirafy.engine.phase.onStart;
import me.spirafy.engine.utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class KitPVP {

    private Engine eng;
    private Main main;
    private ScoreboardUtil util;

    public KitPVP(Main instance){
        this.main = instance;
        eng = new Engine("KitPVP", instance);
        eng.getArenaManager().loadArena();
        eng.getArenaManager().addArena("Test arena", 2, 10, new Location(Bukkit.getWorld("world"), 0, 0, 0));

        eng.getArenaManager().getArenas().forEach((s, arena) -> {
            arena.registerPhase(new onStart(), "start");
            arena.setPhase("start");
        });

        util = new ScoreboardUtil("KitPVP", main);
        util.setFrameSlot("Testing server", 4, 0);
        util.setBlankSpot( 3, 0);
        util.setFrameSlot("Welcome to kitpvp!", 2, 0);
        util.setBlankSpot( 1, 0);
        util.setFrameSlot( "Spirafy", 0, 0);
    }

    public void disable(){
        eng.getArenaManager().clear();
        eng.getArenaManager().storeArena();
    }

}
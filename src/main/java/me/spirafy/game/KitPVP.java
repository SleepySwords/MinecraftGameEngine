package me.spirafy.game;

/*
 * Copyright © 2018 by sword1234. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.Engine;
import me.spirafy.engine.phase.onStart;
import me.spirafy.engine.utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;

public class KitPVP {
    private Engine eng;
    private Main main;
    private ScoreboardUtil util;

    public KitPVP(Main instance) {
        this.main = instance;
        this.eng = new Engine("KitPVP", instance);
        this.eng.getArenaManager().loadArena();
        this.eng.getArenaManager().addArena("Test arena", 2, 10, new Location(Bukkit.getWorld("world"), 0.0D, 0.0D, 0.0D));
        this.eng.getArenaManager().getArenas().forEach((s, arena) -> {
            arena.registerPhase((new onStart()).setArena(arena));
            arena.startPhase("Starting");
            arena.addTeam("Blue", Color.BLUE);
            arena.addTeam("red", Color.BLUE);
        });
        this.util = new ScoreboardUtil("KitPVP", this.main);
        this.util.setFrameSlot("Testing server", 4, 0);
        this.util.setBlankSpot(3, 0);
        this.util.setFrameSlot("Welcome to kitpvp!", 2, 0);
        this.util.setBlankSpot(1, 0);
        this.util.setFrameSlot("Spirafy", 0, 0);
    }

    public void disable() {
        this.eng.getArenaManager().clear();
        this.eng.getArenaManager().storeArena();
    }
}

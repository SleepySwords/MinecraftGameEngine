package me.spirafy.game;

/*
 * This code was originally designed and coded by Swords1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.Engine;
import me.spirafy.engine.arenas.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements me.spirafy.engine.GameMethods{

    public Engine eng;

    @Override
    public void onEnable(){
        eng = new Engine(this, this);
    }

    @Override
    public void preLoad(Arena a) {
        //Place intitialization methods here
    }

    @EventHandler
    public void onPlayer(PlayerInteractEvent e){
        if(eng.getAm().getArenaPlayer().contains(e.getPlayer())){
            ((Arena)eng.getAm().getLists().get(e.getPlayer())).removePlayer(e.getPlayer());
            ((Arena) eng.getAm().getLists().get(e.getPlayer())).update();
        }
    }

    @Override
    public void onStart(Arena a) {
        //start things
    }

    @Override
    public void midGame(Arena a) {

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
    public void playerJoin(Player p, Arena a) {
        if(a.getPlayers().size() <= 2){
            a.start();
        }
    }

    @Override
    public void update(Arena a) {
        if(a.getPlayers().size() == 1){
            a.end();
        }
    }
}

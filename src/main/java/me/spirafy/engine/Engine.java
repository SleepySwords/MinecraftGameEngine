package me.spirafy.engine;

/*
 * This code was originally designed and coded by Swords1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

import me.spirafy.engine.arenas.ArenaManager;
import me.spirafy.engine.utils.EventManager;
import me.spirafy.game.main;

public class Engine {

    GameMethods gm;
    ArenaManager am;
    EventManager em;

    public EventManager getEm() {
        return em;
    }

    public Engine(GameMethods gm, main instance){
        //this.em = new EventManager(instance);

        this.gm = gm;
        am = new ArenaManager(this);
    }

    public ArenaManager getAm() {
        return am;
    }

    public GameMethods getGm() {
        return gm;
    }
}

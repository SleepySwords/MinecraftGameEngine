package me.spirafy.engine;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import me.spirafy.engine.arenas.Arena;

public interface GameMethods {
    void preLoad(Arena a);

    void onStart(Arena a);

    void midGame(Arena a);

    void onEnd(Arena a);

    void update(Arena a, boolean started);
}

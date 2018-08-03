package me.spirafy.engine;

/*
 * Copyright © 2018 by Ibrahim Hizamul Ansari. All rights reserved.
 * This code may not be copied, reproduced or distributed without permission from the owner.
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

package me.spirafy.engine.phase;

/*
 * This code was originally developed by Sword1234.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */

public interface Completable {
    boolean isCompleted();

    void executeWhenCompleted();

    void check();
}
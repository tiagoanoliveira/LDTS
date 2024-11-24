package com.t04g05.patterns;

public abstract class GameTemplate {
    public void runGame() throws InterruptedException {
        initializeGame();
        while (!isGameOver()) {
            playLevel();
        }
        finishGame();
    }

    protected abstract void initializeGame();
    protected abstract boolean isGameOver();
    protected abstract void playLevel() throws InterruptedException;
    protected abstract void finishGame();
}

package com.t04g05.patterns;

import java.util.HashSet;
import java.util.Set;

public class GameSubject {
    private final Set<GameObserver> observers = new HashSet<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyGoalReached() {
        for (GameObserver observer : observers) {
            observer.onGoalReached();
        }
    }

    public void notifyGameOver() {
        for (GameObserver observer : observers) {
            observer.onGameOver();
        }
    }
}

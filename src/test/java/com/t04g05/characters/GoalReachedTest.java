package com.t04g05.characters;

import com.t04g05.patterns.GameObserver;
import com.t04g05.patterns.GameSubject;
import org.junit.jupiter.api.Test;

public class GoalReachedTest {

    @Test
    public void testGoalReachedNotification() {
        GameSubject gameSubject = new GameSubject();
        GameObserver observer = new GameObserver() {
            @Override
            public void onGoalReached() {
                System.out.println("Objetivo alcançado!");
            }

            @Override
            public void onGameOver() {
                System.out.println("Jogo finalizado.");
            }
        };
        gameSubject.addObserver(observer);
        gameSubject.notifyGoalReached(); // Notifica que o objetivo foi alcançado
    }
}

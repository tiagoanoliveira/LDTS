package com.t04g05;

import com.t04g05.patterns.GameController;
import com.t04g05.screen.ScreenManager;

public class Main {
    public static void main(String[] args) {
        ScreenManager screenManager = ScreenManager.getInstance();
        try {
            new GameController(screenManager).playLevel();
        } catch (InterruptedException e) {
            System.err.println("Erro na execução do jogo: " + e.getMessage());
        }
    }
}



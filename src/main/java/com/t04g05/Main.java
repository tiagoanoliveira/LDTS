package com.t04g05;

import com.t04g05.controller.GameController;
import com.t04g05.viewer.ScreenManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Boa sorte! Usa as setas ou WASD para controlar o teu movimento.");
        ScreenManager screenManager = ScreenManager.getInstance();
        try {
            new GameController(screenManager).playLevel();
        } catch (InterruptedException e) {
            System.err.println("Erro na execução do jogo: " + e.getMessage());
        }
    }
}



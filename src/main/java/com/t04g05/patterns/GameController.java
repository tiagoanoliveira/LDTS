package com.t04g05.patterns;

import com.t04g05.arena.Arena1;
import com.t04g05.arena.Arena2;
import com.t04g05.screen.ScreenManager;

public class GameController extends GameTemplate {
    private ScreenManager screenManager;

    // Injeção de dependência via construtor
    public GameController(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    @Override
    protected void initializeGame() {
        screenManager = ScreenManager.getInstance();
    }

    @Override
    protected boolean isGameOver() {
        return false; // Exemplo de implementação
    }

    @Override
    public void playLevel() throws InterruptedException {
        Arena1 arena1 = new Arena1();
        boolean level1Completed = arena1.run();

        if (level1Completed) {
            Thread.sleep(500);
            screenManager.stopScreen();
            ScreenManager.getInstance(true);  // Reinicia o ScreenManager
            Arena2 arena2 = new Arena2();
            arena2.run();
        } else {
            System.out.println("O jogo terminou.");
        }
    }

    @Override
    protected void finishGame() {
        // Finalização do jogo
    }
}

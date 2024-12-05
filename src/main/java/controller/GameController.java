package controller;

import com.t04g05.patterns.GameTemplate;
import model.game.arena.Arena1;
import model.game.arena.Arena2;
import viewer.ScreenManager;

public class GameController extends GameTemplate {
    private ScreenManager screenManager;

    public GameController(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    @Override
    protected void initializeGame() {
        screenManager = ScreenManager.getInstance();
    }

    @Override
    protected boolean isGameOver() {
        return false;
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
    //Depois vamos melhorar o GameController ao implementar mais funcionalidades
}

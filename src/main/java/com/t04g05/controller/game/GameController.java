package com.t04g05.controller.game;

import com.t04g05.controller.menu.MenuController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena1;
import com.t04g05.model.game.arena.Arena2;
import com.t04g05.states.Level1State;
import com.t04g05.states.Level2State;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;
import com.t04g05.viewer.game.ArenaViewer;

public class GameController {
    private GameState currentState;
    private final MenuController menuController;
    private final GUI gui;

    public GameController(MenuController menuController, GUI gui) {
        this.menuController = menuController;
        this.gui = gui;
        this.currentState = new Level1State();
    }

    public void startGame() {
        while (currentState instanceof MenuState) {
            currentState.step(gui);
            GameState nextState = currentState.getNextState();
            if (nextState != null) {
                currentState = nextState;
            }
        }
        currentState.initializeLevel();
        Arena arena = (currentState instanceof Level1State) ? new Arena1() : new Arena2();
        ArenaViewer arenaViewer = new ArenaViewer(arena);
        arenaViewer.draw(gui);
        gui.refresh();
    }

    public void updateGameState() {
        if (currentState instanceof Level1State || currentState instanceof Level2State) {
            if (isLevelComplete()) {
                if (currentState instanceof Level1State) {
                    currentState = new Level2State();
                } else {
                    stopGame(); // Finaliza o jogo
                    return;
                }
                currentState.initializeLevel();
            }
        }
    }

    private boolean isLevelComplete() {
        return currentState.getArena().isGoalReached(); // Usa getArena de GameState
    }

    public void stopGame() {
        // Lógica de finalização do jogo por implementar
        System.out.println("Jogo Finalizado!");
        gui.close();
    }
}

package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.states.GameState;


public class GameController {
    private GameState currentState;

    public GameController(GameState initialState) {
        this.currentState = initialState;
    }

    public void process(GUI gui) {
        while (currentState != null) {
            currentState.step(gui);
            currentState = currentState.getNextState();
        }
    }
}

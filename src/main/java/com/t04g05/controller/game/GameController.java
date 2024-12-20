package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.states.GameState;

import java.io.IOException;


public class GameController {
    private GameState currentState;

    public GameController(GameState initialState) {
        this.currentState = initialState;
    }
    public void process(GUI gui) throws IOException {
        while (currentState != null) {
            GUI.ACTION action = gui.getNextAction();
            currentState.step(gui, action); // Passa a ação para o estado atual
            currentState = currentState.getNextState(); // Obtém o próximo estado
        }
    }
}

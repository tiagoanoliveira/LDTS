package com.t04g05.states;

import com.t04g05.controller.menu.GameOverController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Character;
import com.t04g05.viewer.menu.GameOverViewer;


import java.io.IOException;

public class GameOverState extends GameState {
    private final GameOverController gameoverController;
    private  final GameOverViewer gameoverViewer;

    public GameOverState(Character character, GameOverController gameoverController){
        this.gameoverViewer = new GameOverViewer(character);
        this.gameoverController = gameoverController;
    }
    @Override
    public void step(GUI gui) {
        try{
            gui.clear();
            gameoverViewer.draw(gui);
            gui.refresh();
            GUI.ACTION action = gui.getNextAction();
            GameState nextState = gameoverController.processInput(action);
            if (nextState != null) {
                setNextState(nextState);
            } else {
                System.out.println("Nenhum próximo estado definido.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar a GUI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Arena getArena() {
        return null;
    }
}

package com.t04g05.states;

import com.t04g05.controller.menu.WinController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.menu.Win;
import com.t04g05.viewer.menu.WinViewer;

import java.io.IOException;

public class WinState extends GameState {
    private final WinController winController;
    private final WinViewer winViewer;

    public WinState(Win win, WinController winController) {
        this.winController = winController;
        this.winViewer = new WinViewer(win);
    }

    @Override
    public void step(GUI gui) {
        try {
            gui.clear();
            winViewer.draw(gui);
            gui.refresh();

            GUI.ACTION action = gui.getNextAction();
            GameState nextState = winController.processInput(action);
            if (nextState != null) {
                setNextState(nextState);
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
package com.t04g05.states;

import com.t04g05.controller.menu.InstructionsController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.viewer.menu.InstructionsViewer;

import java.io.IOException;

public class InstructionsState extends GameState {
    private final InstructionsController instructionsController;
    private  final InstructionsViewer instructionsViewer;

    public InstructionsState(InstructionsController instructionsController, GUI gui){
        this.instructionsViewer = new InstructionsViewer(instructionsController.getInstructions());
        this.instructionsController = instructionsController;
    }
    @Override
    public void step(GUI gui, GUI.ACTION action) {
        try{
            gui.clear();
            instructionsViewer.draw(gui);
            gui.refresh();
            GameState nextState = instructionsController.processInput(action);
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
        // InstructionsState não usa arenas, então retornamos null
        return null;
    }
}

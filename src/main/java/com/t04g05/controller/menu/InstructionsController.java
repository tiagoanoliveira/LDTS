package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Instructions;
import com.t04g05.states.GameState;
import com.t04g05.states.InstructionsState;
import com.t04g05.states.MenuState;

public class InstructionsController {
    private Instructions instructions;
    private final MenuController menuController;
    private final GUI gui;

    public InstructionsController(MenuController menuController, GUI gui){
        this.instructions = null;
        this.menuController = menuController;
        this.gui = gui;
    }
    public Instructions getInstructions() {
        if(instructions == null) instructions = new Instructions();
        return instructions;
    }
    public GameState processInput(GUI.ACTION action){
        switch (action){
            case ENTER:
                String selectedOption = instructions.getSelectedOption();
                if(instructions.isBackSelected()){
                    return new MenuState(menuController, gui);
                }
            default:
                break;
        }
        return null;
    }
}

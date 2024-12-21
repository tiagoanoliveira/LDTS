package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.*;

public class MenuController {
    private final Menu menu;
    private final InstructionsController instructionsController;
    private final GUI gui;

    public MenuController(Menu menu, InstructionsController instructionsController, GUI gui) {
        this.menu = menu;
        this.instructionsController = instructionsController;
        this.gui = gui;
    }

    public Menu getMenu() {
        return menu;
    }

    public GameState processInput(GUI.ACTION action) {
        System.out.println("Ação recebida MenuController: " + action);
        switch (action) {
            case UP:
                menu.previousOption();
                break;
            case DOWN:
                menu.nextOption();
                break;
            case ENTER:
                String selectedOption = menu.getSelectedOption();
                System.out.println("Opção selecionada: " + selectedOption);
                if (menu.isStartGameSelected()) {
                    return new Level1State(); // Transição para o nível 1
                } else if(menu.isInstructionsSelected()) {
                    return new InstructionsState(instructionsController, gui);
                }
                else if (menu.isExitSelected()) {
                    return null;
                }
                break;
            case ESC, QUIT:
                return null;
            case NONE: // Sem ação
                break;
            default:
                break;
        }
        return new MenuState(this, null);
    }
}

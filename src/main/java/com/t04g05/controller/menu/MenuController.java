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
        switch (action) {
            case UP:
                menu.previousOption();
                break;
            case DOWN:
                menu.nextOption();
                break;
            case ENTER:
                String selectedOption = menu.getSelectedOption();
                if (menu.isStartGameSelected()) {
                    System.out.println("Boa sorte. Tens que chegar à Porta de Ouro sem ser morto pelos inimigos!");
                    System.out.println("Tens 3 vidas. Podes controlar as vidas restantes no topo do jogo.");
                    return new Level1State(); // Transição para o nível 1; Inicio do jogo
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

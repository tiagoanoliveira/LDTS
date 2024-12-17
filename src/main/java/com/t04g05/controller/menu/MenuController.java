package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.GameState;
import com.t04g05.states.Level1State;
import com.t04g05.states.MenuState;

public class MenuController {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public GameState processInput(GUI.ACTION action) {
        System.out.println("Ação recebida: " + action);

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
                } else if (menu.isExitSelected()) {
                    System.out.println("Saindo do jogo.");
                    return null; // Sair do jogo
                }
                break;
            case ESC:
                System.out.println("ESC pressionado. Saindo do menu.");
                return null; // Sair do jogo
            case NONE: // Sem ação
                System.out.println("Nenhuma tecla pressionada.");
                break;
            default:
                System.out.println("Ação desconhecida ou não suportada.");
                break;
        }

        return new MenuState(this, null);
    }
}

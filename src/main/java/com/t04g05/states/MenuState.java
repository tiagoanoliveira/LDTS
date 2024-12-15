package com.t04g05.states;

import com.t04g05.controller.menu.MenuController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.viewer.menu.MenuViewer;

public class MenuState extends GameState {
    private final MenuController menuController;
    private final MenuViewer menuViewer;

    public MenuState(MenuController menuController, GUI gui) {
        this.menuViewer = new MenuViewer(menuController.getMenu());
        this.menuController = menuController;
    }

    @Override
    public void step(GUI gui) {
        try{
            gui.clear(); // Limpa a tela
            menuViewer.draw(gui); // Desenha o menu
            gui.refresh(); // Atualiza a tela

            // Processa a entrada do usuário
            GUI.ACTION action = gui.getNextAction();
            GameState nextState = menuController.processInput(action);
            if (nextState != null) {
                setNextState(nextState);
            } else {
                System.out.println("Nenhum próximo estado definido.");
            }
    } catch (Exception e) {
        System.err.println("Erro ao atualizar a GUI: " + e.getMessage());
        e.printStackTrace();
    }
    }

    @Override
    public void run(GUI gui) {
        // Desenha o menu inicial antes do loop principal
        try {
            gui.clear();
            menuViewer.draw(gui);
            gui.refresh();
        } catch (Exception e) {
            System.err.println("Erro ao exibir o menu inicial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initializeLevel() {
        // Sem inicialização específica para o menu
    }

    @Override
    public Arena getArena() {
        // MenuState não usa arenas, então retornamos null
        return null;
    }
}

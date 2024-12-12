package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena2;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;

public class Level2State extends GameState {
    private final ArenaController arenaController;

    public Level2State() {
        this.arenaController = new ArenaController(new Arena2());
    }

    @Override
    public void step(GUI gui) {
        try {
            gui.clear();  // Pode lançar IOException
        } catch (IOException e) {
            System.err.println("Erro ao limpar a tela: " + e.getMessage());
            e.printStackTrace();
        }
        ArenaViewer viewer = new ArenaViewer(arenaController.getArena());
        viewer.draw(gui);
        gui.refresh();

        arenaController.processInput(gui.getNextAction());
        arenaController.update();
    }

    @Override
    public void run(GUI gui) {
        // Lógica do estado de execução, geralmente um loop de jogo
        while (!arenaController.isGameOver()) {
            step(gui); // Chama o step a cada ciclo
            try {
                Thread.sleep(100); // Para evitar um loop rápido demais
            } catch (InterruptedException e) {
                System.out.println("Jogo interrompido: " + e.getMessage());
            }
        }
    }

    @Override
    public void initializeLevel() {
        // Inicialização específica do nível 2 - falta implementar
        System.out.println("Inicializando o Nível 2...");
    }

    @Override
    public Arena getArena() {
        return arenaController.getArena(); // Retorna a arena do controlador
    }

}
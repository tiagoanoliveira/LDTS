package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena1;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;

public class Level1State extends GameState {
    private final ArenaController arenaController;
    private final ArenaViewer arenaViewer;

    public Level1State() {
        // Criação da arena específica do nível 1
        var arena = new Arena1();

        // Inicializa o controlador e o visualizador da arena
        this.arenaController = new ArenaController(arena);
        this.arenaViewer = new ArenaViewer(arena);
    }

    @Override
    public void step(GUI gui) {
        try {
            gui.clear();
            arenaViewer.draw(gui);
            gui.refresh();

            GUI.ACTION action = gui.getNextAction();
            if (action != null) {
                arenaController.processInput(action);
            }

            arenaController.update();

            if (arenaController.isGameOver()) {
                System.out.println("Game Over");
                setNextState(null); // Sair do jogo
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O durante o processamento do Level1State: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run(GUI gui) {
        while (!arenaController.isGameOver()) {
            step(gui);
            try {
                Thread.sleep(100); // Pequeno atraso entre ciclos
            } catch (InterruptedException e) {
                System.out.println("Jogo interrompido: " + e.getMessage());
            }
        }
        System.out.println("Nível 1 concluído ou jogo terminado.");
    }

    @Override
    public void initializeLevel() {
        // Inicialização específica do nível 1 - falta implementar
        System.out.println("Inicializando o Nível 1...");
    }

    @Override
    public Arena getArena() {
        return arenaController.getArena(); // Retorna a arena do controlador
    }

}

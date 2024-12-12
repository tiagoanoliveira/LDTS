package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena1;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;

public class Level1State extends GameState {
    private final ArenaController arenaController;

    public Level1State() {
        this.arenaController = new ArenaController(new Arena1());
    }

    @Override
    public void step(GUI gui) {
        try {
            gui.clear(); // Limpa a tela

            // Desenha a arena
            ArenaViewer viewer = new ArenaViewer(arenaController.getArena());
            viewer.draw(gui);
            gui.refresh(); // Atualiza a tela

            // Processa entrada do usuário
            GUI.ACTION action = gui.getNextAction();
            arenaController.processInput(action);

            // Atualiza lógica da arena
            arenaController.update();

            // Verifica se o jogo acabou
            if (arenaController.isGameOver()) {
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

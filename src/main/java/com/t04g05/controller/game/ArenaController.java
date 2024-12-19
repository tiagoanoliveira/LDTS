package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;

public class ArenaController {
    private final Arena arena;

    public ArenaController(Arena arena) {
        this.arena = arena;
    }
    public Arena getArena() {
        return arena;
    }
    public boolean isGameOver() {
        // Verifica se o jogador alcançou o objetivo
        if (arena.getCharacter().getPosition().equals(arena.getGoalPosition())) {
            System.out.println("Objetivo alcançado!");
            return true; // Vitória
        }
        System.out.println("isGameOver chamado. Resultado: false" );
        // Aqui podes adicionar outras condições, como vidas do jogador
        return false; // Continua o jogo
    }


    public void processInput(GUI.ACTION action) {
        System.out.println("Processando ação: " + action);
        Position newPosition = getNewPosition(action);
        // Atualiza a posição do personagem se o movimento for válido
        if (canMoveTo(newPosition)) {
            arena.getCharacter().setPosition(newPosition);
        }
    }

    private Position getNewPosition(GUI.ACTION action) {
        Position currentPos = arena.getCharacter().getPosition();
        return switch (action) {
            case UP -> currentPos.move(0, -1);
            case DOWN -> currentPos.move(0, 1);
            case LEFT -> currentPos.move(-1, 0);
            case RIGHT -> currentPos.move(1, 0);
            default -> currentPos;
        };
    }

    private boolean canMoveTo(Position position) {
        // Verifica se a nova posição está dentro dos limites da arena e não colide com obstáculos
        if (position.getX() < 0 || position.getX() >= arena.getWidth() ||
                position.getY() < 0 || position.getY() >= arena.getHeight()) {
            return false;
        }

        return arena.getElements().stream().noneMatch(e -> e.getPosition().equals(position));
    }

    public void update() {
        System.out.println("Atualizando arena...");
        arena.updateEnemies();
        arena.checkCollisions();
        if (isGameOver()) {
            System.out.println("Fim do jogo identificado em update().");
        }
    }
}


package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Enemy;

public class ArenaController {
    private final Arena arena;

    public ArenaController(Arena arena) {
        this.arena = arena;
    }
    public Arena getArena() {
        return arena;
    }
    public boolean isGoalReached() {
        // Verifica se o jogador alcançou o objetivo
        if (arena.getCharacter().getPosition().equals(arena.getGoalPosition())) {
            System.out.println("Objetivo alcançado!");
            return true; // Vitória
        }
        return false; // Continua o jogo
    }
    public boolean isGameOver() {return true;}
    public void processInput(GUI.ACTION action) {
        Position newPosition = getNewPosition(action);
        // Atualiza a posição do personagem se o movimento for válido
        if (canMoveTo(newPosition)) {
            arena.getCharacter().setPosition(newPosition);
            arena.checkCoinCollection();
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
        arena.updateEnemies();
        arena.checkCollisions();
        arena.checkCoinCollection();
        arena.updateCharacter(arena.getCharacter().getPosition());
    }
}


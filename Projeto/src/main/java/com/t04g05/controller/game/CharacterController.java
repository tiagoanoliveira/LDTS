package com.t04g05.controller.game;

import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.gui.GUI;

public class CharacterController {
    private final Arena arena;

    public CharacterController(Arena arena) {
        this.arena = arena;
    }

    public void processInput(GUI.ACTION action) {
        Position newPosition = getNewPosition(action);
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
        // Verifica se a nova posição está dentro dos limites e não colide com obstáculos
        if (position.getX() < 0 || position.getX() >= arena.getWidth() ||
                position.getY() < 0 || position.getY() >= arena.getHeight()) {
            return false;
        }

        return arena.getElements().stream().noneMatch(e -> e.getPosition().equals(position));
    }

    public void updateCharacter() {
        // Verifica e atualiza a posição do personagem
        Position newPosition = arena.getCharacter().getPosition();
        arena.updateCharacter(newPosition);
    }

    public void checkCollisions() {
        if (arena.getCharacter().getLives() <= 0) {
            System.out.println("\n===================================");
            System.out.println("              GAME OVER!              ");
            System.out.println("===================================");
            System.out.println("   Foste comido por um inimigo!");
            System.out.println("===================================\n");
        }
    }

    public boolean checkGoal() {
        if (arena.getGoalPositions().contains(arena.getCharacter().getPosition())) {
            System.out.println("Boa! É assim mesmo");
            return true;
        }
        return false;
    }

}

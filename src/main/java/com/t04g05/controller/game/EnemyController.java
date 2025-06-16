package com.t04g05.controller.game;

import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Enemy;

import java.util.Random;

public class EnemyController {
    private final Arena arena;
    private final Random random;

    public EnemyController(Arena arena) {
        this.arena = arena;
        this.random = new Random();
    }

    public void updateEnemies() {
        for (Enemy enemy : arena.getEnemies()) {
            Position newPosition = getRandomValidPosition(enemy.getPosition());
            if (newPosition != null) {
                enemy.setPosition(newPosition);
            }
        }
    }

    Position getRandomValidPosition(Position currentPosition) {
        Position[] possibleMoves = new Position[]{
                currentPosition.move(0, -1), // UP
                currentPosition.move(0, 1),  // DOWN
                currentPosition.move(-1, 0), // LEFT
                currentPosition.move(1, 0)   // RIGHT
        };

        // Tenta encontrar uma posição válida
        for (int i = 0; i < possibleMoves.length; i++) {
            int index = random.nextInt(possibleMoves.length);
            Position potentialMove = possibleMoves[index];
            if (arena.canMoveTo(potentialMove)) {
                return potentialMove;
            }
        }
        // Nenhum movimento válido
        return null;
    }
    public Random getRandom(){return this.random;}
}

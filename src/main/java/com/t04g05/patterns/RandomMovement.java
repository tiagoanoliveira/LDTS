package com.t04g05.patterns;

import com.t04g05.model.game.elementos.Character;
import com.t04g05.model.game.elementos.Enemy;
import com.t04g05.model.game.elementos.Obstacle;
import com.t04g05.model.Position;
import com.t04g05.model.game.elementos.Walls;
import java.util.Random;
import java.util.Set;
import com.googlecode.lanterna.input.KeyStroke;

public class RandomMovement implements MovementStrategy {
    private final Random random = new Random();

    @Override
    public void move(Character character, KeyStroke keyStroke, Set<Walls> walls, Set<Obstacle> obstacles) {
        // Implementação de movimento para o Character
        int newX = character.getPosition().getX();
        int newY = character.getPosition().getY();

        switch (keyStroke.getKeyType()) {
            case ArrowUp -> newY = Math.max(1, newY - 1);
            case ArrowDown -> newY = Math.min(29, newY + 1);
            case ArrowLeft -> newX = Math.max(1, newX - 1);
            case ArrowRight -> newX = Math.min(59, newX + 1);
        }

        Position newPosition = new Position(newX, newY);

        // Verifica se a nova posição está bloqueada por paredes ou obstáculos
        if (!walls.contains(new Walls(newX, newY)) && !obstacles.contains(new Obstacle(newX, newY))) {
            character.moveTo(newPosition); // Mover a personagem
        }
    }

    @Override
    public void move(Enemy enemy, Set<Walls> walls, Set<Obstacle> obstacles) {
        // Implementação de movimento aleatório para o Enemy
        int newX = enemy.getPosition().getX();
        int newY = enemy.getPosition().getY();

        // Movimentos aleatórios: 0 = up, 1 = down, 2 = left, 3 = right
        int direction = random.nextInt(4); // Gera um número aleatório de 0 a 3
        switch (direction) {
            case 0 -> newY = Math.max(1, newY - 1); // Mover para cima
            case 1 -> newY = Math.min(29, newY + 1); // Mover para baixo
            case 2 -> newX = Math.max(1, newX - 1); // Mover para a esquerda
            case 3 -> newX = Math.min(59, newX + 1); // Mover para a direita
        }

        Position newPosition = new Position(newX, newY);

        // Verifica se o novo movimento não está bloqueado por uma parede ou obstáculo
        if (!walls.contains(new Walls(newX, newY)) && (obstacles == null || !obstacles.contains(new Obstacle(newX, newY)))) {
            enemy.moveTo(newPosition); // Move o inimigo para a nova posição
        }
    }
}

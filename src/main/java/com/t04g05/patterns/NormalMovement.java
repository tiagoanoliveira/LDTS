package com.t04g05.patterns;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t04g05.characters.Character;
import com.t04g05.characters.Enemy;
import com.t04g05.elements.Position;
import com.t04g05.elements.Walls;
import com.t04g05.elements.Obstacle;
import java.util.Set;

public class NormalMovement implements MovementStrategy {
    @Override
    public void move(Character character, KeyStroke key, Set<Walls> walls, Set<Obstacle> obstacles) {
        int newX = character.getPosition().getX();
        int newY = character.getPosition().getY();

        switch (key.getKeyType()) {
            case ArrowUp -> newY = Math.max(0, newY - 1);
            case ArrowDown -> newY = Math.min(30, newY + 1);
            case ArrowLeft -> newX = Math.max(0, newX - 1);
            case ArrowRight -> newX = Math.min(59, newX + 1);
        }

        if (key.getKeyType() == KeyType.Character) {
            char characters = key.getCharacter();
            switch (characters) {
                case 'W', 'w' -> newY = Math.max(0, newY - 1);
                case 'S', 's' -> newY = Math.min(30, newY + 1);
                case 'A', 'a' -> newX = Math.max(0, newX - 1);
                case 'D', 'd' -> newX = Math.min(59, newX + 1);
            }
        }

        Position newPosition = new Position(newX, newY);

        // Verifica se a nova posição está bloqueada por uma parede
        boolean isWall = walls.stream().anyMatch(wall -> wall.getPosition().equals(newPosition));
        // Verifica se a nova posição está bloqueada por um obstáculo
        boolean isObstacle = obstacles != null && obstacles.stream().anyMatch(obstacle -> obstacle.getPosition().equals(newPosition));

        // Se não houver obstáculos nem paredes, a personagem pode se mover
        if (!isWall && !isObstacle) {
            character.moveTo(newPosition); // Mover a personagem
        } else if (isWall) {
            System.out.println("Movimento bloqueado por parede.");
        } else if (isObstacle) {
            System.out.println("Movimento bloqueado por obstáculo.");
        }
    }
    @Override
    public void move(Enemy enemy, Set<Walls> walls, Set<Obstacle> obstacles) {
    }

}


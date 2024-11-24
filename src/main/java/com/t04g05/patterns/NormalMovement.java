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
            case ArrowUp -> newY = Math.max(1, newY - 1);
            case ArrowDown -> newY = Math.min(29, newY + 1);
            case ArrowLeft -> newX = Math.max(1, newX - 1);
            case ArrowRight -> newX = Math.min(59, newX + 1);
        }

        if (key.getKeyType() == KeyType.Character) {
            char characters = key.getCharacter();
            switch (characters) {
                case 'W', 'w' -> newY = Math.max(1, newY - 1);
                case 'S', 's' -> newY = Math.min(29, newY + 1);
                case 'A', 'a' -> newX = Math.max(1, newX - 1);
                case 'D', 'd' -> newX = Math.min(59, newX + 1);
            }
        }

        Position newPosition = new Position(newX, newY);

        // Verificar se a nova posição está bloqueada por paredes
        if (!walls.contains(new Walls(newX, newY))) {
            // Verificar obstáculos somente se o conjunto de obstáculos não for null
            if (obstacles != null && !obstacles.contains(new Obstacle(newX, newY))) {
                character.moveTo(newPosition); // Mover a personagem
            }
            else if (obstacles != null && !obstacles.contains(new Obstacle(newX, newY))) {
                System.out.println("Movimento bloqueado por obstáculo.");
            }
            else if (obstacles == null) {
                character.moveTo(newPosition); // Mover a personagem se não houver obstáculos
            }
        } else if (walls.contains(new Walls(newX, newY))) {
            System.out.println("Movimento bloqueado por parede.");
        }
    }
    @Override
    public void move(Enemy enemy, Set<Walls> walls, Set<Obstacle> obstacles) {
        // Esse metodo não será utilizado em NormalMovement
    }

}


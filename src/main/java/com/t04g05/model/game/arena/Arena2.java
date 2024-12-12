package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Obstacle;
import com.t04g05.model.game.elements.Walls;

import java.util.*;

public class Arena2 extends Arena {
    private final Set<Walls> walls;
    private final Set<Obstacle> obstacles;

    public Arena2() {
        super(60, 31, new Character(new Position(54,3)), createEnemies());
        this.walls = new HashSet<>();
        this.obstacles = new HashSet<>();
        initializeElements();
        this.goalPosition = new Position(45, 13);
    }

    @Override
    public void initializeElements() {
        //Adicionar paredes internas para o labirinto
        for (int i = 6; i < 16; i++) {
            walls.add(new Walls(new Position(11, i))); // 1.ª linha vertical à esquerda
        }
        for (int i = 10; i < 26; i++) {
            walls.add(new Walls(new Position(50, i))); // ultima linha vertical à direita
        }
        for (int i = 11; i < 60; i++) {
            walls.add(new Walls(new Position(i, 5))); // 1.ª linha horizontal em cima
        }
        for (int i = 20; i < 50; i++) {
            walls.add(new Walls(new Position(i, 10))); // 2º linha horizontal
        }
        for (int i = 11; i < 50; i++) {
            walls.add(new Walls(new Position(i, 15))); // 3º linha horizontal
        }
        for (int i = 1; i < 42; i++) {
            walls.add(new Walls(new Position(i, 20))); // 4º linha horizontal
        }
        for (int i = 10; i < 50; i++) {
            walls.add(new Walls(new Position(i, 25))); // ultima linha horizontal
        }

        // Adicione obstáculos
        obstacles.add(new Obstacle(new Position(25, 12)));
        obstacles.add(new Obstacle(new Position(25, 13)));
        obstacles.add(new Obstacle(new Position(40, 18)));
        obstacles.add(new Obstacle(new Position(40, 17)));
        obstacles.add(new Obstacle(new Position(50, 7)));
        obstacles.add(new Obstacle(new Position(50, 6)));
        obstacles.add(new Obstacle(new Position(35, 22)));
        obstacles.add(new Obstacle(new Position(35, 21)));

        synchronizeWalls(walls);
    }

    // Adicione inimigos
    private static List<Enemy> createEnemies () {
        return Arrays.asList(
            new Enemy(new Position(9, 10)),
            new Enemy(new Position(42, 21)),
            new Enemy(new Position(30, 4)),
            new Enemy(new Position(30, 19)),
            new Enemy(new Position(15, 27)),
            new Enemy(new Position(52, 24)),
            new Enemy(new Position(35, 12)),
            new Enemy(new Position(40, 6))
        );
    }

    @Override
    public boolean isGoalReached() {
        // Verifica se a posição do personagem é a mesma que a do goal
        return character.getPosition().equals(goalPosition);
    }
}
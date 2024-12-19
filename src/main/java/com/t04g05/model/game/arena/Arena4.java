package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;

import java.util.*;

public class Arena4 extends Arena {
    private final Set<Walls> walls;

    public Arena4() {
        super(100, 50, new Character(new Position(10,10)), createEnemies());
        this.walls = new HashSet<>();
        initializeElements();
        this.goalPosition = new Position(90, 45);
    }

    @Override
    public void initializeElements() {
        for (int i = 5; i < 45; i++) {
            walls.add(new Walls(new Position(25, i))); // Vertical line left
        }
        for (int i = 5; i < 95; i++) {
            walls.add(new Walls(new Position(i, 5))); // Top boundary
        }
        for (int i = 5; i < 95; i++) {
            walls.add(new Walls(new Position(i, 45))); // Bottom boundary
        }
        for (int i = 25; i < 75; i++) {
            walls.add(new Walls(new Position(i, 25))); // Middle horizontal line
        }
        for (int i = 10; i < 40; i++) {
            if (i % 3 == 0) walls.add(new Walls(new Position(50, i))); // Spaced vertical line in the middle
        }
        for (int i = 30; i < 60; i++) {
            walls.add(new Walls(new Position(i, 15))); // Diagonal line
        }
        for (int i = 5; i < 95; i += 2) {
            walls.add(new Walls(new Position(i, 35))); // Additional horizontal line bottom
        }
        for (int i = 10; i < 45; i++) {
            walls.add(new Walls(new Position(70, i))); // Vertical line right
        }
        for (int i = 30; i < 70; i += 3) {
            walls.add(new Walls(new Position(i, 20))); // Spaced horizontal line top
        }
        synchronizeWalls(walls);
    }

    private static List<Enemy> createEnemies() {
        return Arrays.asList(
                new Enemy(new Position(20, 20)),
                new Enemy(new Position(80, 40)),
                new Enemy(new Position(50, 10)),
                new Enemy(new Position(40, 30)),
                new Enemy(new Position(60, 15)),
                new Enemy(new Position(85, 25)),
                new Enemy(new Position(45, 12)),
                new Enemy(new Position(55, 28))
        );
    }

    @Override
    public boolean isGoalReached() {
        return character.getPosition().equals(goalPosition);
    }
}


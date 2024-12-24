package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.viewer.game.EnemyViewer;

import java.util.Random;
import java.util.Set;

public class EnemyController {
    private Set<Enemy> enemies;
    private Set<Walls> walls;
    private EnemyViewer enemyView;
    private Random random;
    private final GUI gui;

    public EnemyController(Set<Enemy> enemies, Set<Walls> walls, GUI gui) {
        this.enemies = enemies;
        this.walls = walls;
        this.enemyView = new EnemyViewer();
        this.random = new Random();
        this.gui=gui;
    }
    public void moveEnemies() {
        for (Enemy enemy : enemies) {
            Position newPosition = getNewPosition(enemy);
            if (isMoveValid(newPosition.getX(), newPosition.getY())) {
                enemy.setPosition(newPosition);
            }
            enemyView.draw(gui, enemy);
        }
    }

    private Position getNewPosition(Enemy enemy) {
        int newX = enemy.getPosition().getX();
        int newY = enemy.getPosition().getY();
        switch (random.nextInt(4)) {
            case 0 -> newY -= 1;
            case 1 -> newY += 1;
            case 2 -> newX -= 1;
            case 3 -> newX += 1;
        }
        return new Position(newX, newY);
    }


    private boolean isMoveValid(int newX, int newY) {
        // Verifica se o novo movimento do inimigo colide com uma parede ou um obstáculo
        for (Walls wall : walls) {
            if (wall.getPosition().getX() == newX && wall.getPosition().getY() == newY) {
                return false; // Colidiu com parede
            }
        }
        return true; // Movimento válido
    }
}
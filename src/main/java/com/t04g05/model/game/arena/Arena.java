package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.model.game.elements.GameElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Arena {
    private final int width;
    private final int height;
    private final Set<Walls> walls;
    protected Character character;
    private final List<Enemy> enemies;
    protected Position goalPosition;

    public Arena(int width, int height, Character character, List<Enemy> enemies) {
        this.width = width;
        this.height = height;
        this.walls = new HashSet<>();
        this.character = character;
        this.enemies = (enemies != null) ? enemies : new ArrayList<>();
        createOuterWalls();
    }

    private void createOuterWalls() {
        for (int x = 0; x < width; x++) {
            walls.add(new Walls(new Position(x, 0)));
            walls.add(new Walls(new Position(x, height - 1)));
        }
        for (int y = 1; y < height - 1; y++) {
            walls.add(new Walls(new Position(0, y)));
            walls.add(new Walls(new Position(width - 1, y)));
        }
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Character getCharacter() {
        return character;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Set<Walls> getWalls() {
        return walls;
    }

    public boolean isGameOver() {
        for (Enemy enemy : enemies) {
            if (character.getPosition().equals(enemy.getPosition())) {
                return true; // Jogo termina se houver colisão com inimigo.
            }
        }
        return false;
    }

    public abstract boolean isGoalReached();

    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            //Lógica para atualizar a posição do inimigo (por exemplo, movimento simples)
            Position newPosition = enemy.getPosition().move(1, 0); // Exemplo de movimento
            if (canMoveTo(newPosition)) {
                enemy.setPosition(newPosition);
            }
        }
    }

    public void checkCollisions() {
        //Verifica colisões entre o personagem e os inimigos
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(character.getPosition())) {
                //Lógica para indicar Game Over ou outra ação
                System.out.println("Game Over! O personagem colidiu com um inimigo.");
            }
        }
    }

    public boolean canMoveTo(Position position) {
        // Verifica se a posição está dentro dos limites e não colide com paredes ou inimigos
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }
        if (walls.stream().anyMatch(wall -> wall.getPosition().equals(position))) {
            return false;
        }
        return enemies.stream().noneMatch(enemy -> enemy.getPosition().equals(position));
    }

    public List<GameElement> getElements() {
        List<GameElement> elements = new ArrayList<>();
        elements.addAll(walls);
        elements.addAll(enemies);
        elements.add(character);
        return elements;
    }

    public abstract void initializeElements();

    public void synchronizeWalls(Set<Walls> additionalWalls) {
        this.walls.addAll(additionalWalls);
    }
    public Position getGoalPosition() {
        return goalPosition;
    }

}

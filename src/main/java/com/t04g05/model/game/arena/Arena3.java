// Arena3.java
package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;

import java.util.*;

public class Arena3 extends Arena {
    private final Set<Walls> walls;
    private ArrayList<Coin> coins;

    public Arena3() {
        super(80, 40, new Character(new Position(5,5)), createEnemies());
        this.walls = new HashSet<>();
        this.coins = new ArrayList<>();
        initializeElements();
        this.goalPosition = new Position(75, 35);
    }

    @Override
    public void initializeElements() {
        for (int i = 10; i < 30; i++) {
            walls.add(new Walls(new Position(15, i))); // Vertical line left
        }
        for (int i = 20; i < 70; i++) {
            walls.add(new Walls(new Position(i, 10))); // Horizontal line top
        }
        for (int i = 20; i < 70; i += 5) {
            walls.add(new Walls(new Position(i, 30))); // Spaced horizontal line bottom
        }
        for (int i = 10; i < 35; i++) {
            walls.add(new Walls(new Position(40, i))); // Central vertical line
        }
        for (int i = 5; i < 75; i += 2) {
            walls.add(new Walls(new Position(i, 20))); // Additional horizontal line
        }
        for (int i = 5; i < 40; i++) {
            walls.add(new Walls(new Position(60, i))); // Vertical line right
        }
        for (int i = 10; i < 70; i += 3) {
            walls.add(new Walls(new Position(i, 25))); // Spaced horizontal line middle
        }
        synchronizeWalls(walls);
        placeCoins();
    }

    //metodo para colocar moedas
    private void placeCoins() {
        int numberOfCoins = 10;
        while (coins.size() < numberOfCoins) {
            int x = (int) (Math.random() * 60);
            int y = (int) (Math.random() * 31);

            Position coinPosition = new Position(x, y);
            if (!isWall(coinPosition) && !isCoin(coinPosition)) {
                coins.add(new Coin(coinPosition));
            }
        }
    }

    private boolean isWall(Position position) {
        for (Walls wall : getWalls()) {
            if (wall.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCoin(Position position) {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private static List<Enemy> createEnemies() {
        return Arrays.asList(
                new Enemy(new Position(10, 15)),
                new Enemy(new Position(50, 25)),
                new Enemy(new Position(20, 5)),
                new Enemy(new Position(60, 35)),
                new Enemy(new Position(30, 20)),
                new Enemy(new Position(70, 10)),
                new Enemy(new Position(35, 18)),
                new Enemy(new Position(45, 22))
        );
    }

    @Override
    public boolean isGoalReached() {
        return character.getPosition().equals(goalPosition);
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}




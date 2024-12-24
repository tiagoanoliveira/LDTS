package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;

import java.util.*;

public class Arena3 extends Arena {
    private final Set<Walls> walls;
    private final ArrayList<Coin> coins;

    public Arena3(Character character) {
        super(90, 49, character, createEnemies());
        this.walls = new HashSet<>();
        this.coins = new ArrayList<>();
        initializeElements();
        this.doorPosition = new Position(79, 12);
        setGoalPositions(79, 85, 12, 17);
    }

    @Override
    public void initializeElements() {
        for (int x = 14; x <= 15; x++) { // Colunas 14 e 15
            for (int y = 11; y < 26; y++) { // Linhas de 11 a 25
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda em cima
            }
        }
        for (int x = 14; x <= 15; x++) { // Colunas 14 e 15
            for (int y = 34; y < 42; y++) { // Linhas de 34 a 41
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda em baixo
            }
        }
        for (int x = 29; x <= 30; x++) { // Colunas 29 e 30
            for (int y = 11; y < 42; y++) { // Linhas de 11 a 41
                getWalls().add(new Walls(new Position(x, y)));  // 2 linha vertical à esquerda
            }
        }
        for (int x = 44; x <= 45; x++) { // Colunas 44 e 45
            for (int y = 3; y < 11; y++) { // Linhas de 3 a 10
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio em cima
            }
        }
        for (int x = 44; x <= 45; x++) { // Colunas 44 e 45
            for (int y = 19; y < 26; y++) { // Linhas de 19 a 25
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio
            }
        }
        for (int x = 44; x <= 45; x++) { // Colunas 44 e 45
            for (int y = 34; y < 49; y++) { // Linhas de 34 a 48
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio em baixo
            }
        }
        for (int x = 59; x <= 60; x++) { // Colunas 59 e 60
            for (int y = 3; y < 19; y++) { // Linhas de 3 a 18
                getWalls().add(new Walls(new Position(x, y)));  // 4 linha vertical em cima
            }
        }
        for (int x = 59; x <= 60; x++) { // Colunas 59 e 60
            for (int y = 34; y < 42; y++) { // Linhas de 34 a 41
                getWalls().add(new Walls(new Position(x, y)));  // 4 linha vertical em baixo
            }
        }
        for (int x = 74; x <= 75; x++) { // Colunas 74 e 75
            for (int y = 19; y < 26; y++) { // Linhas de 19 a 25
                getWalls().add(new Walls(new Position(x, y)));  // Ultima linha vertical em cima
            }
        }
        for (int x = 74; x <= 75; x++) { // Colunas 74 e 75
            for (int y = 34; y < 42; y++) { // Linhas de 34 a 41
                getWalls().add(new Walls(new Position(x, y)));  // Ultima linha vertical em baixo
            }
        }
        for (int i = 60; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 11))); // 1.ª linha horizontal em cima à direita
        }
        for (int i = 30; i < 46; i++) {
            getWalls().add(new Walls(new Position(i, 18))); // 2º linha horizontal no meio
        }
        for (int i = 74; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 18))); // 2º linha horizontal à direita
        }
        for (int i = 0; i < 16; i++) {
            getWalls().add(new Walls(new Position(i, 26))); // 3º linha horizontal à esquerda
        }
        for (int i = 44; i < 76; i++) {
            getWalls().add(new Walls(new Position(i, 26))); // 3º linha horizontal à direita
        }
        for (int i = 44; i < 60; i++) {
            getWalls().add(new Walls(new Position(i, 34))); // 4º linha horizontal
        }
        for (int i = 15; i < 30; i++) {
            getWalls().add(new Walls(new Position(i, 41))); // Última linha horizontal à esquerda
        }
        for (int i = 60; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 41))); // Última linha horizontal à direita
        }
        synchronizeWalls(walls);
        placeCoins();
    }

    private void placeCoins() {
        int numberOfCoins = 20;
        while (coins.size() < numberOfCoins) {
            int x = (int) (Math.random() * (getWidth()-2))+1;
            int y = (int) (Math.random() * (getHeight()-4))+4;

            Position coinPosition = new Position(x, y);
            if (!isWall(coinPosition) && !isCoin(coinPosition)) {
                coins.add(new Coin(coinPosition));
            }
        }
    }

    protected boolean isWall(Position position) {
        for (Walls wall : getWalls()) {
            if (wall.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isCoin(Position position) {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private static List<Enemy> createEnemies() {
        return Arrays.asList(
                new Enemy(new Position(8, 15)),
                new Enemy(new Position(55, 22)),
                new Enemy(new Position(82, 30)),
                new Enemy(new Position(70, 39)),
                new Enemy(new Position(62, 44)),
                new Enemy(new Position(43, 28)),
                new Enemy(new Position(25, 45)),
                new Enemy(new Position(20, 38)),
                new Enemy(new Position(38, 20)),
                new Enemy(new Position(6, 28)),
                new Enemy(new Position(18, 9)),
                new Enemy(new Position(40, 37)),
                new Enemy(new Position(47, 5)),
                new Enemy(new Position(68, 13)),
                new Enemy(new Position(62, 5)),
                new Enemy(new Position(80, 8))
        );
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}




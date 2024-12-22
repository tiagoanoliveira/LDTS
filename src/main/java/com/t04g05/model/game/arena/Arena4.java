package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;

import java.util.*;

public class Arena4 extends Arena {
    private final Set<Walls> walls;
    private ArrayList<Coin> coins;

    public Arena4() {
        super(90, 49, new Character(new Position(4,7)), createEnemies());
        this.walls = new HashSet<>();
        this.coins = new ArrayList<>();
        initializeElements();
        this.doorPosition = new Position(81, 5);
        setGoalPositions(81, 87, 5, 10); // Define o intervalo de posições do objetivo
    }

    @Override
    public void initializeElements() {
        //Adicionar paredes internas para o labirinto
        for (int x = 14; x <= 15; x++) { // Colunas 14 e 15
            for (int y = 11; y < 19; y++) { // Linhas de 11 a 19
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda em cima
            }
        }
        for (int x = 14; x <= 15; x++) { // Colunas 14 e 15
            for (int y = 34; y < 42; y++) { // Linhas de 34 a 41
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda em baixo
            }
        }
        for (int x = 29; x <= 30; x++) { // Colunas 29 e 30
            for (int y = 3; y < 11; y++) { // Linhas de 3 a 10
                getWalls().add(new Walls(new Position(x, y)));  // 2 linha vertical em cima
            }
        }
        for (int x = 29; x <= 30; x++) { // Colunas 29 e 30
            for (int y = 26; y < 42; y++) { // Linhas de 26 a 41
                getWalls().add(new Walls(new Position(x, y)));  // 2 linha vertical em baixo
            }
        }
        for (int x = 45; x <= 46; x++) { // Colunas 45 e 46
            for (int y = 11; y < 26; y++) { // Linhas de 11 a 26
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio em cima
            }
        }
        for (int x = 45; x <= 46; x++) { // Colunas 45 e 46
            for (int y = 34; y < 49; y++) { // Linhas de 34 a 48
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio em baixo
            }
        }
        for (int x = 59; x <= 60; x++) { // Colunas 59 e 60
            for (int y = 3; y < 19; y++) { // Linhas de 3 a 18
                getWalls().add(new Walls(new Position(x, y)));  // 4 linha vertical em cima
            }
        }
        for (int x = 74; x <= 75; x++) { // Colunas 74 e 75
            for (int y = 11; y < 42; y++) { // Linhas de 11 a 41
                getWalls().add(new Walls(new Position(x, y)));  // Ultima linha vertical
            }
        }
        for (int i = 15; i < 45; i++) {
            getWalls().add(new Walls(new Position(i, 18))); // 1.ª linha horizontal em cima
        }
        for (int i = 0; i < 30; i++) {
            getWalls().add(new Walls(new Position(i, 26))); // 2º linha horizontal à esquerda
        }
        for (int i = 45; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 26))); // 2º linha horizontal à direita
        }
        for (int i = 0; i < 15; i++) {
            getWalls().add(new Walls(new Position(i, 34))); // 3º linha horizontal à esquerda
        }
        for (int i = 45; i < 60; i++) {
            getWalls().add(new Walls(new Position(i, 34))); // 3º linha horizontal no meio
        }
        for (int i = 75; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 34))); // 3º linha horizontal à direita
        }
        for (int i = 60; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 41))); // Última linha horizontal à direita
        }
        synchronizeWalls(walls);
        placeCoins();
    }

    private void placeCoins() {
        int numberOfCoins = 15;
        while (coins.size() < numberOfCoins) {
            int x = (int) (Math.random() * (getWidth()-2))+1;
            int y = (int) (Math.random() * (getHeight()-4))+4;
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

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
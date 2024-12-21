package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;

import java.util.*;

public class Arena2 extends Arena {
    private final Set<Walls> walls;
    private ArrayList<Coin> coins;

    public Arena2() {
        super(90, 46, new Character(new Position(4,2)), createEnemies());
        this.walls = new HashSet<>();
        this.coins = new ArrayList<>();
        initializeElements();
        this.doorPosition = new Position(81, 2);
        this.goalPosition = new Position(81, 2);
    }

    @Override
    public void initializeElements() {
        //Adicionar paredes internas para o labirinto
        for (int x = 14; x <= 15; x++) { // Colunas 14 e 15
            for (int y = 31; y < 39; y++) { // Linhas de 31 a 38
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda em baixo
            }
        }
        for (int x = 29; x <= 30; x++) { // Colunas 29 e 30
            for (int y = 23; y < 31; y++) { // Linhas de 23 a 31
                getWalls().add(new Walls(new Position(x, y)));  // 2 linha vertical à esquerda em baixo
            }
        }
        for (int x = 45; x <= 46; x++) { // Colunas 45 e 46
            for (int y = 0; y < 16; y++) { // Linhas de 0 a 17
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio em cima
            }
        }
        for (int x = 45; x <= 46; x++) { // Colunas 45 e 46
            for (int y = 31; y < 39; y++) { // Linhas de 31 a 39
                getWalls().add(new Walls(new Position(x, y)));  // Linha vertical no meio em baixo
            }
        }
        for (int x = 74; x <= 75; x++) { // Colunas 74 e 75
            for (int y = 15; y < 31; y++) { // Linhas de 0 a 17
                getWalls().add(new Walls(new Position(x, y)));  // Ultima linha vertical
            }
        }
        for (int i = 0; i < 32; i++) {
            getWalls().add(new Walls(new Position(i, 8))); // 1.ª linha horizontal em cima à esquerda
        }
        for (int i = 60; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 8))); // 1.ª linha horizontal em cima à direita
        }
        for (int i = 15; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 15))); // 2º linha horizontal
        }
        for (int i = 0; i < 61; i++) {
            getWalls().add(new Walls(new Position(i, 22))); // 3º linha horizontal
        }
        for (int i = 45; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 30))); // 4º linha horizontal
        }
        for (int i = 15; i < 45; i++) {
            getWalls().add(new Walls(new Position(i, 38))); // Última linha horizontal à esquerda
        }
        for (int i = 61; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 38))); // Última linha horizontal à direita
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


    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
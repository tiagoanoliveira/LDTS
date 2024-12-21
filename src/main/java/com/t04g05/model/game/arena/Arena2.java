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
        super(90, 46, new Character(new Position(82,2)), createEnemies());
        this.walls = new HashSet<>();
        this.coins = new ArrayList<>();
        initializeElements();
        this.goalPosition = new Position(45, 13);
    }

    @Override
    public void initializeElements() {
        //Adicionar paredes internas para o labirinto
        for (int x = 16; x <= 17; x++) { // Colunas 16 e 17
            for (int y = 8; y < 24; y++) { // Linhas de 8 a 23
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda
            }
        }
        for (int x = 72; x <= 73; x++) { // Colunas 74 e 75
            for (int y = 15; y < 39; y++) { // Linhas de 15 a 38
                getWalls().add(new Walls(new Position(x, y)));  //Última linha vertical à direita
            }
        }
        for (int i = 17; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 8))); // 1.ª linha horizontal em cima
        }
        for (int i = 32; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 15))); // 2º linha horizontal
        }
        for (int i = 17; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 23))); // 3º linha horizontal
        }
        for (int i = 1; i < 58; i++) {
            getWalls().add(new Walls(new Position(i, 30))); // 4º linha horizontal
        }
        for (int i = 16; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 38))); // Última linha horizontal
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

    @Override
    public boolean isGoalReached() {
        // Verifica se a posição do personagem é a mesma que a do goal
        return character.getPosition().equals(goalPosition);
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
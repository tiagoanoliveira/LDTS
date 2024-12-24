package com.t04g05.model.game.arena;

import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.model.Position;

import java.util.ArrayList;

public class Arena1 extends Arena {
    private ArrayList<Coin> coins;

    public Arena1() {
        super(90, 46, new Character(new Position(54, 3)), new ArrayList<>());
        this.coins = new ArrayList<>();
        initializeElements();
        this.goalPosition = new Position(68, 17);
    }

    @Override
    public void initializeElements() {
        //Adicionar paredes internas para o labirinto
        for (int x = 17; x <= 18; x++) { // Colunas 17 e 18
            for (int y = 8; y < 24; y++) { // Linhas de 8 a 24
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda
            }
        }
        for (int x = 74; x <= 75; x++) { // Colunas 74 e 75
            for (int y = 15; y < 39; y++) { // Linhas de 15 a 39
                getWalls().add(new Walls(new Position(x, y)));  //Última linha vertical à direita
            }
        }
        for (int i = 17; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 8))); // 1.ª linha horizontal em cima
        }
        for (int i = 33; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 15))); // 2º linha horizontal
        }
        for (int i = 17; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 23))); // 3º linha horizontal
        }
        for (int i = 1; i < 60; i++) {
            getWalls().add(new Walls(new Position(i, 30))); // 4º linha horizontal
        }
        for (int i = 15; i < 75; i++) {
            getWalls().add(new Walls(new Position(i, 38))); // Última linha horizontal
        }
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

    @Override
    public boolean isGoalReached() {
        // Verifica se a posição do personagem é a mesma que a do goal
        return character.getPosition().equals(goalPosition);
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
package com.t04g05.model.game.arena;

import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.model.Position;

import java.util.ArrayList;

public class Arena1 extends Arena {
    private final ArrayList<Coin> coins;

    public Arena1() {
        super(90, 49, new Character(new Position(82, 7)), new ArrayList<>());
        this.coins = new ArrayList<>();
        initializeElements();
        this.doorPosition = new Position(64, 20);
        setGoalPositions(64, 70, 20, 25);
    }

    @Override
    public void initializeElements() {
        for (int x = 16; x <= 17; x++) { // Colunas 16 e 17
            for (int y = 11; y < 27; y++) { // Linhas de 8 a 23
                getWalls().add(new Walls(new Position(x, y)));  // 1 linha vertical à esquerda
            }
        }
        for (int x = 72; x <= 73; x++) { // Colunas 74 e 75
            for (int y = 18; y < 42; y++) { // Linhas de 15 a 38
                getWalls().add(new Walls(new Position(x, y)));  //Última linha vertical à direita
            }
        }
        for (int i = 17; i < 90; i++) {
            getWalls().add(new Walls(new Position(i, 11))); // 1.ª linha horizontal em cima
        }
        for (int i = 32; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 18))); // 2º linha horizontal
        }
        for (int i = 17; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 26))); // 3º linha horizontal
        }
        for (int i = 1; i < 58; i++) {
            getWalls().add(new Walls(new Position(i, 33))); // 4º linha horizontal
        }
        for (int i = 16; i < 74; i++) {
            getWalls().add(new Walls(new Position(i, 41))); // Última linha horizontal
        }
        placeCoins();
    }

    private void placeCoins() {
        int numberOfCoins = 15;
        while (coins.size() < numberOfCoins) {
            int x = (int) (Math.random() * (getWidth()-2))+1;
            int y = (int) (Math.random() * (getHeight()-4))+4;

            Position coinPosition = new Position(x, y);
            if (!isWall(coinPosition) && getCoins().stream().noneMatch(coin -> coin.getPosition().equals(coinPosition))) {
                getCoins().add(new Coin(coinPosition));
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

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
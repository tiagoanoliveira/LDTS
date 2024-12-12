package com.t04g05.model.game.arena;

import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.model.Position;

import java.util.ArrayList;

public class Arena1 extends Arena {
    public Arena1() {
        super(60, 31, new Character(new Position(54,3)), new ArrayList<>());
        initializeElements();
        this.goalPosition = new Position(45, 13);
    }

    @Override
    public void initializeElements() {
        //Adicionar paredes internas para o labirinto
        for (int i = 6; i < 16; i++) {
            getWalls().add(new Walls(new Position( 11, i))); // 1.ª linha vertical à esquerda
        }
        for (int i = 10; i < 26; i++) {
            getWalls().add(new Walls(new Position( 50, i))); // Última linha vertical à direita
        }
        for (int i = 11; i < 60; i++) {
            getWalls().add(new Walls(new Position( i, 5))); // 1.ª linha horizontal em cima
        }
        for (int i = 20; i < 50; i++) {
            getWalls().add(new Walls(new Position( i, 10))); // 2º linha horizontal
        }
        for (int i = 11; i < 50; i++) {
            getWalls().add(new Walls(new Position( i, 15))); // 3º linha horizontal
        }
        for (int i = 1; i < 42; i++) {
            getWalls().add(new Walls(new Position( i, 20))); // 4º linha horizontal
        }
        for (int i = 10; i < 50; i++) {
            getWalls().add(new Walls(new Position( i, 25))); // Última linha horizontal
        }
    }
    @Override
    public boolean isGoalReached() {
        // Verifica se a posição do personagem é a mesma que a do goal
        return character.getPosition().equals(goalPosition);
    }
}
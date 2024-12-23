package com.t04g05.model.game.elements;

import com.t04g05.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void testEnemyCreation() {
        // Cria um inimigo na posição (6, 3)
        Position position = new Position(6, 3);
        Enemy enemy = new Enemy(position);

        // Verifica se a posição do inimigo está correta
        assertEquals(position, enemy.getPosition(), "A posição do inimigo deveria ser (6, 3).");
    }

    @Test
    void testSetPosition() {
        // Cria um inimigo e altera sua posição
        Enemy enemy = new Enemy(new Position(2, 2));
        Position newPosition = new Position(8, 5);

        enemy.setPosition(newPosition);

        // Verifica se a posição foi alterada corretamente
        assertEquals(newPosition, enemy.getPosition(), "A posição do inimigo deveria ser (8, 5).");
    }

    @Test
    void testGetXAndY() {
        // Cria um inimigo e verifica as coordenadas X e Y
        Enemy enemy = new Enemy(new Position(7, 4));

        assertEquals(7, enemy.getPosition().getX(), "A coordenada X do inimigo deveria ser 7.");
        assertEquals(4, enemy.getPosition().getY(), "A coordenada Y do inimigo deveria ser 4.");
    }
}

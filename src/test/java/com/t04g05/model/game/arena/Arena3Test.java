package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Arena3Test {

    private Arena3 arena;
    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character(new Position(10, 10));
        arena = new Arena3(character);
    }

    @Test
    void testInitializeElements() {
        assertNotNull(arena.getCharacter(), "O personagem não deveria ser nulo.");
        assertFalse(arena.getWalls().isEmpty(), "Deveria haver paredes na arena.");
        assertEquals(20, arena.getCoins().size(), "Deveriam haver 20 moedas na arena.");
    }

    @Test
    void testPlaceCoins() {
        for (Coin coin : arena.getCoins()) {
            assertFalse(arena.isWall(coin.getPosition()), "A moeda não pode ser colocada em uma parede.");
            assertTrue(coin.getPosition().getX() > 0 && coin.getPosition().getX() < arena.getWidth(), "A moeda deve estar dentro dos limites da largura.");
            assertTrue(coin.getPosition().getY() >= 4 && coin.getPosition().getY() < arena.getHeight(), "A moeda deve estar dentro dos limites da altura.");
        }
    }

    @Test
    void testWallsPlacement() {
        Set<Walls> walls = arena.getWalls();
        for (Walls wall : walls) {
            Position position = wall.getPosition();
            assertNotNull(position, "A posição de uma parede não pode ser nula.");
            assertTrue(position.getX() >= 0 && position.getX() < arena.getWidth(), "As paredes devem estar dentro dos limites da largura.");
            assertTrue(position.getY() >= 0 && position.getY() < arena.getHeight(), "As paredes devem estar dentro dos limites da altura.");
        }
    }

    @Test
    void testIsWall() {
        Position wallPosition = new Position(14, 12); // Posição de uma parede conhecida
        assertTrue(arena.isWall(wallPosition), "Deveria ser uma parede.");

        Position nonWallPosition = new Position(5, 5); // Posição sem parede
        assertFalse(arena.isWall(nonWallPosition), "Não deveria ser uma parede.");
    }

    @Test
    void testIsCoin() {
        Coin coin = arena.getCoins().get(0); // Obtém uma moeda existente
        assertTrue(arena.isCoin(coin.getPosition()), "Deveria ser uma moeda.");

        Position nonCoinPosition = new Position(1, 1); // Posição sem moeda
        assertFalse(arena.isCoin(nonCoinPosition), "Não deveria ser uma moeda.");
    }

    @Test
    void testEnemiesPlacement() {
        for (Enemy enemy : arena.getEnemies()) {
            Position position = enemy.getPosition();
            assertNotNull(position, "A posição de um inimigo não pode ser nula.");
            assertFalse(arena.isWall(position), "O inimigo não pode estar em uma parede.");
            assertFalse(arena.isCoin(position), "O inimigo não pode estar na posição de uma moeda.");
        }
    }

    @Test
    void testGoalPosition() {
        Position doorPosition = arena.getDoorPosition();
        assertNotNull(doorPosition, "A posição da porta não deveria ser nula.");
        assertTrue(doorPosition.getX() >= 79 && doorPosition.getX() <= 85, "A posição da porta está fora do intervalo esperado de X.");
        assertTrue(doorPosition.getY() >= 12 && doorPosition.getY() <= 17, "A posição da porta está fora do intervalo esperado de Y.");
    }

    @Test
    void testCharacterPosition() {
        Position characterPosition = arena.getCharacter().getPosition();
        assertNotNull(characterPosition, "A posição do personagem não pode ser nula.");
        assertFalse(arena.isWall(characterPosition), "O personagem não pode começar em uma parede.");
        assertFalse(arena.isCoin(characterPosition), "O personagem não pode começar em uma posição de moeda.");
    }
}

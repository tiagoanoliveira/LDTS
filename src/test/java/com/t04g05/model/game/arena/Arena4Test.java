package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Walls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Arena4Test {
    private Arena4 arena;
    private Character character;

    @BeforeEach
    public void setUp() {
        character = new Character(new Position(5, 5));
        arena = new Arena4(character);
    }

    @Test
    public void testInitialization() {
        assertNotNull(arena);
        assertEquals(90, arena.getWidth());
        assertEquals(49, arena.getHeight());
        assertNotNull(arena.getWalls());
        assertNotNull(arena.getCoins());
        assertEquals(character, arena.getCharacter());
    }

    @Test
    public void testWallsPlacement() {
        Set<Walls> walls = arena.getWalls();
        assertFalse(walls.isEmpty());
        for (Walls wall : walls) {
            Position pos = wall.getPosition();
            assertTrue(pos.getX() >= 0 && pos.getX() < arena.getWidth());
            assertTrue(pos.getY() >= 0 && pos.getY() < arena.getHeight());
        }
    }

    @Test
    public void testCoinsPlacement() {
        ArrayList<Coin> coins = arena.getCoins();
        assertEquals(20, coins.size());
        for (Coin coin : coins) {
            Position pos = coin.getPosition();
            assertTrue(pos.getX() >= 1 && pos.getX() < arena.getWidth() - 1);
            assertTrue(pos.getY() >= 4 && pos.getY() < arena.getHeight() - 1);
            assertFalse(arena.isWall(pos));
        }
    }

    @Test
    public void testIsWall() {
        Set<Walls> walls = arena.getWalls();
        for (Walls wall : walls) {
            assertTrue(arena.isWall(wall.getPosition()));
        }
        assertFalse(arena.isWall(new Position(0, 0)));
    }

    @Test
    public void testIsCoin() {
        ArrayList<Coin> coins = arena.getCoins();
        for (Coin coin : coins) {
            assertTrue(arena.isCoin(coin.getPosition()));
        }
        assertFalse(arena.isCoin(new Position(0, 0)));
    }

    @Test
    public void testEnemiesPlacement() {
        ArrayList<Enemy> enemies = (ArrayList<Enemy>) arena.getEnemies();
        assertEquals(12, enemies.size());
        for (Enemy enemy : enemies) {
            Position pos = enemy.getPosition();
            assertTrue(pos.getX() >= 0 && pos.getX() < arena.getWidth());
            assertTrue(pos.getY() >= 0 && pos.getY() < arena.getHeight());
        }
    }

    @Test
    public void testGoalPosition() {
        Position doorPosition = arena.getDoorPosition();
        assertEquals(new Position(79, 28), doorPosition);
        Position goalStart = new Position(79, 28);
        Position goalEnd = new Position(85, 33);
        assertTrue(doorPosition.getX() >= goalStart.getX() && doorPosition.getX() <= goalEnd.getX());
        assertTrue(doorPosition.getY() >= goalStart.getY() && doorPosition.getY() <= goalEnd.getY());
    }

    @Test
    public void testCharacterInitialPosition() {
        Position charPos = character.getPosition();
        assertTrue(charPos.getX() >= 0 && charPos.getX() < arena.getWidth());
        assertTrue(charPos.getY() >= 0 && charPos.getY() < arena.getHeight());
        assertFalse(arena.isWall(charPos));
    }
}

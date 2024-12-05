package com.t04g05.characters;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t04g05.model.Position;
import com.t04g05.model.game.elementos.Walls;
import com.t04g05.patterns.NormalMovement;
import com.t04g05.model.game.elementos.Character;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MovementBlockedbyWallsTest {

    @Test
    public void testMoveBlockedByWall() {
        Character character = new Character(5, 5);
        Walls wall = new Walls(5, 4);
        Set<Walls> walls = Set.of(wall); // conjunto de paredes

        NormalMovement movementStrategy = new NormalMovement();

        Position originalPosition = character.getPosition();
        KeyStroke simulatedKeyStroke = new KeyStroke(KeyType.ArrowUp);

        movementStrategy.move(character, simulatedKeyStroke, walls, null);

        // Assert que a posição não foi alterada
        assertEquals(originalPosition, character.getPosition());
    }
}

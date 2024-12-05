package com.t04g05.characters;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Position;
import model.game.elementos.Walls;
import com.t04g05.patterns.NormalMovement;
import model.game.elementos.Character;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MovementBlockedbyWallsTest {

    @Test
    public void testMoveBlockedByWall() {
        model.game.elementos.Character character = new Character(5, 5);
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

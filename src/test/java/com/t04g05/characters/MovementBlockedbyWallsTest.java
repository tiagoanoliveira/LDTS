package com.t04g05.characters;


import com.t04g05.elements.Position;
import com.t04g05.elements.Walls;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovementBlockedbyWallsTest {

    @Test
    public void testMoveBlockedByWall() {
        Character character = new Character(5, 5);
        Walls wall = new Walls(5, 4); // Posição da parede
        // Tentar mover o character para a posição da parede
        Position originalPosition = character.getPosition();
        character.moveTo(new Position(5, 4));

        // Verificar que o character não mudou de posição
        assertEquals(originalPosition, character.getPosition());
    }
}

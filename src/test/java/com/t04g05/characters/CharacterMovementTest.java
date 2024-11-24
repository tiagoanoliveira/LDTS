package com.t04g05.characters;

import com.t04g05.elements.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterMovementTest {

    @Test
    public void testMoveUp() {
        Character character = new Character(5, 5);
        character.moveTo(new Position(5, 4)); // Mover para cima
        assertEquals(5, character.getPosition().getX());
        assertEquals(4, character.getPosition().getY());
    }

    @Test
    public void testMoveDown() {
        Character character = new Character(5, 5);
        character.moveTo(new Position(5, 6)); // Mover para baixo
        assertEquals(5, character.getPosition().getX());
        assertEquals(6, character.getPosition().getY());
    }

    @Test
    public void testMoveLeft() {
        Character character = new Character(5, 5);
        character.moveTo(new Position(4, 5)); // Mover para a esquerda
        assertEquals(4, character.getPosition().getX());
        assertEquals(5, character.getPosition().getY());
    }

    @Test
    public void testMoveRight() {
        Character character = new Character(5, 5);
        character.moveTo(new Position(6, 5)); // Mover para a direita
        assertEquals(6, character.getPosition().getX());
        assertEquals(5, character.getPosition().getY());
    }
}

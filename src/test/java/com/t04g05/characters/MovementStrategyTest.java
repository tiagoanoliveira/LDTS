package com.t04g05.characters;

import com.t04g05.elements.Obstacle;
import com.t04g05.elements.Walls;
import com.t04g05.patterns.MovementStrategy;
import com.t04g05.patterns.NormalMovement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.HashSet;
import java.util.Set;

public class MovementStrategyTest {

    @Test
    public void testNormalMovement() {
        Character character = new Character(5, 5);
        MovementStrategy strategy = new NormalMovement();

        // Simula a seleção de uma tecla para mover para a direita (chave 'D')
        KeyStroke key = new KeyStroke('D', false, false, false);

        // Cria coleções vazias para paredes e obstáculos
        Set<Walls> walls = new HashSet<>();
        Set<Obstacle> obstacles = new HashSet<>();

        // Verifica o movimento para a direita
        strategy.move(character, key, walls, obstacles); // Passa KeyStroke e coleções vazias
        assertEquals(6, character.getPosition().getX());
        assertEquals(5, character.getPosition().getY());

        // Simula a seleção de uma tecla para mover para cima (chave 'W')
        key = new KeyStroke('W', false, false, false);
        strategy.move(character, key, walls, obstacles);
        assertEquals(6, character.getPosition().getX());
        assertEquals(4, character.getPosition().getY());
    }
}
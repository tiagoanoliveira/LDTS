package com.t04g05.model.game.elements;

import com.t04g05.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character character;

    @BeforeEach
    void setUp() {
        // Inicializa a personagem na posição (5, 5) antes de cada teste
        character = new Character(new Position(5, 5));
    }

    @Test
    void testInitialLives() {
        // Verifica se a personagem inicia com 3 vidas
        assertEquals(3, character.getLives(), "A personagem deveria começar com 3 vidas.");
    }

    @Test
    void testDecreaseLives() {
        // Reduz as vidas da personagem e verifica o valor
        character.decreaseLives();
        assertEquals(2, character.getLives(), "As vidas deveriam ser reduzidas para 2 após decreaseLives().");

        character.decreaseLives();
        assertEquals(1, character.getLives(), "As vidas deveriam ser reduzidas para 1 após outra decreaseLives().");
    }

    @Test
    void testInitialScore() {
        // Verifica se a pontuação inicial é 0
        assertEquals(0, character.getScore(), "A personagem deveria começar com 0 de pontuação.");
    }

    @Test
    void testIncreaseScore() {
        // Incrementa a pontuação e verifica o valor
        character.increaseScore();
        assertEquals(1, character.getScore(), "A pontuação deveria ser 1 após increaseScore().");

        character.increaseScore();
        character.increaseScore();
        assertEquals(3, character.getScore(), "A pontuação deveria ser 3 após mais dois increaseScore().");
    }

    @Test
    void testInitialPosition() {
        // Verifica se a posição inicial está correta
        assertEquals(new Position(5, 5), character.getPosition(), "A posição inicial deveria ser (5, 5).");
    }

    @Test
    void testSetPosition() {
        // Altera a posição da personagem e verifica o resultado
        character.setPosition(new Position(10, 10));
        assertEquals(new Position(10, 10), character.getPosition(), "A posição deveria ser alterada para (10, 10).");
    }
}


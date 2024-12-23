package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Arena2Test {

    private Arena2 arena;
    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character(new Position(1, 1)); // Posição inicial do personagem
        arena = new Arena2(character); // Criação da arena com o personagem
    }

    @Test
    void testArenaInitialization() {
        // Verifica se o personagem foi corretamente colocado na arena
        assertNotNull(arena.getCharacter(), "O personagem não deveria ser nulo.");

        // Verifica se a arena possui um conjunto de paredes
        assertFalse(arena.getWalls().isEmpty(), "A arena deveria ter paredes.");

        // Verifica se as moedas foram corretamente adicionadas à arena
        assertFalse(arena.getCoins().isEmpty(), "A arena deveria ter pelo menos uma moeda.");
    }

    @Test
    void testWallAtPosition() {
        Position wallPosition = new Position(0, 0); // Supondo que exista uma parede na posição (0, 0)
        Position emptyPosition = new Position(5, 5); // Posição onde não deve haver parede

        // Verifica se a posição (0, 0) é realmente uma parede
        assertTrue(arena.isWall(wallPosition), "A posição (0, 0) deveria ser uma parede.");

        // Verifica se a posição (5, 5) não é uma parede
        assertFalse(arena.isWall(emptyPosition), "A posição (5, 5) não deveria ser uma parede.");
    }

    @Test
    void testCoinPlacement() {
        // Verifica se o número de moedas é o esperado
        assertEquals(15, arena.getCoins().size(), "A arena deveria ter 15 moedas.");

        // Verifica se as moedas não estão sendo colocadas em posições de parede
        for (Coin coin : arena.getCoins()) {
            assertFalse(arena.isWall(coin.getPosition()), "A moeda não pode ser colocada em uma posição de parede.");
        }
    }

    @Test
    void testCoinCollection() {
        Position coinPosition = new Position(2, 2); // Posição da moeda
        Coin coin = new Coin(coinPosition);
        arena.getCoins().add(coin); // Adiciona a moeda à arena

        // Move o personagem para a posição da moeda
        arena.updateCharacter(coinPosition);

        // Verifica se a moeda foi coletada corretamente
        assertFalse(arena.getCoins().contains(coin), "A moeda deveria ter sido removida após ser coletada.");
    }

    @Test
    void testCollisionWithEnemy() {
        // Verifica o número de vidas do personagem antes da colisão
        int initialLives = arena.getCharacter().getLives();

        // Move o personagem para a posição de um inimigo (supondo que o inimigo esteja na posição (40, 6))
        arena.updateCharacter(new Position(40, 6));

        // Verifica se o personagem perdeu uma vida após a colisão com o inimigo
        assertEquals(initialLives - 1, arena.getCharacter().getLives(), "O personagem deveria perder uma vida após a colisão com um inimigo.");
    }

    @Test
    void testCharacterMovement() {
        Position initialPosition = arena.getCharacter().getPosition();
        Position newPosition = new Position(2, 2);

        // Move o personagem para uma nova posição
        arena.updateCharacter(newPosition);

        // Verifica se a posição do personagem foi atualizada corretamente
        assertEquals(newPosition, arena.getCharacter().getPosition(), "A posição do personagem deveria ter sido atualizada.");

        // Verifica se a posição original ainda não está ocupada pelo personagem
        assertNotEquals(initialPosition, arena.getCharacter().getPosition(), "O personagem não deveria permanecer na posição inicial.");
    }
}

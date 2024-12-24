package java.com.t04g05.model.game.arena;

import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Coin;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Arena1Test {

    private Arena1 arena;
    private Character character;

    @BeforeEach
    void setUp() {
        arena = new Arena1();
        character = new Character(new Position(1,1));
    }

    @Test
    void testInitializeElements() {
        assertNotNull(arena.getCharacter(), "O personagem não deveria ser nulo.");
        // Verifica se as paredes foram corretamente adicionadas ao labirinto
        assertFalse(arena.getWalls().isEmpty(), "Deveria haver paredes no labirinto.");
    }

    @Test
    void testPlaceCoins() {
        // Verifica se o número de moedas é igual ao esperado (15 moedas)
        assertEquals(15, arena.getCoins().size(), "Deveriam haver 15 moedas na arena.");
    }

    @Test
    void testIsWall() {
        // Verifica se o método isWall funciona corretamente
        Position wallPosition = new Position(16, 11); // Uma posição que deve ser uma parede
        assertTrue(arena.isWall(wallPosition), "Deveria ser uma parede.");

        Position nonWallPosition = new Position(5, 5); // Uma posição que não deve ser uma parede
        assertFalse(arena.isWall(nonWallPosition), "Não deveria ser uma parede.");
    }

    @Test
    void testCoinPlacement() {
        // Verifica se as moedas não estão sendo colocadas sobre as paredes
        for (Coin coin : arena.getCoins()) {
            assertFalse(arena.isWall(coin.getPosition()), "A moeda não pode ser colocada em uma parede.");
        }
    }

    @Test
    void testGoalPosition() {
        // Verifica se as posições do objetivo estão sendo configuradas corretamente
        Position goalPosition = new Position(64, 70); // A posição do objetivo definida no método setGoalPositions
        // Verifique se o intervalo de posições do objetivo está dentro dos limites esperados
        assertTrue(goalPosition.getX() >= 64 && goalPosition.getX() <= 70, "A posição do objetivo está fora do intervalo esperado.");
        assertTrue(goalPosition.getY() >= 20 && goalPosition.getY() <= 25, "A posição do objetivo está fora do intervalo esperado.");
    }

    @Test
    void testCharacterMovement() {
        Position initialPosition = character.getPosition();
        Position newPosition = new Position(2, 2);

        // Move o personagem para a nova posição
        arena.updateCharacter(newPosition);

        // Verifica se a posição do personagem foi atualizada corretamente
        assertEquals(newPosition, character.getPosition(), "A posição do personagem não foi atualizada corretamente.");

        // Verifica se a posição original não está mais ocupada
        assertNotEquals(initialPosition, character.getPosition(), "O personagem ainda está na posição original.");
    }

    @Test
    void testCharacterCannotMoveThroughWalls() {
        Position wallPosition = new Position(16, 11); // Posição de uma parede
        // Tenta mover o personagem para uma posição de parede
        arena.updateCharacter(wallPosition);

        // Verifica se o personagem permanece na posição original
        assertNotEquals(wallPosition, character.getPosition(), "O personagem não pode se mover para uma posição de parede.");
    }

    @Test
    void testCharacterCollectCoin() {
        // Adiciona uma moeda à posição (3, 3)
        Coin coin = new Coin(new Position(3, 3));
        arena.getCoins().add(coin);

        // Move o personagem para a posição da moeda
        arena.updateCharacter(new Position(3, 3));

        // Verifica se a moeda foi coletada corretamente
        assertFalse(arena.getCoins().contains(coin), "A moeda deveria ser coletada e removida.");
    }
}

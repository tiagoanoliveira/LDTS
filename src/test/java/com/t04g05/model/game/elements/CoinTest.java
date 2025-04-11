package java.com.t04g05.model.game.elements;

import com.t04g05.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    @Test
    void testCoinCreation() {
        // Cria uma moeda na posição (3, 7)
        Position position = new Position(3, 7);
        Coin coin = new Coin(position);

        // Verifica se a posição da moeda está correta
        assertEquals(position, coin.getPosition(), "A posição da moeda deveria ser (3, 7).");
    }

    @Test
    void testSetPosition() {
        // Cria uma moeda e altera sua posição
        Coin coin = new Coin(new Position(1, 1));
        Position newPosition = new Position(5, 10);

        coin.setPosition(newPosition);

        // Verifica se a posição foi alterada corretamente
        assertEquals(newPosition, coin.getPosition(), "A posição da moeda deveria ser (5, 10).");
    }

    @Test
    void testGetXAndY() {
        // Cria uma moeda e verifica as coordenadas X e Y
        Coin coin = new Coin(new Position(4, 9));

        assertEquals(4, coin.getPosition().getX(), "A coordenada X da moeda deveria ser 4.");
        assertEquals(9, coin.getPosition().getY(), "A coordenada Y da moeda deveria ser 9.");
    }
}

package java.com.t04g05.model.game.elements;

import com.t04g05.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallsTest {

    @Test
    void testWallsCreation() {
        // Cria uma parede na posição (10, 20)
        Position position = new Position(10, 20);
        Walls walls = new Walls(position);

        // Verifica se a posição da parede está correta
        assertEquals(position, walls.getPosition(), "A posição da parede deveria ser (10, 20).");
    }

    @Test
    void testSetPosition() {
        // Cria uma parede e altera sua posição
        Walls walls = new Walls(new Position(1, 1));
        Position newPosition = new Position(5, 10);

        walls.setPosition(newPosition);

        // Verifica se a posição foi alterada corretamente
        assertEquals(newPosition, walls.getPosition(), "A posição da parede deveria ser (5, 10).");
    }

    @Test
    void testGetXAndY() {
        // Cria uma parede e verifica as coordenadas X e Y
        Walls walls = new Walls(new Position(4, 9));

        assertEquals(4, walls.getPosition().getX(), "A coordenada X da parede deveria ser 4.");
        assertEquals(9, walls.getPosition().getY(), "A coordenada Y da parede deveria ser 9.");
    }
}

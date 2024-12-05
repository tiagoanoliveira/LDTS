package com.t04g05.characters;

import com.t04g05.model.Position;
import com.t04g05.model.game.elementos.Obstacle;
import com.t04g05.model.game.elementos.Character;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MovementBlockedbyObstaclesTest {

    @Test
    public void testMoveBlockedByObstacle() {
        Character character = new Character(5, 5);
        Obstacle obstacle = new Obstacle(5, 4); // Posição do obstáculo

        // Simular lista de obstáculos no jogo
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        // Verificar se o personagem pode se mover para a posição do obstáculo
        Position originalPosition = character.getPosition();
        Position targetPosition = new Position(5, 4);

        if (obstacles.stream().anyMatch(o -> o.getPosition().equals(targetPosition))) {
            character.moveTo(originalPosition); // Movimento bloqueado
        } else {
            character.moveTo(targetPosition);
        }

        // Verificar que o personagem não mudou de posição
        assertEquals(originalPosition, character.getPosition());
    }
}

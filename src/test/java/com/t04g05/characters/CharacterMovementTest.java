package com.t04g05.characters;

import org.junit.jupiter.api.Test;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t04g05.elements.Obstacle;
import com.t04g05.elements.Position;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CharacterMovementTest {

    @Test
    void characterCannotMoveIntoObstacle() {
        // Configuração inicial
        // Criar personagem na posição inicial (5, 5)
        Character character = new Character(5, 5);

        // Criar um obstáculo na posição (6, 5)
        Obstacle obstacle = new Obstacle(6, 5);
        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(obstacle);

        // Simular um movimento para a direita (tecla "ArrowRight")
        KeyStroke key = new KeyStroke(KeyType.ArrowRight, false, false);
        character.processKey(key, new HashSet<>(), obstacles);

        // Validação
        // Verificar que o personagem não se moveu para (6, 5)
        assertEquals(new Position(5, 5), character.getPosition(),
                "O personagem não deveria atravessar o obstáculo.");
    }
}

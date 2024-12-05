package com.t04g05.characters;

import model.Position;
import model.game.elementos.Character;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevelExecutionTest {

    @Test
    public void testLevelExecution() throws InterruptedException {
        model.game.elementos.Character character = new Character(5, 5);

        // Simular o objetivo do nível
        Position goalPosition = new Position(10, 10);

        // Movimentar o personagem até o objetivo
        character.moveTo(goalPosition);

        // Simular o comportamento de nível completo
        boolean levelCompleted = character.getPosition().equals(goalPosition);

        // Verificar se o nível foi concluído
        assertTrue(levelCompleted);
    }
}

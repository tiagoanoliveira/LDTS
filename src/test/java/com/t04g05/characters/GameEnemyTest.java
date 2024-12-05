package com.t04g05.characters;

import com.t04g05.patterns.GameObserver;
import com.t04g05.patterns.GameSubject;
import com.t04g05.model.game.elementos.Character;
import com.t04g05.model.game.elementos.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEnemyTest {

    @Test
    void testGameEndsOnCharacterEnemyCollision() {
        // Configurar o ambiente
        GameSubject gameSubject = new GameSubject();
        TestObserver testObserver = new TestObserver();
        gameSubject.addObserver(testObserver);

        Character character = new Character(5, 5);
        Enemy enemy = new Enemy(5, 5); // Mesmo local do character para simular colisão

        // Verificar a colisão
        if (enemy.isCollidingWithCharacter(character)) {
            gameSubject.notifyGameOver();
        }

        // Garantir que o utilizador detetou o fim do jogo
        assertTrue(testObserver.gameOverTriggered, "O jogo deve terminar quando o personagem colidir com um inimigo.");
    }

    // Classe auxiliar para capturar o evento de término do jogo
    static class TestObserver implements GameObserver {
        boolean gameOverTriggered = false;

        @Override
        public void onGoalReached() {
            // Não usado neste teste
        }

        @Override
        public void onGameOver() {
            gameOverTriggered = true;
        }
    }
}

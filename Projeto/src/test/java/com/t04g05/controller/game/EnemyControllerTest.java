package java.com.t04g05.controller.game;

import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnemyControllerTest {
    private Arena arenaMock;
    private EnemyController enemyController;
    private Random randomMock;

    @BeforeEach
    void setUp() {
        // Mock da Arena
        arenaMock = Mockito.mock(Arena.class);

        // Mock do Random
        randomMock = Mockito.mock(Random.class);

        // Inicializa o EnemyController com o mock da Arena
        enemyController = new EnemyController(arenaMock) {
            @Override
            public Random getRandom() {
                return randomMock; // Usamos o mock do Random
            }
        };
    }

    @Test
    void testUpdateEnemies() {
        // Mockando os inimigos
        List<Enemy> enemies = new ArrayList<>();
        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        enemies.add(enemy1);
        enemies.add(enemy2);

        // Configura posições atuais dos inimigos
        Position position1 = new Position(5, 5);
        Position position2 = new Position(10, 10);

        when(enemy1.getPosition()).thenReturn(position1);
        when(enemy2.getPosition()).thenReturn(position2);

        // Mock da lista de inimigos na Arena
        when(arenaMock.getEnemies()).thenReturn(enemies);

        // Simula movimentos válidos
        when(arenaMock.canMoveTo(any(Position.class))).thenReturn(true);

        // Simula que o Random escolhe índices específicos
        when(randomMock.nextInt(anyInt())).thenReturn(0);

        // Executa o método updateEnemies
        enemyController.updateEnemies();

        // Verifica se os inimigos foram movidos
        verify(enemy1, times(1)).setPosition(any(Position.class));
        verify(enemy2, times(1)).setPosition(any(Position.class));
    }

    @Test
    void testUpdateEnemiesWithNoValidMoves() {
        // Mockando os inimigos
        List<Enemy> enemies = new ArrayList<>();
        Enemy enemy = Mockito.mock(Enemy.class);
        enemies.add(enemy);

        // Configura posição atual do inimigo
        Position position = new Position(5, 5);
        when(enemy.getPosition()).thenReturn(position);

        // Mock da lista de inimigos na Arena
        when(arenaMock.getEnemies()).thenReturn(enemies);

        // Simula que nenhum movimento é válido
        when(arenaMock.canMoveTo(any(Position.class))).thenReturn(false);

        // Executa o método updateEnemies
        enemyController.updateEnemies();

        // Verifica que o inimigo não se moveu
        verify(enemy, never()).setPosition(any());
    }

    @Test
    void testGetRandomValidPositionWithValidMove() {
        // Configura posição inicial
        Position currentPosition = new Position(5, 5);

        // Simula movimentos válidos
        when(arenaMock.canMoveTo(any(Position.class))).thenReturn(true);

        // Simula que o Random escolhe um índice específico
        when(randomMock.nextInt(anyInt())).thenReturn(0);

        // Chama o método getRandomValidPosition
        Position newPosition = enemyController.getRandomValidPosition(currentPosition);

        // Verifica se a nova posição não é nula e é válida
        assertNotNull(newPosition);
        verify(arenaMock, atLeastOnce()).canMoveTo(newPosition);
    }

    @Test
    void testGetRandomValidPositionWithNoValidMoves() {
        // Configura posição inicial
        Position currentPosition = new Position(5, 5);

        // Simula que nenhum movimento é válido
        when(arenaMock.canMoveTo(any(Position.class))).thenReturn(false);

        // Chama o método getRandomValidPosition
        Position newPosition = enemyController.getRandomValidPosition(currentPosition);

        // Verifica que nenhuma posição válida foi encontrada
        assertNull(newPosition);
        verify(arenaMock, atLeastOnce()).canMoveTo(any(Position.class));
    }
}

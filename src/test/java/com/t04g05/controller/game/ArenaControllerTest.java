package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArenaControllerTest {
    private Arena arenaMock;
    private EnemyController enemyControllerMock;
    private CharacterController characterControllerMock;
    private ArenaController arenaController;

    @BeforeEach
    void setUp() {
        // Mockando as dependências
        arenaMock = Mockito.mock(Arena.class);
        enemyControllerMock = Mockito.mock(EnemyController.class);
        characterControllerMock = Mockito.mock(CharacterController.class);

        // Inicializando o ArenaController
        arenaController = new ArenaController(arenaMock);

        // Substituímos os controladores internos por mocks para facilitar o teste
        arenaController = Mockito.spy(arenaController);
        Mockito.doReturn(enemyControllerMock).when(arenaController).getEnemyController();
        Mockito.doReturn(characterControllerMock).when(arenaController).getCharacterController();
    }

    @Test
    void testGetArena() {
        // Verifica se o método getArena retorna a instância correta
        assertEquals(arenaMock, arenaController.getArena());
    }

    @Test
    void testIsGoalReached() {
        // Configura o mock para simular que o objetivo foi alcançado
        when(characterControllerMock.checkGoal()).thenReturn(true);

        // Verifica se o método retorna true quando o objetivo é alcançado
        assertTrue(arenaController.isGoalReached());

        // Configura o mock para simular que o objetivo não foi alcançado
        when(characterControllerMock.checkGoal()).thenReturn(false);

        // Verifica se o método retorna false quando o objetivo não é alcançado
        assertFalse(arenaController.isGoalReached());
    }

    @Test
    void testProcessInput() {
        // Simula uma ação do jogador
        GUI.ACTION action = GUI.ACTION.UP;

        // Chama o método processInput
        arenaController.processInput(action);

        // Verifica se o método processInput do CharacterController foi chamado com a ação correta
        verify(characterControllerMock, times(1)).processInput(action);
    }

}

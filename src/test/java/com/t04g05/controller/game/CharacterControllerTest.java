package com.t04g05.controller.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Element;
import com.t04g05.model.game.elements.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterControllerTest {
    private Arena arenaMock;
    private Character characterMock;
    private CharacterController characterController;

    @BeforeEach
    void setUp() {
        // Mockando as dependências
        arenaMock = Mockito.mock(Arena.class);
        characterMock = Mockito.mock(Character.class);

        // Configurando o mock do personagem no mock da arena
        when(arenaMock.getCharacter()).thenReturn(characterMock);

        // Inicializando o CharacterController
        characterController = new CharacterController(arenaMock);
    }

    @Test
    void testProcessInputWithValidMove() {
        // Configura a posição inicial do personagem
        Position currentPosition = new Position(5, 5);
        when(characterMock.getPosition()).thenReturn(currentPosition);

        // Simula que a nova posição é válida
        Position newPosition = new Position(5, 4);
        when(arenaMock.getWidth()).thenReturn(10);
        when(arenaMock.getHeight()).thenReturn(10);
        when(arenaMock.getElements()).thenReturn(List.of());
        when(arenaMock.getCharacter().getPosition()).thenReturn(currentPosition);

        // Processa a ação UP
        characterController.processInput(GUI.ACTION.UP);

        // Verifica se a posição do personagem foi atualizada
        verify(characterMock, times(1)).setPosition(newPosition);

        // Verifica se a arena chamou o método para verificar a coleta de moedas
        verify(arenaMock, times(1)).checkCoinCollection();
    }

    @Test
    void testProcessInputWithInvalidMove() {
        // Configura a posição inicial do personagem
        Position currentPosition = new Position(0, 0);
        when(characterMock.getPosition()).thenReturn(currentPosition);

        // Simula que a nova posição é inválida (fora dos limites)
        when(arenaMock.getWidth()).thenReturn(10);
        when(arenaMock.getHeight()).thenReturn(10);
        when(arenaMock.getElements()).thenReturn(List.of());

        // Processa a ação UP
        characterController.processInput(GUI.ACTION.UP);

        // Verifica se a posição do personagem não foi alterada
        verify(characterMock, never()).setPosition(any());
        verify(arenaMock, never()).checkCoinCollection();
    }

    @Test
    void testUpdateCharacter() {
        // Configura a posição atual do personagem
        Position currentPosition = new Position(5, 5);
        when(characterMock.getPosition()).thenReturn(currentPosition);

        // Chama o método para atualizar o personagem
        characterController.updateCharacter();

        // Verifica se a arena chamou o método para atualizar o personagem
        verify(arenaMock, times(1)).updateCharacter(currentPosition);
    }

    @Test
    void testCheckCollisions() {
        // Simula que o personagem tem 0 vidas
        when(characterMock.getLives()).thenReturn(0);

        // Chama o método para verificar colisões
        characterController.checkCollisions();

        // Aqui, verificamos a saída da consola se necessário ou apenas que o método foi executado
        verify(characterMock, times(1)).getLives();
    }

    @Test
    void testCheckGoalWhenGoalReached() {
        // Simula que o personagem alcançou o objetivo
        Position goalPosition = new Position(5, 5);
        when(characterMock.getPosition()).thenReturn(goalPosition);
        when(arenaMock.getGoalPositions()).thenReturn((Set<Position>) List.of(goalPosition));

        // Verifica se o método retorna true
        assertTrue(characterController.checkGoal());
    }

    @Test
    void testCheckGoalWhenGoalNotReached() {
        // Simula que o personagem não alcançou o objetivo
        Position characterPosition = new Position(5, 5);
        when(characterMock.getPosition()).thenReturn(characterPosition);
        when(arenaMock.getGoalPositions()).thenReturn((Set<Position>) List.of(new Position(10, 10)));

        // Verifica se o método retorna false
        assertFalse(characterController.checkGoal());
    }
}

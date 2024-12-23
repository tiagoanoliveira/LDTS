package com.t04g05.states.game;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Character;
import com.t04g05.states.GameOverState;
import com.t04g05.states.Level4State;
import com.t04g05.viewer.game.ArenaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Level4StateTest {
    @Mock
    private GUI gui;
    @Mock
    private ArenaController arenaController;
    @Mock
    private ArenaViewer arenaViewer;
    @Mock
    private Arena arena;
    @Mock
    private Character character;

    private Level4State level4State;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(arenaController.getArena()).thenReturn(arena);
        when(arena.getCharacter()).thenReturn(character);

        // Inicializa o estado com um personagem mockado
        level4State = new Level4State(character);
    }

    @Test
    public void testStepGoalReached() throws IOException {
        when(arenaController.isGoalReached()).thenReturn(true);

        level4State.step(gui);

        assertNull(level4State.getNextState());
    }

    @Test
    public void testStepGameOver() throws IOException {
        when(character.getLives()).thenReturn(0);

        level4State.step(gui);

        assertTrue(level4State.getNextState() instanceof GameOverState);
    }

    @Test
    public void testStepQuitAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.QUIT);

        level4State.step(gui);

        assertNull(level4State.getNextState());
    }

    @Test
    public void testStepMaintainsState() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        when(character.getLives()).thenReturn(1);
        when(arenaController.isGoalReached()).thenReturn(false);

        level4State.step(gui);

        assertEquals(level4State, level4State.getNextState());
    }

    @Test
    public void testGetArenaReturnsCorrectArena() {
        assertEquals(arena, level4State.getArena());
    }

    @Test
    public void testCharacterPositionInitializedCorrectly() {
        Position expectedPosition = new Position(4, 30);
        verify(character).setPosition(eq(expectedPosition));
    }

    @Test
    public void testIOExceptionHandledGracefully() throws IOException {
        doThrow(new IOException("Test Exception")).when(gui).getNextAction();

        assertDoesNotThrow(() -> level4State.step(gui));
    }
}

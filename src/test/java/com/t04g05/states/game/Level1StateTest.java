package com.t04g05.states.game;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Character;
import com.t04g05.states.GameOverState;
import com.t04g05.states.Level1State;
import com.t04g05.states.Level2State;
import com.t04g05.viewer.game.ArenaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Level1StateTest {
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

    private Level1State level1State;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(arenaController.getArena()).thenReturn(arena);
        when(arena.getCharacter()).thenReturn(character);
        level1State = new Level1State();
    }

    @Test
    public void testStepGoalReached() throws IOException {
        when(arenaController.isGoalReached()).thenReturn(true);
        when(character.getScore()).thenReturn(100);

        level1State.step(gui);

        assertTrue(level1State.getNextState() instanceof Level2State);
    }

    @Test
    public void testStepGameOver() throws IOException {
        when(character.getLives()).thenReturn(0);

        level1State.step(gui);

        assertTrue(level1State.getNextState() instanceof GameOverState);
    }

    @Test
    public void testStepQuitAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.QUIT);

        level1State.step(gui);

        assertNull(level1State.getNextState());
    }

    @Test
    public void testStepMaintainsState() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        when(character.getLives()).thenReturn(1);
        when(arenaController.isGoalReached()).thenReturn(false);

        level1State.step(gui);

        assertEquals(level1State, level1State.getNextState());
    }

    @Test
    public void testGetArenaReturnsCorrectArena() {
        assertEquals(arena, level1State.getArena());
    }

    @Test
    public void testIOExceptionHandledGracefully() throws IOException {
        doThrow(new IOException("Test Exception")).when(gui).getNextAction();

        assertDoesNotThrow(() -> level1State.step(gui));
    }
}

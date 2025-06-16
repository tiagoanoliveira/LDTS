package java.com.t04g05.states.game;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.Character;
import com.t04g05.states.GameOverState;
import com.t04g05.states.Level2State;
import com.t04g05.states.Level3State;
import com.t04g05.viewer.game.ArenaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Level2StateTest {
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

    private Level2State level2State;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(arenaController.getArena()).thenReturn(arena);
        when(arena.getCharacter()).thenReturn(character);

        // Criar um personagem mockado para o Level2State
        level2State = new Level2State(character);
    }

    @Test
    public void testStepGoalReached() throws IOException {
        when(arenaController.isGoalReached()).thenReturn(true);

        level2State.step(gui);

        assertTrue(level2State.getNextState() instanceof Level3State);
    }

    @Test
    public void testStepGameOver() throws IOException {
        when(character.getLives()).thenReturn(0);

        level2State.step(gui);

        assertTrue(level2State.getNextState() instanceof GameOverState);
    }

    @Test
    public void testStepQuitAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.QUIT);

        level2State.step(gui);

        assertNull(level2State.getNextState());
    }

    @Test
    public void testStepMaintainsState() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        when(character.getLives()).thenReturn(1);
        when(arenaController.isGoalReached()).thenReturn(false);

        level2State.step(gui);

        assertEquals(level2State, level2State.getNextState());
    }

    @Test
    public void testGetArenaReturnsCorrectArena() {
        assertEquals(arena, level2State.getArena());
    }

    @Test
    public void testCharacterPositionInitializedCorrectly() {
        Position expectedPosition = new Position(4, 7);
        verify(character).setPosition(eq(expectedPosition));
    }

    @Test
    public void testIOExceptionHandledGracefully() throws IOException {
        doThrow(new IOException("Test Exception")).when(gui).getNextAction();

        assertDoesNotThrow(() -> level2State.step(gui));
    }
}

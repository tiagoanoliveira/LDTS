package java.com.t04g05.states.menu;

import com.t04g05.controller.menu.MenuController;
import com.t04g05.gui.GUI;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;
import com.t04g05.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuStateTest {
    @Mock
    private MenuController controller;
    @Mock
    private GUI gui;
    @Mock
    private MenuViewer viewer;

    private MenuState menuState;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(controller.getMenu()).thenReturn(mock(com.t04g05.model.menu.Menu.class));
        menuState = new MenuState(controller, gui);
    }

    @Test
    public void testStepInvokesDrawAndProcessInput() throws Exception {
        // Mock behavior
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        when(controller.processInput(GUI.ACTION.UP)).thenReturn(null);

        // Execute step
        menuState.step(gui);

        // Verify interactions
        verify(gui).clear();
        verify(gui).refresh();
        verify(gui).getNextAction();
        verify(controller).processInput(GUI.ACTION.UP);
        verifyNoMoreInteractions(gui, controller);
    }

    @Test
    public void testStepChangesState() throws Exception {
        // Mock a next state
        GameState nextState = mock(GameState.class);
        when(gui.getNextAction()).thenReturn(GUI.ACTION.ENTER);
        when(controller.processInput(GUI.ACTION.ENTER)).thenReturn(nextState);

        // Execute step
        menuState.step(gui);

        // Assert the state is updated
        assertEquals(nextState, menuState.getNextState());
    }

    @Test
    public void testGetArenaReturnsNull() {
        assertNull(menuState.getArena());
    }

    @Test
    public void testExceptionInStepIsHandled() throws Exception {
        // Mock behavior that throws an exception
        doThrow(new RuntimeException("Test Exception")).when(gui).getNextAction();

        // Execute step
        assertDoesNotThrow(() -> menuState.step(gui));
    }
}

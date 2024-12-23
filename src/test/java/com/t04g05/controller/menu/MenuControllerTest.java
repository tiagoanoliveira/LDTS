package java.com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.GameState;
import com.t04g05.states.Level1State;
import com.t04g05.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {
    private Menu menuMock;
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        menuMock = Mockito.mock(Menu.class); // Cria um mock para a classe Menu
        menuController = new MenuController(menuMock); // Inicializa o MenuController com o mock
    }

    @Test
    void testProcessInputUpAction() {
        // Testa a navegação para a opção anterior
        menuController.processInput(GUI.ACTION.UP);
        verify(menuMock, times(1)).previousOption();
    }

    @Test
    void testProcessInputDownAction() {
        // Testa a navegação para a próxima opção
        menuController.processInput(GUI.ACTION.DOWN);
        verify(menuMock, times(1)).nextOption();
    }

    @Test
    void testProcessInputEnterStartGame() {
        // Configura o mock para simular o estado do menu como MAIN_MENU e a opção Start Game selecionada
        when(menuMock.getMode()).thenReturn(Menu.Mode.MAIN_MENU);
        when(menuMock.isStartGameSelected()).thenReturn(true);

        GameState result = menuController.processInput(GUI.ACTION.ENTER);

        // Verifica que o jogo transita para o estado Level1State
        assertTrue(result instanceof Level1State);
        verify(menuMock, never()).nextOption();
        verify(menuMock, never()).previousOption();
    }

    @Test
    void testProcessInputEnterInstructions() {
        // Configura o mock para simular o estado do menu como MAIN_MENU e a opção Instructions selecionada
        when(menuMock.getMode()).thenReturn(Menu.Mode.MAIN_MENU);
        when(menuMock.isInstructionsSelected()).thenReturn(true);

        GameState result = menuController.processInput(GUI.ACTION.ENTER);

        // Verifica que o menu muda para o modo INSTRUCTIONS
        assertNull(result);
        verify(menuMock, times(1)).setMode(Menu.Mode.INSTRUCTIONS);
    }

    @Test
    void testProcessInputEnterExitGame() {
        // Configura o mock para simular o estado do menu como MAIN_MENU e a opção Exit selecionada
        when(menuMock.getMode()).thenReturn(Menu.Mode.MAIN_MENU);
        when(menuMock.isExitSelected()).thenReturn(true);

        GameState result = menuController.processInput(GUI.ACTION.ENTER);

        // Verifica que o jogo retorna null para sair
        assertNull(result);
    }

    @Test
    void testProcessInputEnterBackFromInstructions() {
        // Configura o mock para simular o estado do menu como INSTRUCTIONS e a opção Back selecionada
        when(menuMock.getMode()).thenReturn(Menu.Mode.INSTRUCTIONS);
        when(menuMock.isBackSelected()).thenReturn(true);

        GameState result = menuController.processInput(GUI.ACTION.ENTER);

        // Verifica que o menu muda de volta para o MAIN_MENU
        assertNull(result);
        verify(menuMock, times(1)).setMode(Menu.Mode.MAIN_MENU);
    }

    @Test
    void testProcessInputEscOrQuit() {
        // Testa se a ação ESC ou QUIT retorna null para sair do jogo
        GameState resultEsc = menuController.processInput(GUI.ACTION.ESC);
        GameState resultQuit = menuController.processInput(GUI.ACTION.QUIT);

        assertNull(resultEsc);
        assertNull(resultQuit);
    }

    @Test
    void testProcessInputNone() {
        // Testa se a ação NONE retorna um estado do menu sem alterações
        GameState result = menuController.processInput(GUI.ACTION.NONE);

        assertNotNull(result);
        assertTrue(result instanceof MenuState);
    }
}


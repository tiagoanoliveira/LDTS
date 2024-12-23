package com.t04g05.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t04g05.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanternaGUITest {
    private LanternaGUI lanternaGUI;
    private TerminalScreen screenMock;
    private TextGraphics textGraphicsMock;

    @BeforeEach
    void setUp() throws IOException {
        // Mock do TerminalScreen e TextGraphics
        screenMock = mock(TerminalScreen.class);
        textGraphicsMock = mock(TextGraphics.class);

        // Substituir a inicialização para usar os mocks
        lanternaGUI = new LanternaGUI() {
            {
                this.screen = screenMock;
                this.textGraphics = textGraphicsMock;
            }
        };
    }

    @Test
    void testSetBackgroundColor() {
        // Configura a cor de fundo
        lanternaGUI.setBackgroundColor("#FFFFFF");

        // Verifica se o TextGraphics foi chamado corretamente
        verify(textGraphicsMock, times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(textGraphicsMock, times(1)).fill(' ');
    }

    @Test
    void testDrawText() {
        // Desenha um texto na posição (5, 5)
        lanternaGUI.drawText(5, 5, "Hello", "#FF0000", "#00FF00");

        // Verifica se o TextGraphics configurou as cores e desenhou o texto
        verify(textGraphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(textGraphicsMock, times(1)).putString(5, 5, "Hello");
    }

    @Test
    void testDrawElement() {
        // Desenha um elemento na posição (3, 3)
        lanternaGUI.drawElement(new Position(3, 3), 'X', "#0000FF", "#FFFFFF");

        // Verifica se o TextGraphics configurou as cores e desenhou o caractere
        verify(textGraphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        verify(textGraphicsMock, times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(textGraphicsMock, times(1)).putString(3, 3, "X");
    }

    @Test
    void testClearScreen() throws IOException {
        // Limpa a tela
        lanternaGUI.clear();

        // Verifica se o método clear foi chamado no screen
        verify(screenMock, times(1)).clear();
    }

    @Test
    void testRefreshScreen() throws IOException {
        // Atualiza a tela
        lanternaGUI.refresh();

        // Verifica se o método refresh foi chamado no screen
        verify(screenMock, times(1)).refresh();
    }

    @Test
    void testClose() throws IOException {
        // Fecha a interface
        lanternaGUI.close();

        // Verifica se o screen e o terminal foram fechados
        verify(screenMock, times(1)).close();
    }

    @Test
    void testGetNextActionArrowKeys() throws IOException {
        // Simula entrada do usuário: tecla de seta para cima
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowUp, false, false);
        when(screenMock.readInput()).thenReturn(keyStroke);

        // Verifica se a ação corresponde ao esperado
        assertEquals(GUI.ACTION.UP, lanternaGUI.getNextAction());
    }

    @Test
    void testGetNextActionCharacterKeys() throws IOException {
        // Simula entrada do usuário: tecla 'W'
        KeyStroke keyStroke = new KeyStroke('w', false, false);
        when(screenMock.readInput()).thenReturn(keyStroke);

        // Verifica se a ação corresponde ao esperado
        assertEquals(GUI.ACTION.UP, lanternaGUI.getNextAction());
    }

    @Test
    void testGetNextActionEscape() throws IOException {
        // Simula entrada do usuário: tecla Escape
        KeyStroke keyStroke = new KeyStroke(KeyType.Escape, false, false);
        when(screenMock.readInput()).thenReturn(keyStroke);

        // Verifica se a ação corresponde ao esperado
        assertEquals(GUI.ACTION.ESC, lanternaGUI.getNextAction());
    }

    @Test
    void testGetNextActionNone() throws IOException {
        // Simula ausência de entrada
        when(screenMock.readInput()).thenReturn(null);

        // Verifica se a ação é NONE
        assertEquals(GUI.ACTION.NONE, lanternaGUI.getNextAction());
    }
}

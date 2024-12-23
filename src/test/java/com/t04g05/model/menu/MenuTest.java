package com.t04g05.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testInitialMode() {
        assertEquals(Menu.Mode.MAIN_MENU, menu.getMode());
    }

    @Test
    public void testInitialOption() {
        assertEquals(0, menu.getCurrentOption());
        assertEquals("Start Game", menu.getSelectedOption());
    }

    @Test
    public void testNavigationMainMenu() {
        menu.nextOption();
        assertEquals("Instructions", menu.getSelectedOption());

        menu.nextOption();
        assertEquals("Exit", menu.getSelectedOption());

        menu.nextOption();
        assertEquals("Start Game", menu.getSelectedOption()); // Ciclo

        menu.previousOption();
        assertEquals("Exit", menu.getSelectedOption());
    }

    @Test
    public void testStartGameSelected() {
        assertTrue(menu.isStartGameSelected());
        menu.nextOption();
        assertFalse(menu.isStartGameSelected());
    }

    @Test
    public void testInstructionsSelected() {
        menu.nextOption();
        assertTrue(menu.isInstructionsSelected());
        menu.nextOption();
        assertFalse(menu.isInstructionsSelected());
    }

    @Test
    public void testExitSelected() {
        menu.nextOption();
        menu.nextOption();
        assertTrue(menu.isExitSelected());
        menu.previousOption();
        assertFalse(menu.isExitSelected());
    }

    @Test
    public void testModeSwitching() {
        menu.setMode(Menu.Mode.INSTRUCTIONS);
        assertEquals(Menu.Mode.INSTRUCTIONS, menu.getMode());
        assertEquals(0, menu.getCurrentOption());
        assertEquals("Back", menu.getSelectedOption());

        menu.setMode(Menu.Mode.MAIN_MENU);
        assertEquals(Menu.Mode.MAIN_MENU, menu.getMode());
        assertEquals(0, menu.getCurrentOption());
        assertEquals("Start Game", menu.getSelectedOption());
    }

    @Test
    public void testBackSelected() {
        menu.setMode(Menu.Mode.INSTRUCTIONS);
        assertTrue(menu.isBackSelected());
        menu.nextOption(); // Testa o ciclo
        assertTrue(menu.isBackSelected());
    }

    @Test
    public void testCurrentOptions() {
        List<String> mainMenuOptions = menu.getCurrentOptions();
        assertEquals(3, mainMenuOptions.size());
        assertTrue(mainMenuOptions.contains("Start Game"));
        assertTrue(mainMenuOptions.contains("Instructions"));
        assertTrue(mainMenuOptions.contains("Exit"));

        menu.setMode(Menu.Mode.INSTRUCTIONS);
        List<String> instructionsOptions = menu.getCurrentOptions();
        assertEquals(1, instructionsOptions.size());
        assertTrue(instructionsOptions.contains("Back"));
    }
}

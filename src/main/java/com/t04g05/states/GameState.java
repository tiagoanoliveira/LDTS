    package com.t04g05.states;

    import com.t04g05.gui.GUI;
    import com.t04g05.model.game.arena.Arena;

    import java.io.IOException;

    public abstract class GameState {
        private GameState nextState;

        public abstract void step(GUI gui, GUI.ACTION action) throws IOException;

        public void setNextState(GameState nextState) {
            this.nextState = nextState;
        }

        public GameState getNextState() {
            return nextState;
        }

        public abstract Arena getArena();
    }

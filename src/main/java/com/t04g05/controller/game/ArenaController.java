    package com.t04g05.controller.game;

    import com.t04g05.gui.GUI;
    import com.t04g05.model.game.arena.Arena;

    public class ArenaController {
        private final Arena arena;
        private final EnemyController enemyController;
        private final CharacterController characterController;

        public ArenaController(Arena arena) {
            this.arena = arena;
            this.enemyController = new EnemyController(arena);
            this.characterController = new CharacterController(arena);
        }
        public Arena getArena() {
            return arena;
        }

        public boolean isGoalReached() {
            if (characterController.checkGoal()) {
                return true;
            };
            return false; // Continua o jogo
        }
        public void processInput(GUI.ACTION action) {
            characterController.processInput(action);
        }

        public void update() {
            enemyController.updateEnemies();
            arena.checkCollisions();
            characterController.updateCharacter();  // Atualiza o personagem
            characterController.checkCollisions();
            if (arena.getCharacter().getLives() <= 0) {
                //Transição para janela de Gameover
            }
        }
    }


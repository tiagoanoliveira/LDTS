
## LDTS - T04_G05

> This project was made by Beatriz Bastos [(202303793)](https://sigarra.up.pt/feup/pt/fest_geral.cursos_list?pv_num_unico=202303793), Luís Ferreira [(202208429)](https://sigarra.up.pt/feup/pt/fest_geral.cursos_list?pv_num_unico=202208429) and Tiago Oliveira [(2020207448)](https://sigarra.up.pt/feup/pt/fest_geral.cursos_list?pv_num_unico=202007448).

# Heroman and the Dungeon of Disgrace
### DESCRIPTION

In this thrilling yet simple game, a hero tries to escape a mysterious and dangerous dungeon filled with all kinds of wild
creatures and traps, while collecting coins which makes his adventure even more interesting. To survive each different dungeon, he has to find the exit without getting killed.

### FEATURES

- **Game Execution**
    - **Start Game** - Upon selection, begins a new playthrough.

- **Hero**
    - **Movement** - The movement of the hero is defined by the arrows (up, down, left, right). Also when the hero touches a wall his movement his blocked.
    - **Progression** - To complete a level, the Heroman has to reach the final door to progress to a different level.

- **Menu**
  - **Exit** - A simple button to exit the game.
  - **Instructions** - By selecting the option "Instructions" shows the users everything he needs to know to play the game.

- **Extras**
  - **Score** - The score depends on the level reached and the amount of coins collected across the levels.
  - **HP** - When the Heroman touches a wild creature his HP goes down by one. If it eventually reaches 0, its game over.
  - **Getting hidden coins** - When the Heroman touches one of the coins available per level he collects it and his score goes up.

### MODEL

![Game Image 1](resources/GameScreenshots/LDTSgame1.jpg)


### DOCUMENTATION
#### **Implementation**
The following classes are the ones we implemented to base our project.

- **Main** - It's the starting point of the game. Creates the window for the visualization of the game and initializes the menu. 

- **Arena**
    - **Arena1, Arena2, Arena3 and Arena4** - Represent the game levels and contain the initialization of the elements of each level, 
  such as walls, enemies and coins. Control the rendering of each element and the conditions when the player finishes or loses a level.

- **Elements**
    - **Element** - Superclass for other classes like Walls, Obstacle and Enemy because it provides mutual attributes and methods like Position and Draw.

    - **Walls** - Represents the level limits.

    - **Enemy** - Represents the enemies present throughout the level that the player has to avoid.

    - **Coin** - Represents the elements the player has to collect.

    - **Character** - Represents the player himself.

- **Menu**
  - **Menu** - Represents the main menu and the necessary methods to implement it.

  - **Instructions** - Represents the instructions screen and has the respective methods.

- **Controller**
  - **ArenaController** - Controls every possible state for the level. Processes the player input to change his character's 
  position and verifies if the game is over.

  - **CharacterController, EnemyController** - Process the corresponding movement and checks if those movements are valid.

  - **MenuController, InstructionsController** - Process what happens according to which keys the user presses.

- **States**
  - **MenuState** - Responsible for controlling the menu interface state and handling the user's actions inside the menu.

  - **InstructionsState** - Responsible for controlling the instructions interface state and handling the user's actions inside the instructions screen.

  - **LevelsState** - Responsible for controlling the levels interface state and handling the user's actions inside each level or if he wants to quit.

- **Viewer**
    - **ArenaViewer** - Responsible for the instantiation of the draw methods for each element in an arena.

    - **CharacterViewer, CoinViewer, EnemyViewer, WallsViewer** - Responsible for instantiating the draw methods for the 
  corresponding elements (player, coin, enemy and walls).

    - **MenuViewer, InstructionsViewer** - Responsible for the drawing of the corresponding text and options.

#### **The Patterns**

1. **Singleton Pattern** with Main and GUI

2. **State Pattern** in states directory

3. **MVC Pattern** with game, controller and viewer directories

4. **Template Method Pattern** in Arena creation

#### **Consequences**

The use of the **Singleton Pattern** in the current design allows the following benefits:

  - Allows a single point of the initialization of the game and allows a single method to call every other method needed
  to run the game;
  - Ensures the GUI instance is universally accesible throughout the game, avoiding redundant instances;

The use of the **State Pattern** in the current design allows the following benefits:

  - Encapsulation of the state logic: Each state encapsulates its specific behaviour, making the overall state cleaner and easier to understand;
  - Allows the game to transition between states dynamically;
  - Makes the code easier to understand because instead of having a switch-case block in GameController, each state defines its behavior.

The use of the **MVC Pattern** in the current design allows the following benefits:

  - 

The use of the **Template Method Pattern** in the current design allows the following benefits:

  - Enforces a consistent structure for the program across subclasses. Even though there are difference, the overall sequence is the same;
  - Centralizing the common step in the parent class, this pattern reduces the duplicate code across the subclasses;
  - 

Some of the mentioned design patterns used are represented in the following UML:

![LDTSuml](resources/UML/UMLldts.jpg)

### CODE 
#### **Smells**
In an overall overview, the game code has some smells that influence the code perception and understanding for someone who is looking
at it for the first time. This means it could be better organized and less confusing.

#### **Coverage Report**
### TESTING

The following screenshots show the tests implemented until this moment:

![Testing Directions](resources/TestingScreenshots/testing1.jpg)

![Testing Movement stop by Obstacle](resources/TestingScreenshots/testing2.jpg)

![Testing normal Movement](resources/TestingScreenshots/testing3.jpg)

![Goal Notification](resources/TestingScreenshots/testing4.jpg)

![Testing Level Execution](resources/TestingScreenshots/testing5.jpg)

![Testing Endgame on Enemy Collision](resources/TestingScreenshots/testing6.jpg)

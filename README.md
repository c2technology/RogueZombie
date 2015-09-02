RogueZombie

This game is an exercise in game development. Originally, this was part of an
interview process but has and will become much more as I learn new things about
game development. Thank you for taking a look!

This application uses Apache Maven with local libraries not normally available
in the public repositories. In order to compile, you must first issue a 
`mvn clean` command before compiling. If you are using the Netbeans IDE, you 
MUST issue "Clean" and not "Clean and Build" before this application will 
compile.

The objective of this first release is to rescue the President from the Zombie
invasion of DC and escape alive. I tried to develop the world in a way that
mimicks a city and not a typical "dungeon." As such, there are no doors 
(although I do plan on widening the streets and adding cars!). There are no
other items in this world aside from the President. I am aware that the 
President should probably be another creature in the field and this is planned
on being so in the future. For now, the President is an item you can pick up
without any modifications to the Player aside from meeting the win condition.

There are a few keys I hope are adequately explained in the user interface. For
posterity, they are listed below with some undocumented keys to help anyone who
doesn't have the time to accomplish (or fail) the objective.

Keys:
    North West        : 7        
    North             : Up or 8
    North East        : 9
    West              : Left or 6
    East              : Right
    South West        : 1
    South             : Down or 2
    South East        : 3
    Toggle Fog of War : F
    Automatic Win     : W
    Automatic Lose    : Q
	
Movement:
  You can move in any of the 8 cardinal directions as described above. This
  means that any dead end that is diagonally connected to another empty space
  has an available space for movement.

Interactions:
  You can do one of 3 things on your turn:
    1. Move
      This is explained in the Movement section. Moving into an enemy will
      attack it. Attempting to move into a wall will prevent movement and cost
      a turn. Moving onto the exist (the Helicopter) will check win conditions.
    2. Attack
      Moving into another creature will attempt an attack. Creatures move after
      the Player and may result in the creature attacking you in return if the
      Creature is not dead.
    3. Pick up item ([Enter])
      You can pick up any object you are standing on. If you attempt to pick up
      something in an empty space, nothing will happen and it will cost a turn.
 
Additional Actions:
  
  Fog of War
    As the Player moves about the map, the explored areas will be mapped by the 
    Player. Only the Tile will be mapped and creatures or Items will not. You
    can toggle this setting without costing a turn by pressing "F"

  Automatic Win
    This is not documented in the user interface. Pressing this will 
    automatically win the game and present the user with the winning screen.

  Automatic Loss
    This is not documented in the user interface. Pressing this will
    automatically lose the game and present the user with the losing screen.
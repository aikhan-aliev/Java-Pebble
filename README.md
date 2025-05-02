# Java-Pebble

Pebble is a two-player game, played on a board consists of n x n fields. Initially, n
white and n black pebbles are placed on the board randomly. Each color belongs to
only one player. The players take turns choosing one of its pebble, and then move
it horizontally or vertically. The movement also affects the neighbouring pebbles
in the direction (the pebble on the edge falls off). The objective of the game is to
push out as much pebbles of the opponent from the board as we can, within a given
number of turns (5n). A player wins, if he has more pebbles on the board at the end
than his opponent. The game is draw, if they have the same number of pebbles on
the board.
Implement this game, and let the board size be selectable (3x3, 4x4, 6x6 → turns
are 15, 20, 30). The game should recognize if it is ended, and it has to show the
name of the winner in a message box (if the game is not ended with draw), and
automatically begin a new game.

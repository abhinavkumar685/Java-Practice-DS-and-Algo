package SnakeLadderGame;

import SnakeLadderGame.models.Game;
import SnakeLadderGame.models.Player;

public class SnakeLadderApplication {
    public static void main(String[] args) {
        int boardSize = 100;
        int numberOfSnakes = 10;
        int numberOfLadders = 10;
        int numberOfPlayers = 2;

        Game game = new Game(numberOfSnakes, numberOfLadders, boardSize);
        for(int i=1; i<=numberOfPlayers; i++) {
            Player player = new Player("Player" + i);
            game.addPlayer(player);
        }

        game.playGame();
    }
}

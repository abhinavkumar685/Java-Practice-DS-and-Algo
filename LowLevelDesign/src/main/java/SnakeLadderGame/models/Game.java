package SnakeLadderGame.models;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private final int numberOfSnakes;
    private final int numberOfLadders;

    private final Queue<Player> players;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    private final Board board;
    private final Dice dice;

    public Game(int numberOfSnakes, int numberOfLadders, int boardSize) {
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfLadders = numberOfLadders;
        board = new Board(boardSize);
        players = new ArrayDeque<>();
        snakes = new ArrayList<>(numberOfSnakes);
        ladders = new ArrayList<>(numberOfLadders);
        dice = new Dice();
        initBoard();
    }

    private void initBoard() {
        Set<String> snakeLadderSet = new HashSet<>();

        for(int i=0; i<numberOfSnakes; i++) {
            while(true) {
                int snakeStart = ThreadLocalRandom.current().nextInt(board.getStart(), board.getEnd());
                int snakeEnd = ThreadLocalRandom.current().nextInt(board.getStart(), board.getEnd());
                if(snakeEnd >= snakeStart){
                    continue;
                }
                String startEndPair = String.valueOf(snakeStart) + snakeEnd;
                if(!snakeLadderSet.contains(startEndPair)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakes.add(snake);
                    snakeLadderSet.add(startEndPair);
                    break;
                }
            }
        }

        for(int i=0; i<numberOfLadders; i++) {
            while(true) {
                int ladderStart = ThreadLocalRandom.current().nextInt(board.getStart(), board.getEnd());
                int ladderEnd = ThreadLocalRandom.current().nextInt(board.getStart(), board.getEnd());
                if(ladderEnd <= ladderStart){
                    continue;
                }
                String startEndPair = String.valueOf(ladderEnd) + ladderStart;
                if(!snakeLadderSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.add(ladder);
                    snakeLadderSet.add(startEndPair);
                    break;
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while(true) {
            Player player = players.poll();
            int val = dice.roll();
            int newPosition = player.getPosition() + val;

            if(newPosition > board.getEnd()) {
                player.setPosition(player.getPosition());
                players.offer(player);
            }
            else {
                int finalPosition = getNewPosition(player, newPosition);
                player.setPosition(finalPosition);
                if(player.getPosition() == board.getEnd()) {
                    player.setWon(true);
                    System.out.println("Player " + player.getName() + " won!!");
                }
                else {
                    System.out.println("Setting " + player.getName() +
                            " position to " + finalPosition);
                    players.offer(player);
                }
            }

            if(players.size() < 2) {
                System.out.println("Game Over!!!!!!");
                break;
            }
        }
    }

    public int getNewPosition(Player player, int newPosition) {
        for(Snake snake : snakes) {
            if(snake.getHead() == newPosition) {
                System.out.println(player.getName() + " bit by snake at " + newPosition);
                newPosition = snake.getTail();
                break;
            }
        }

        for(Ladder ladder : ladders) {
            if(ladder.getStart() == newPosition) {
                System.out.println(player.getName() + " climbed ladder at " + newPosition);
                newPosition = ladder.getEnd();
                break;
            }
        }
        return newPosition;
    }
}

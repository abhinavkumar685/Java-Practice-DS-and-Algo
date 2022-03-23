package SnakeLadderGame.models;

public class Player {
    private final String name;

    private int position;
    private boolean won;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.won = false;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWon() {
        return won;
    }
}

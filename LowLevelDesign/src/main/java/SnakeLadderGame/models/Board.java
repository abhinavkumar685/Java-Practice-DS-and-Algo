package SnakeLadderGame.models;

public class Board {
    int size;
    int start;
    int end;

    public Board(int size) {
        this.start = 1;
        this.size = size;
        this.end = this.size + start - 1;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

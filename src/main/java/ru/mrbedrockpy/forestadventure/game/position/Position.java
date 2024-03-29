package ru.mrbedrockpy.forestadventure.game.position;

public class Position {

    private int x;

    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position position) {
        if (this.getX() == position.getX() && this.getY() == position.getY()) {
            return true;
        }
        return false;
    }

    public Position clone(){
        return new Position(this.x, this.y);
    }
}

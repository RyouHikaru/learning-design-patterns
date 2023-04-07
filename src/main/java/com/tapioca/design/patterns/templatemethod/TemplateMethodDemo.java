package com.tapioca.design.patterns.templatemethod;

/**
 * Template Method - allows us to define the skeleton of the algorithm,
 * with concrete implementations defined in subclasses.
 */
abstract class Game {
    protected int currentPlayer;
    protected final int numberOfPlayer;

    protected abstract int getWinningPlayer();
    protected abstract void takeTurn();
    protected abstract boolean haveWinner();
    protected abstract void start();

    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    // This contains the skeleton of the algorithm
    public void run() {
        start();
        while(!haveWinner())
            takeTurn();
        System.out.println("Player " + getWinningPlayer() + " wins");
    }
}

class Chess extends Game {
    private int maxTurns = 10;
    private int turn = 1;

    public Chess() {
        super(2);
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % numberOfPlayer;
    }

    @Override
    protected boolean haveWinner() {
        return turn == maxTurns;
    }

    @Override
    protected void start() {
        System.out.println("Starting a game of chess");
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        new Chess().run();
    }
}

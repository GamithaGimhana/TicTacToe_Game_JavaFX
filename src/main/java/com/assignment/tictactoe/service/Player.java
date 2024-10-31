package com.assignment.tictactoe.service;

public abstract class Player {
    protected BoardImpl board;

    public Player(BoardImpl board) {
        this.board = board;
    }

    public abstract boolean move(int row, int col);
//    public abstract int[] getMovements();
}

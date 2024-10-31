package com.assignment.tictactoe.service;

import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner input = new Scanner(System.in);

    Piece mark = Piece.X;

    public HumanPlayer(BoardImpl board) {
        super(board);
    }

    @Override
    public boolean move(int row, int col) {
        if (board.isLegalMove(row, col)) {
            board.updateMove(row, col, mark);
            return true;
        }
        return false;
    }

//    This code is made for to play using the random AI generator in terminal ↓

/*    public int[] getMovements() {
        int row, col;
        do {
            System.out.println("Enter row and column (0, 1, or 2): "); // get human player's movements
            row = input.nextInt();
            col = input.nextInt();
        } while (!board.isLegalMove(row, col));

        return new int[]{row, col};
    } */

}

package com.assignment.tictactoe.service;

import java.util.Random;

public class AiPlayer extends Player {
    Random random = new Random();

    Piece mark = Piece.O;

    public AiPlayer (BoardImpl board) {
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
            row = random.nextInt(3);
            col = random.nextInt(3); // get ai player's movements
        } while (!board.isLegalMove(row, col));

        return new int[]{row, col};
    } */

    public void findBestMove() {
        int bestValue = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        Piece[][] pieces = board.getBoard();
        Random rand = new Random();

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    pieces[i][j] = Piece.O;
                    int moveValue = minimax(pieces, 0, false); // Move using minimax

                    // Add randomness to AI's decision to make it more human-like
                    if (rand.nextInt(10) < 2) { // 20% chance to pick this move
                        moveValue -= 5; // Decrease the move value
                    }
                    pieces[i][j] = Piece.EMPTY;

                    // If the move value is better than the best value, update bestRow and bestCol
                    if (moveValue > bestValue) {
                        bestRow = i;
                        bestCol = j;
                        bestValue = moveValue;
                    }
                }
            }
        }

        // Make the best move
        if (bestRow != -1 && bestCol != -1) {
            move(bestRow, bestCol);
        }
    }

    // Minimax algorithm to evaluate the board state
    private int minimax(Piece[][] pieces, int depth, boolean isMaximizing) {
        Winner winner = board.checkWinner(); // Check if there's a winner
        if (winner != null) {
            if (winner.getWinningPiece() == Piece.O) { // AI wins
                return 10 - depth; // Return score based on depth
            } else if (winner.getWinningPiece() == Piece.X) { // Human wins
                return depth - 10; // Return score based on depth
            }
        }

        if (board.checkDraw()) { // Check if it's a draw
            return 0; // Return score for draw
        }

        if (isMaximizing) { // If it's the AI's turn
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.O;
                        bestValue = Math.max(bestValue, minimax(pieces, depth + 1, false)); // Evaluate the move
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue; // Return the best value for the AI
        } else { // If it's the human's turn
            int bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.X;
                        bestValue = Math.min(bestValue, minimax(pieces, depth + 1, true)); // Evaluate the move
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue; // Return the best value for the human
        }
    }


}

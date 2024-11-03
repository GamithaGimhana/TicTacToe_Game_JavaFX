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
        int bestValue = Integer.MIN_VALUE; // best score ekata danata podima agaya dagannava
        int bestRow = -1;
        int bestCol = -1;
        Piece[][] pieces = board.getBoard();

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    pieces[i][j] = Piece.O; // piece eke his thanak balala ekata O dagannava
                    int moveValue = minimax(pieces, 0, false); // minimax eka call karala score ekak illaganava

                    pieces[i][j] = Piece.EMPTY; // dapu piece eka ain karanava

                    // best score ekata adala best move eke row ekai col ekata dagannava
                    if (moveValue > bestValue) {
                        bestRow = i;
                        bestCol = j;
                        bestValue = moveValue; // gatta score eka danata thiyene ekata wada wadi nan eka best score ekata dagannava
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
                return 10 - depth; // ai eka depth base unu score ek return krl puluwan tharam ikmanata dinanna balanava
            } else if (winner.getWinningPiece() == Piece.X) { // Human wins
                return depth - 10; // ai eka depth base unu score ek return krl puluwan tharam pahuwela paradenna balanava
            }
        }

        if (board.checkDraw()) { // Check if it's a draw
            return 0; // Return score for draw
        }

        if (isMaximizing) { // ai eke dinana chance eka maximize karanava
            int bestValue = Integer.MIN_VALUE; // hodama score ekata danata aduma value eka danava
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.O; // board eke piece eka empty nan ekata danata O danava
                        bestValue = Math.max(bestValue, minimax(pieces, depth + 1, false));  // minimax eka call karala depth ekak wadi karala eelaga recursion eke humange dinana chance eka minimize karanava
                        pieces[i][j] = Piece.EMPTY; // board eke daapu piece eka ain karanava
                    }
                }
            }
            return bestValue; // ai ekt hodama score eka return karanava
        } else { // dan karanne human ge dinana chance eka adu karana eka
            int bestValue = Integer.MAX_VALUE; // adu karaddi aduma score eka daganna one hinda danata wadima score eka danava
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.X; // kalin wage piece eka empty nan X dagannava
                        bestValue = Math.min(bestValue, minimax(pieces, depth + 1, true)); // minimax ekata call karala ai depth eka ekakin wadi karala eelaga recursion eke ai ge dinana chance eka maximize karanava
                        pieces[i][j] = Piece.EMPTY; // ai dagatta piece eka ain karanava
                    }
                }
            }
            return bestValue; // dagatta score eka return karanava
        }
    }


}

package com.assignment.tictactoe.service;

public class BoardImpl implements Board {
    private Piece[][] board = new Piece[3][3]; // 3x3 game board

    public BoardImpl() {
        initializeBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    @Override
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = Piece.EMPTY; // initialize board with empty boxes
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        if(row >=0 && row <=2 && col >=0 && col <=2){
            if(board[row][col] == Piece.EMPTY){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            board[row][col] = piece; // place a piece
        }
    }

    @Override
    public Winner checkWinner() {
        for (int i = 0; i <=2 ; i++) {
            if (board[0][i] != Piece.EMPTY &&board[0][i] == board[1][i] && board[1][i] == board[2][i]) { // check the column win
                return new Winner(board[0][i], 0, i, 1, i, 2, i); // return wining piece
            }
            if (board[i][0] != Piece.EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) { // check the row win
                return new Winner(board[i][0], i, 0, i, 1, i, 2); // return wining piece
            }
        }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != Piece.EMPTY) {
            return new Winner(board[0][0], 0, 0, 1, 1, 2, 2);
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != Piece.EMPTY) {
            return new Winner(board[0][2], 0, 2, 1, 1, 2, 0);
        }
        return null;
    }

    @Override
    public void printBoard() {
        System.out.println("-------------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------------------");
        }
    }

    public boolean checkDraw(){
        for (int i = 0; i <=2; i++) {
            for (int j = 0; j <=2; j++) {
                if (board[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

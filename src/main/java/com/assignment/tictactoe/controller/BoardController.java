package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class BoardController implements BoardUI {
    BoardImpl board;
    AiPlayer ai;
    HumanPlayer human;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public BoardController() {
        board = new BoardImpl();
        ai = new AiPlayer(board);
        human = new HumanPlayer(board);

    }

    @FXML
    private AnchorPane ancBoard;

    @FXML
    private Button id00;

    @FXML
    private Button id01;

    @FXML
    private Button id02;

    @FXML
    private Button id10;

    @FXML
    private Button id11;

    @FXML
    private Button id12;

    @FXML
    private Button id20;

    @FXML
    private Button id21;

    @FXML
    private Button id22;

    @FXML
    private GridPane gridBoard;

    @FXML
    private Button btnReset;

    @FXML
    private Label lblWinner;

    @FXML
    public void initialize() {
        lblWinner.setDisable(true);
        btnReset.setDisable(true);
    }

    @FXML
    void onClickGridBtn(ActionEvent event) {
        Button button = (Button) event.getSource();
        int row = Integer.parseInt(button.getId().split("")[2]);
        int col = Integer.parseInt(button.getId().split("")[3]);

        if (human.move(row, col)) {
            System.out.println("Human moves");
            board.printBoard();
            updateUi();
            if (checkForEndGame()) return;

            ai.findBestMove();
            System.out.println("AI moves");
            board.printBoard();
            updateUi();
            checkForEndGame();
        }
    }

    private boolean checkForEndGame() {
        // Check if there's a winner
        Winner winner = board.checkWinner();
        if (winner != null) {
            NotifyWinner(winner.getWinningPiece());
            disableGame();
            return true;
        }
        // Check if the game is a draw
        if (board.checkDraw()) {
            displayDraw();
            return true;
        }
        return false;
    }

    private void disableGame() {
        btnReset.setDisable(false);
        gridBoard.setDisable(true);
    }

    private void displayDraw() {
        System.out.println(YELLOW + "Match Tie!!!" + RESET);
        lblWinner.setText("Match Tie!!!");
        lblWinner.setDisable(false);
        disableGame();
    }

    public void updateUi() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                update(i, j, board.getBoard()[i][j]);
            }
        }
    }

    @Override
    public void update(int row, int col, Piece piece) {
        String text = "";
        if (piece == Piece.X) {
            text = "X";
        } else if (piece == Piece.O) {
            text = "O";
        }

        if (row == 0 && col == 0) {
            id00.setText(text);
        } else if (row == 0 && col == 1) {
            id01.setText(text);
        } else if (row == 0 && col == 2) {
            id02.setText(text);
        } else if (row == 1 && col == 0) {
            id10.setText(text);
        } else if (row == 1 && col == 1) {
            id11.setText(text);
        } else if (row == 1 && col == 2) {
            id12.setText(text);
        } else if (row == 2 && col == 0) {
            id20.setText(text);
        } else if (row == 2 && col == 1) {
            id21.setText(text);
        } else if (row == 2 && col == 2) {
            id22.setText(text);
        }
    }

    @Override
    public void NotifyWinner(Piece winner) {
        if (winner == Piece.X) {
            System.out.println(GREEN + "Human (X) wins" + RESET);
            disableGame();
            lblWinner.setText("You (X) wins");
            lblWinner.setDisable(false);
        } else if (winner == Piece.O) {
            System.out.println(RED + "AI (O) wins" + RESET);
            disableGame();
            lblWinner.setText("AI (O) wins");
            lblWinner.setDisable(false);
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        board.initializeBoard();
        updateUi();

        lblWinner.setText("");
        lblWinner.setDisable(true);
        btnReset.setDisable(true);
        gridBoard.setDisable(false);
    }

}

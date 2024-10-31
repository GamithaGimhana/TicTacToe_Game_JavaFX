package com.assignment.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

//    This code is made for to play using the random AI generator in terminal ↓

/*    public static void main(String[] args) {
        BoardImpl board = new BoardImpl(); // create the game board
        HumanPlayer human = new HumanPlayer(board); // initialize human player
        AiPlayer ai = new AiPlayer(board); // initialize AI player

        System.out.println("Welcome to Tic-Tac-Toe with Ai player! Let’s start...");

        Player cp;
        cp = human;

        boolean gameOn = true;

        while (gameOn) {
            board.printBoard();

            if (cp == human) {
                System.out.println("Human's Turn:");
            } else if (cp == ai) {
                System.out.println("AI's Turn:");
            }

            int[] moves = cp.getMovements(); // get player's movements

            int row = moves[0];
            int col = moves[1];

            cp.move(row, col);

            board.printBoard();

            if (board.checkWinner() != null) { // check winner
                if (cp == human) {
                    System.out.println("Human wins!");
                } else if (cp == ai) {
                    System.out.println("AI wins!");
                }
                break;
            } else if (board.checkDraw()) { // check for a draw
                System.out.println("Game is Draw!");
                break;
            } else {
                if (cp == human) {
                    cp = ai; // change the player
                } else{
                    cp = human;
                }
            }
        }
    }   */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/BoardView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tic Tac Toe");

        Image image = new Image(getClass().getResourceAsStream("/images/icon.png"));
        stage.getIcons().add(image);

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}

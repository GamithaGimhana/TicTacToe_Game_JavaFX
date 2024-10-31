module com.assignment.tictactoe.service {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.assignment.tictactoe.controller to javafx.fxml;
    exports com.assignment.tictactoe;
}
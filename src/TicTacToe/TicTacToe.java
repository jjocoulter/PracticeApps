package TicTacToe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Attempting to make a functional tic-tac-toe game.
 */
public class TicTacToe extends Application {
    private static int BOARDSIZE = 3;
    private static int TILESIZE = 50;
    private static int PADDING = 2;
    private static int SCREENWIDTH = 600;
    private static int SCREENHEIGHT = 600;

    public AnchorPane paneMain;
    public Label lblWinner;
    public Button btnReset;

    private Button[][] board;
    private int turnCount = 0;


    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public void initialize() {
        setBoard();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void SelectSquare(Button button) {
        String selectedSquare = button.getId();
        System.out.println(selectedSquare);
        if ((turnCount % 2) == 0) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        turnCount++;
        button.setDisable(true);
        CheckForWinner();
    }

    private void setBoard() {
        int spacing = TILESIZE + PADDING;
        int hMargin = (SCREENHEIGHT - (spacing * BOARDSIZE) - PADDING) / 2;
        int vMargin = (SCREENWIDTH - (spacing * BOARDSIZE) - PADDING) / 2;

        board = new Button[BOARDSIZE][BOARDSIZE];

        for (int c = 0; c < BOARDSIZE; c++) {
            for (int r = 0; r < BOARDSIZE; r++) {
                final int column = c;
                final int row = r;
                board[column][row] = new Button();
                board[column][row].setPrefSize(50, 50);
                int id = column + (row * 3) + 1;
                board[column][row].setId(Integer.toString(id));
                board[column][row].setText(Integer.toString(id));
                board[column][row].setOnMouseClicked(event -> SelectSquare(board[column][row]));
                paneMain.getChildren().add(board[column][row]);
                AnchorPane.setLeftAnchor(board[column][row], (double) (vMargin + (column * spacing)));
                AnchorPane.setTopAnchor(board[column][row], (double) (hMargin + (row * spacing)));
            }
        }
    }

    private void CheckForWinner(){
        if (turnCount == 9){
            lblWinner.setText("Game Tied. There is no winner.");
        }
        //Check columns and rows for a winner.
        for (int i = 0; i < 3; i++){
            if (board[i][0].getText().equals(board[i][1].getText()) && board[i][0].getText().equals(board[i][2].getText())){
                lblWinner.setText("Winner is " + board[i][0].getText() + " in " + turnCount + " turns.");
                EndGame();
                break;
            } else if (board[0][i].getText().equals(board[1][i].getText()) && board[0][i].getText().equals(board[2][i].getText())){
                lblWinner.setText("Winner is " + board[0][i].getText() + " in " + turnCount + " turns.");
                EndGame();
                break;
            }
        }
        //Check diagonals for a winner.
        if (board[0][0].getText().equals(board[1][1].getText()) && board[0][0].getText().equals(board[2][2].getText())){
            lblWinner.setText("Winner is " + board[0][0].getText() + " in " + turnCount + " turns.");
            EndGame();
        } else if (board[2][0].getText().equals(board[1][1].getText()) && board[2][0].getText().equals(board[0][2].getText())){
            lblWinner.setText("Winner is " + board[2][0].getText() + " in " + turnCount + " turns.");
            EndGame();
        }
    }

    private void EndGame(){
        for (int c = 0; c < BOARDSIZE; c++) {
            for (int r = 0; r < BOARDSIZE; r++) {
                board[c][r].setDisable(true);
            }
        }
    }

    public void ResetGame(ActionEvent actionEvent) {
        for (int c = 0; c < BOARDSIZE; c++) {
            for (int r = 0; r < BOARDSIZE; r++) {
                paneMain.getChildren().remove(board[c][r]);
            }
        }
        turnCount = 0;
        lblWinner.setText("");
        setBoard();
    }

//    private void ComputerTurn(){
//        Random random = new Random();
//        while (true){
//            int randomColumn = random.nextInt(3);
//            int randomRow = random.nextInt(3);
//            if (!board[randomColumn][randomRow].isDisabled()){
//                System.out.println(board[randomColumn][randomRow].getId());
//                board[randomColumn][randomRow].setText("O");
//                board[randomColumn][randomRow].setDisable(true);
//                break;
//            }
//
//        }
//    }
}

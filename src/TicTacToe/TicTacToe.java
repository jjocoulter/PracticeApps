package TicTacToe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Attempting to make a functional tic-tac-toe game.
 */
public class TicTacToe extends Application{
    private static int BOARDSIZE = 3;
    private static int TILESIZE = 50;
    private static int PADDING = 2;
    private static int SCREENWIDTH = 600;
    private static int SCREENHEIGHT = 600;

    public AnchorPane paneMain;

    private Button[][] board = new Button[BOARDSIZE][BOARDSIZE];



    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public void initialize(){
        setBoard();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void GetSelectSquare(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        String selectedSquare = node.getId();
        System.out.println(selectedSquare);
    }

    private void setBoard(){
        int spacing = TILESIZE + PADDING;
        int hMargin = (SCREENHEIGHT - (spacing * BOARDSIZE) - PADDING) / 2;
        int vMargin = (SCREENWIDTH - (spacing * BOARDSIZE) - PADDING) / 2;


        for (int c = 0; c < BOARDSIZE; c++){
            for(int r = 0; r < BOARDSIZE; r++){
                board[c][r] = new Button();
                board[c][r].setPrefSize(50, 50);
                paneMain.getChildren().add(board[c][r]);
                AnchorPane.setLeftAnchor(board[c][r], (double) (vMargin + (c * spacing)));
                AnchorPane.setTopAnchor(board[c][r], (double) (hMargin + (r * spacing)));
            }
        }
    }
}

package esalaf.projet11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.*;


public class HelloController implements Initializable {

    @FXML
    private Button btn_login;

    @FXML
    private Button close;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    // DATABASE TOOLS
    private Connection conn;
    private PreparedStatement prepare;
    private ResultSet result;
    private double x=0;
    private double y=0;

    public void login(){
        String sql = "SELECT * FROM admin WHERE username =? and password=?";
        conn = Database.connectDb();
        Alert alert;

        try {
            if (txt_username.getText().isEmpty() || txt_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all BLANK");
                alert.showAndWait();
            }
            else {
                prepare = conn.prepareStatement(sql);
                prepare.setString(1,txt_username.getText());
                prepare.setString(2,txt_password.getText());

                result = prepare.executeQuery();
                if(result.next()){
                    // THEN WE WILL PROCEED TO DASHBOARD
                    GetData.username = txt_username.getText();
                    btn_login.getScene().getWindow().hide();
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login! ");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed( (MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();

                    } );

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX()-x);
                        stage.setY(event.getScreenY()-y);

                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();
                }
                else { // IF INCORRECT THE DATA YOU GAVE :
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO

    }

}
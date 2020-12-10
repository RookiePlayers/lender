package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Authentication.Logic.RegisterController;
import sample.Authentication.Model.AccountType;
import sample.Authentication.Model.Admin;
import sample.Runner.Logic.LenderController;

import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       FXMLLoader loader =new  FXMLLoader(getClass().getResource("Authentication/UI/register.fxml"));
       Parent root=loader.load();
        RegisterController rc = loader.getController();
        rc.setAccountType(AccountType.ADMIN);

        primaryStage.setTitle("Machine Lender");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));


        launch(args);
    }
}

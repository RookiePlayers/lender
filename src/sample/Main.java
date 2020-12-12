package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Authentication.Logic.FileManager;
import sample.Authentication.Logic.LoginController;
import sample.Authentication.Logic.RegisterController;
import sample.Authentication.Model.AccountType;
import sample.Authentication.Model.Admin;
import sample.Home.Logic.Home;
import sample.Runner.Logic.LenderController;

import java.io.IOException;
import java.util.Arrays;

public class Main extends Application {
    public static  Navigation currentStage;

    private FileManager io=new FileManager();
    @Override
    public void start(Stage primaryStage) throws Exception{

        currentStage= new Navigation("Machine Lender");
        getCurrentUser();
        if(Statics.CurrentUser!=null)
        currentStage.setFXMLScene("Home/UI/home.fxml",new Home());
        else
            currentStage.setFXMLScene("Authentication/UI/login.fxml",new LoginController());
        primaryStage=currentStage;
        primaryStage.show();
    }

    public void getCurrentUser(){
        Statics.CurrentUser=null;
        try {
            io.readSerializedFile("currentUser.ser");
            Statics.CurrentUser=io.users.size()>0?io.users.get(0):null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));


        launch(args);
    }
}

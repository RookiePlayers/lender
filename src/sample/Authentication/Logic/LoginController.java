package sample.Authentication.Logic;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import sample.Authentication.Model.AccountType;
import sample.Authentication.Model.User;
import sample.Main;
import sample.Runner.IAdapter;
import sample.Runner.Logic.LenderController;
import sample.Statics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LoginController implements Initializable, IAdapter {

    @FXML
    private Button loginButton, registerPageButton;
    @FXML
    private Label messager;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    String userSname, userId;


    ArrayList<User>users=new ArrayList<>();
    FileManager io=new FileManager();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void loadSceneAndSendInfo() {
        try {

            System.out.println("It's creating the new window....");

            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lender.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            LenderController lend = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            lend.setInfo(userSname, userId);
            System.out.println("The passed in info " + userSname + " " + userId);
            lend.addInv();
            lend.showInformation(userSname, userId);

            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Second Window");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public LoginController() {
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
            if (validateLogin(usernameField.getText(), passwordField.getText())) {
                messager.setText("Logged in as: "+Statics.CurrentUser);
                ArrayList<User> users= new ArrayList<>();
                users.add(Statics.CurrentUser);
                messager.setStyle("-fx-text-fill: green;");
               io.serializeToFile("currentUser.ser",users);
                Main.currentStage.setFXMLScene("Home/UI/home.fxml",new LoginController());
            } else {

            }
        } else {
            messager.setText("Please enter a Username AND Password");
            messager.setStyle("-fx-text-fill: red;");
        }
    }

    public boolean validateLogin(String username, String password) throws IOException {
        //check text file to see if the specified log in exists already
        for(User user : users){
            user.decryptPassword();
            if(username.equalsIgnoreCase(user.getUsername())&&password.equals(user.getPassword())){
                Statics.CurrentUser=user;
                return true;
            }
        }
        messager.setText("User not recognized");
        messager.setStyle("-fx-text-fill: red;");
        return false;
    }

    public void registerButtonOnAction(ActionEvent actionEvent)throws IOException {
        Main.currentStage.setFXMLScene("Authentication/UI/register.fxml",new LoginController(), AccountType.CUSTOMER);
    }

    @Override
    public void init() {
        Arrays.asList("AdminDB.ser","CustomerDB.ser").forEach(path->{
            try {
                io.readSerializedFile((String)path);
                users.addAll(io.users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void custom(Object... args) {

    }
}

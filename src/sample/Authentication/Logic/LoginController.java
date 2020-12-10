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
import sample.Runner.Logic.LenderController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LoginController implements Initializable {

    @FXML
    private Button loginButton, registerPageButton;
    @FXML
    private Label messager;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    String userSname, userId;

    ArrayList<ArrayList<String>> user = new ArrayList<ArrayList<String>>();

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
                loadSceneAndSendInfo();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            } else {
                System.out.println(user);
            }
        } else {
            messager.setText("Please enter a Username AND Password");
        }
    }

    public boolean validateLogin(String username, String password) throws IOException {
        //check text file to see if the specified log in exists already
        try {
            File myObj = new File("userDatabase.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                //split data by comma, into array
                String[] tmpUser = data.split(",");
                //convert from array to list
                List<String> tempUser = Arrays.asList(tmpUser);
                //convert from list to arraylist
                ArrayList<String> tempUsr = new ArrayList<String>(tempUser);
                user.add(tempUsr);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for(ArrayList<String> list : user){
            if(list.contains(username)) {
                if(list.get(2).equals(password)){
                    //successfully logged in
                    messager.setText("success");

                    System.out.println("logged IN!!!");

                    userSname = list.get(0);
                    userId = list.get(3);
                    //add method to send you the application
                    return true;
                }
                else
                {
                    //correct username, wrong password
                    messager.setText("password is wrong");
                    return false;
                }
            } else {
                //incorrect username, name doesnt exist
                messager.setText("No user with this username exists");
                return false;
            }
        }
        return false;
    }

    public void registerButtonOnAction(ActionEvent actionEvent) {
    }
}

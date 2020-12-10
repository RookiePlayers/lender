package sample.Authentication.Logic;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import sample.Authentication.Model.AccountType;
import sample.Authentication.Model.Admin;
import sample.Authentication.Model.User;

import java.io.*;

import java.net.URL;
import java.util.*;

public class RegisterController {

    @FXML
    private TextField firstnameField;
    @FXML
    private PasswordField regPasswordField;
    @FXML
    private TextField regUsenameField;
    @FXML
    private Button registerButton, loginButton;
    @FXML
    private Label msgr;
    ArrayList<ArrayList<String>> user = new ArrayList<ArrayList<String>>();
    User regUser;
    AccountType accountType;

    public void setAccountType(AccountType type){
        this.accountType = type;
    }

    public void registerButtonOnAction(ActionEvent event){

        if(validate(firstnameField.getText(), regUsenameField.getText(), regPasswordField.getText())) {
            //new PasswordValidator(1).Validate(regPasswordField.getText())
             if(this.accountType==AccountType.ADMIN){
                        this.registerAsAdmin();
                    }
                    else{
                        this.registerAsCustomer();
                    }
        }


//        if(!firstnameField.getText().isBlank() && !regUsenameField.getText().isBlank() && !regPasswordField.getText().isBlank()) {
//            if (validate(firstnameField.getText(), regUsenameField.getText(), regPasswordField.getText())) {
//                createUserDatabase(firstnameField.getText(), regUsenameField.getText(), regPasswordField.getText());
//            } else {
//                //make sure the fields adhere to the regex rules (add string response
//                System.out.println("It does not follow rules.");
//                msgr.setText("Username already taken");
//            }
//        } else {
//            // one or more of the fields are empty, let them know
//            System.out.println("can't be empty bro.");
//        }
    }
    private void  registerAsCustomer(){

    }
    private void registerAsAdmin() {
        String time = String.valueOf(System.currentTimeMillis());
        regUser = new Admin(time, firstnameField.getText(), regUsenameField.getText(), regPasswordField.getText());

        regUser.encryptPassword();
        List<User> users=readSErializedFile("AdminDB.ser");
        serializeToFile("AdminDB.ser",users);
    }
    private ArrayList<User> readSErializedFile(String path)  {
       ArrayList<User>users=new ArrayList<>();
        FileInputStream fis= null;
        try {

            fis = new FileInputStream(this.getClass().getResource(path).getPath());
            ObjectInputStream ois=new ObjectInputStream(fis);
            System.out.println("REading File...");
            while(fis.available()!=-1){
                User user= (User) ois.readObject();
                users.add(user);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;

    }
    private void serializeToFile(String path,List<User>users) {
        try {
            FileOutputStream fos = new FileOutputStream(this.getClass().getResource(path).getPath());
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            for(User user:users){
                oos.writeObject(user);
                oos.reset();

            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

    }

    public void createUserDatabase(String name, String username, String password){
        try {
            FileWriter fr = new FileWriter("userDatabase.txt", true);
            BufferedWriter bw = new BufferedWriter(fr);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(name + "," + username + "," + password + "," + user.size()+1);
            pw.flush();

            fr.close();
            bw.close();
            pw.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private boolean validate(String name, String username, String password){
        if(new PasswordValidator(1).Validate(password)){
            return validateRegister(username);
        }
        return true;
    }

    public boolean validateRegister(String username){
        //check text file to see if the specified log in exists already
        System.out.println("does it get here? 1");
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
                //username taken
                return false;

            } else {
                //username not taken, unique
                msgr.setText("Success");
                System.out.println(username);
                System.out.println(list);
                return true;
            }
        }

        System.out.println("does it fail 1");
        return false;
    }
}

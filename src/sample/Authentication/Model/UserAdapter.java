package sample.Authentication.Model;

import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserAdapter {
    private final SimpleStringProperty username;
    private final SimpleStringProperty name;
    private final SimpleStringProperty id;
    private final SimpleStringProperty created;
    public UserAdapter(User user){
        username=new SimpleStringProperty(user.getUsername());
        name=new SimpleStringProperty(user.getName());
        id=new SimpleStringProperty(user.getId());
        created=new SimpleStringProperty(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(Long.parseLong(user.getId())));

    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getCreated() {
        return created.get();
    }

    public SimpleStringProperty createdProperty() {
        return created;
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}

package sample.Authentication.Model;
import java.io.Serializable;

public abstract class User implements Serializable {

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected AccountType type;

    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(String id, String name, String username, String password, AccountType type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public abstract void encryptPassword();
    public abstract void decryptPassword();
}

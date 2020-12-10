package sample.Authentication.Model;

public class Admin extends User{

    private Security security;

    public Admin(String id, String name, String username, String password) {
        super(id, name, username, password);
    }

    public Admin(String id, String name, String username) {
        super(id, name, username);
    }

    @Override
    public void encryptPassword() {
        security = new Encryption();
        this.password = security.layerTwo(password);
        this.password = security.layerOne(password);
        this.password = security.layerTwo(password);
        this.password = security.layerOne(password);
        this.password = security.layerTwo(password);
    }

    @Override
    public void decryptPassword() {
        security = new Decryption();
        this.password = security.layerTwo(password);
        this.password = security.layerOne(password);
        this.password = security.layerTwo(password);
        this.password = security.layerOne(password);
        this.password = security.layerTwo(password);

    }
}

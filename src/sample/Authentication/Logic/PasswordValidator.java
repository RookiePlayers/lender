package sample.Authentication.Logic;

public class PasswordValidator extends Validator{
    private final String passPattern;

    public PasswordValidator(int version) {
        switch (version){
            case 0: this.passPattern = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}"; break;
            default: this.passPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}"; break;
        }
    }

    @Override
    public boolean Validate(String s) {
        return s.matches(passPattern);
    }
}

import java.util.regex.Pattern;

public class EmailValidator {

    public boolean validate(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("invalid email");
            return false;
        }
        String regEx = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(email).matches();
    }
}

package registrationAndLogin.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]{2,5}+)*(\\.[A-Za-z]{2,5})$";
    private final static Pattern EMAIL_COMPILED_PATTERN = Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String emailStr) {
        Matcher matcher = EMAIL_COMPILED_PATTERN.matcher(emailStr);
        return matcher.find();
    }

}

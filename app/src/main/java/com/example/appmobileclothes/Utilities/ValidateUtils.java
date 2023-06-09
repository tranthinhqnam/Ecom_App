package com.example.appmobileclothes.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidLenght(String info){
        return info.length() >= 6;
    }
    public static boolean isValidConfirmPassword(String confirmPass,String pass){
        return confirmPass.equals(pass);
    }
}
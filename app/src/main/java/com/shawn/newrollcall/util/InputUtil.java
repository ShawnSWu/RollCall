package com.shawn.newrollcall.util;

import java.util.regex.Pattern;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public final class InputUtil {

    public static boolean checkEmail(String email){
        Pattern EMAIL_PATTERN = Pattern.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean checkEmptyAndLength(String str,int limitLength){
        return str.length() >= limitLength && !str.contains(" ");
    }

}

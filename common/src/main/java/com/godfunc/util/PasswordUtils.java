package com.godfunc.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author godfunc
 * 密码加密与验证
 */
public class PasswordUtils {

    public static String encode(String str) {
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    public static boolean matches(String password, String encodePassword) {
        return BCrypt.checkpw(password, encodePassword);
    }
}

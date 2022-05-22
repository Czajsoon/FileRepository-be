package psk.project.FileRepository.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public final class PasswordHash {

    private PasswordHash(){}

    private static final String SALT = "$2a$10$D4Q.Ig8qZRsbtRGkuvzwf.fmd";

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, SALT);
    }

    public static boolean checkPassword(String password,String hashedPassword){
        return hashPassword(password).equals(hashedPassword);
    }
}

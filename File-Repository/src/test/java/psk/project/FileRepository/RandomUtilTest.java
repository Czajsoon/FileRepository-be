package psk.project.FileRepository;

import net.bytebuddy.utility.RandomString;

import java.util.Random;
import java.util.UUID;

public class RandomUtilTest {

    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

    public static String randomString(int length) {
        return RandomString.make(length);
    }

    public static Long randomLong() {
        Random r = new Random();
        return r.nextLong(100000);
    }

    public static byte[] randomByteArray() {
        byte[] bytes = new byte[20];
        Random r = new Random();
        r.nextBytes(bytes);
        return bytes;
    }
}

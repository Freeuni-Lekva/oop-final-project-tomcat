package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {
    private static final String ALGORITHM = "SHA-1";
    private static final int SALT_LENGTH = 16;

    /**
     * Computes the SHA-1 hash code for the given password.
     */
    public static String getPasswordCode(String pass, String salt){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(hexToArray(salt));
            byte[] passwordBytes = pass.getBytes();
            messageDigest.update(passwordBytes);
            byte[] resultBytes = messageDigest.digest();
            return hexToString(resultBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param bytes byte array
     * @return returns a hex String with 2 chars for each byte in the array.
     */
    private static String hexToString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (int currValue : bytes) {
            currValue = currValue & 0xff;  // remove higher bits, sign
            if (currValue<16) {
                hexString.append('0'); // leading 0
            }
            hexString.append(Integer.toString(currValue, 16));
        }
        return hexString.toString();
    }

    public static String generatePasswordSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] resultSalt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(resultSalt);
        return hexToString(resultSalt);
    }

    /**
     *
     * @param hex a String of hex byte values
     * @return returns a byte[] array of those values, one byte value -128..127
     *  for each 2 chars.
     */
    public static byte[] hexToArray(String hex) {
        byte[] result = new byte[hex.length()/2];
        for (int i=0; i<hex.length(); i+=2) {
            result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
        }
        return result;
    }

}

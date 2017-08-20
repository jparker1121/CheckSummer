package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

    public static String getChecksum(String algorithm, String file) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            FileInputStream fis = new FileInputStream(new File(file));
            DigestInputStream dis = new DigestInputStream(fis,md);

            byte[] buffer = new byte[md.getDigestLength()];
            while (dis.read(buffer) != -1) {  }

            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
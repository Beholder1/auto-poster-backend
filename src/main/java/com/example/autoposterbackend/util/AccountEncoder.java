package com.example.autoposterbackend.util;

import com.example.autoposterbackend.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class AccountEncoder {
    private final AppConfig appConfig;
    private final static String HEX = "0123456789ABCDEF";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    private static final byte[] IV = new byte[16];
    static {
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
    }

    public String decrypt(String encrypted) throws Exception {
        byte[] rawKey = getRawKey(appConfig.getPasswordSeed().getBytes(StandardCharsets.UTF_8));
        byte[] iv = toByte(encrypted.substring(0, 32));
        byte[] encryptedBytes = toByte(encrypted.substring(32));
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(rawKey, "AES"), new IvParameterSpec(iv));
        byte[] result = cipher.doFinal(encryptedBytes);
        return new String(result, StandardCharsets.UTF_8);
    }

    private byte[] getRawKey(byte[] seed) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        sr.setSeed(seed);
        keyGenerator.init(128, sr);
        return keyGenerator.generateKey().getEncoded();
    }

    public byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    public String encrypt(String cleartext) throws Exception {
        byte[] rawKey = getRawKey(appConfig.getPasswordSeed().getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(rawKey, "AES"), new IvParameterSpec(IV));
        byte[] result = cipher.doFinal(cleartext.getBytes(StandardCharsets.UTF_8));
        return toHex(IV) + toHex(result);
    }

    public String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (byte b : buf) {
            appendHex(result, b);
        }
        return result.toString();
    }

    private void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}

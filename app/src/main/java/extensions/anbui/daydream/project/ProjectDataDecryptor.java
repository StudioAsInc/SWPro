package extensions.anbui.daydream.project;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class ProjectDataDecryptor {

    //Since it is encrypted, it needs to be decrypted before reading.
    public static String decryptProjectFile(String path) {
        try {
            // Readfile
            File file = new File(path);
            if (!file.exists()) {
                Log.e("DecryptError", "File does not exist.");
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] encrypted = new byte[(int) file.length()];
            if (fis.read(encrypted) != encrypted.length) {
                fis.close();
                Log.e("DecryptError", "Error reading file.");
                return "";
            }
            fis.close();

            // Key and IV
            byte[] key = Configs.encryptionKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec iv = new IvParameterSpec(key);

            // AES Decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(encrypted);
            return new String(original, StandardCharsets.UTF_8);

        } catch (Exception e) {
            Log.e("DecryptError", "Decryption failed: " + e.getMessage(), e);
            return "";
        }
    }

    public static byte[] encryptRaw(String plain) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec sk = new SecretKeySpec(Configs.encryptionKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(Configs.encryptionKey.getBytes());
        c.init(Cipher.ENCRYPT_MODE, sk, iv);
        return c.doFinal(plain.getBytes(StandardCharsets.UTF_8));
    }

    public static void saveEncryptedFile(String path, String plain) {
        FileUtils.deleteFile(path);
        try {
            byte[] encrypted = encryptRaw(plain);
            File outFile = new File(path);
            outFile.getParentFile().mkdirs();

            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                fos.write(encrypted);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

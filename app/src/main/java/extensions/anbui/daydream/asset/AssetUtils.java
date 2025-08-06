package extensions.anbui.daydream.asset;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AssetUtils {
    public static void unzipFromAssets(Context context, String assetZipPath, String outputDir) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(assetZipPath);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(inputStream));
            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(outputDir, ze.getName());
                if (ze.isDirectory()) {
                    file.mkdirs();
                } else {
                    File parent = file.getParentFile();
                    if (!parent.exists()) parent.mkdirs();

                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buffer = new byte[4096];
                    int count;
                    while ((count = zis.read(buffer)) != -1) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                }
                zis.closeEntry();
            }

            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

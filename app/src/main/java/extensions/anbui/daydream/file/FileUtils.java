package extensions.anbui.daydream.file;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class FileUtils {

    //Get the internal storage directory.
    public static String getInternalStorageDir() {
        File storageDir = Environment.getExternalStorageDirectory();
        return storageDir.getAbsolutePath();
    }

    //Is file exist.
    public static boolean isFileExist(String path) {
        if (path == null || path.isEmpty()) return false;
        File file = new File(path);
        return file.exists();
    }

    //Get file path from uri.
    public static String getFilePathFromUri(Context context, Uri uri) {
        String filePath = null;
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {MediaStore.Files.FileColumns.DATA};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
                    filePath = cursor.getString(index);
                }
            } catch (Exception e) {
                System.err.println("Error getting file path: " + e.getMessage());
            } finally {
                if (cursor != null)
                    cursor.close();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();
        }
        return filePath;
    }

    public static String readTextFile(String path) {
        if (!isFileExist(path)) return "";

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }

    public static void writeTextFile(String path, String content) {
        File file = new File(path);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) return;
        }

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
            Log.i("FileUtils", "writeTextFile: " + path);
        } catch (IOException e) {
            Log.e("FileUtils", "writeTextFile: " + e.getMessage());
        }
    }

    public static void copyDirectory(File sourceDir, File destDir) throws IOException {
        if (!destDir.exists()) {
            if(!destDir.mkdirs()) return;
        }

        for (File file : sourceDir.listFiles()) {
            File destFile = new File(destDir, file.getName());
            if (file.isDirectory()) {
                copyDirectory(file, destFile);
            } else {
                copyFile(file.getPath(), destFile.getPath());
            }
        }
    }

    public static void copyFile(String source, String dest) {
        File sourceFile = new File(source);
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            System.err.println("Source file not found: " + source);
            return;
        }

        try {
            File destFile = new File(dest);

            if (destFile.exists() && destFile.isDirectory() || dest.endsWith("/")) {
                destFile = new File(destFile, sourceFile.getName());
            }

            File parentDir = destFile.getParentFile();
            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                System.err.println("Failed to create directory: " + parentDir);
                return;
            }

            try (InputStream in = new FileInputStream(sourceFile);
                 OutputStream out = new FileOutputStream(destFile)) {

                byte[] buffer = new byte[8192];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }

        } catch (Exception e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }

    public static void moveAFile(String from, String to) {
        File filefrom = new File(from);
        File finalTarget = filefrom.isDirectory() ? new File(to, filefrom.getName()) : new File(to);

        File parentDir = finalTarget.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) return;

        if (filefrom.renameTo(finalTarget)) {
            Log.d("FileUtils", "Moved " + from + " to " + to);
            return;
        }

        try {
            if (filefrom.isDirectory()) {
                copyDirectory(filefrom, finalTarget);
                deleteRecursive(filefrom);
            } else {
                copyFile(filefrom.getAbsolutePath(), finalTarget.getAbsolutePath());
                deleteRecursive(filefrom);
            }
            Log.d("FileUtils", "Moved by copy+delete!");
        } catch (Exception e) {
            Log.e("FileUtils", "Failed to move: " + e.getMessage());
        }
    }

    public static void deleteFile(String path) {
        if (!isFileExist(path)) return;

        File file = new File(path);
        file.delete();
    }

    public static boolean deleteRecursive(File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            for (File child : fileOrDir.listFiles()) {
                deleteRecursive(child);
            }
        }
        return fileOrDir.delete();
    }

    public static void getFileListInDirectory(String path, ArrayList<String> list) {
        File dir = new File(path);
        if (!dir.exists() || dir.isFile()) return;

        File[] listFiles = dir.listFiles();
        if (listFiles == null || listFiles.length <= 0) return;

        if (list == null) return;
        list.clear();
        for (File file : listFiles) {
            list.add(file.getAbsolutePath());
        }
    }
}

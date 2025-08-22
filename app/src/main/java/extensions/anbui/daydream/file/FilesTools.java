package extensions.anbui.daydream.file;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesTools {
    public static String TAG = "FilesTools";

    //Copy folder
    public static void startCopy(String source, String target) throws IOException {
        String finalTarget = target;
        Path source1 = Path.of(source);
        if (Files.isDirectory(source1)) {
            finalTarget = target + "/" + source1.getFileName();
        }
        copyFileOrDirectory(source1, Path.of(finalTarget));
    }

    //Copy the contents inside the folder
    public static void copyFileOrDirectory(Path source, Path target) throws IOException {
        if (Files.isDirectory(source)) {
            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                    Path newDir = target.resolve(source.relativize(dir));
                    try {
                        Files.copy(dir, newDir, StandardCopyOption.REPLACE_EXISTING);
                    } catch (FileAlreadyExistsException e) {
                        System.out.println("Directory already exists: " + newDir);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else if (Files.isRegularFile(source)) {
            Path targetFile = target;
            if (Files.isDirectory(target)) {
                targetFile = target.resolve(source.getFileName());
            }
            Files.copy(source, targetFile, StandardCopyOption.REPLACE_EXISTING);
        } else {
            throw new IOException("Source is neither a directory nor a file: " + source);
        }
    }

    public static void deleteFileOrDirectory(Path path) throws IOException {
        if (!Files.exists(path)) return;

        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            Files.delete(path);
        }
    }

    public static void openFolder(Activity activity, String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            Log.e(TAG, "openFolder: Folder not found!");
            return;
        }

        Uri uri = FileProvider.getUriForFile(
                activity,
                activity.getPackageName() + ".provider",
                folder
        );

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "resource/folder");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "openFolder: " + e.getMessage());
        }
    }

    public static void openFolderSAF(Activity activity, String folderPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(folderPath));
        intent.setDataAndType(uri, DocumentsContract.Document.MIME_TYPE_DIR);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "openFolderSAF: " + e.getMessage());
        }
    }

}

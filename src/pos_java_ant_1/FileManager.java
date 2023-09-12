/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos_java_ant_1;

/**
 *
 * @author KAVI
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManager {

    public void createDirectories(String parent, String... subfolders) {
        File parentDir = new File(parent);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        for (String subfolder : subfolders) {
            File subDir = new File(parent, subfolder);
            if (!subDir.exists()) {
                subDir.mkdirs();
            }
        }
    }

    public void copyFile(String sourcePath, String destinationPath) {
    Path source = Paths.get(sourcePath);
    Path destination = Paths.get(destinationPath);

    try {
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.*;
import java.util.zip.*;

// https://github.com/netology-code/jd-homeworks/blob/master/files/task1/README.md

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    static int saveCount = 1;
    static Date time = new Date();

    public static void main(String[] args) {

        List<String> folders1 = Arrays.asList("src", "res", "savegames", "temp", "src/main", "src/test", "res/drawables", "res/vectors", "res/icons");
        List<String> files = Arrays.asList("src/Main.java", "src/Utils.java", "temp/temp.txt");
        folders1.forEach(f -> folderCreation(f));
        files.forEach(f -> fileCreation(f));
        tempWrite(sb);

        List<GameProgress> savesList = Arrays.asList(
                new GameProgress(12, 1, 11, 5.7),
                new GameProgress(1, 0, 3, 0.5),
                new GameProgress(13, 2, 5, 1.2));
        List<String> savesPath = new ArrayList<>();
        savesList.forEach(s -> {
            saveCreation(s);
            savesPath.add("C://Users//user/Games/savegames/save" + saveCount + ".dat");
            saveCount++;
        });
        String zipPath = "C://Users//user/Games/savegames/zip_save.zip";
        zipFiles(zipPath, savesPath);
        savesPath.forEach(s -> deleteSave(s));

    }

    public static void deleteSave(String filePath) {
        try {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted");
            } else {
                System.out.println("Sorry, unable to delete the file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String zipPath, List<String> savesPath) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
            savesPath.forEach(s -> {
                ZipEntry e = new ZipEntry(s);
                try {
                    FileInputStream fis = new FileInputStream(s);
                    zout.putNextEntry(e);
                    byte[] data = new byte[fis.available()];
                    fis.read(data);
                    zout.write(data);
                    zout.closeEntry();
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void saveCreation(GameProgress data) {
        try (FileOutputStream out = new FileOutputStream("C://Users//user/Games/savegames/save" + saveCount + ".dat");
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(data);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void tempWrite(StringBuilder str) {
        try (FileWriter writer = new FileWriter("C://Users//user/Games/temp/temp.txt", false)) {
            writer.write(str.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void fileCreation(String filePath) {
        File file = new File("C://Users//user/Games/" + filePath);
        try {
            if (file.createNewFile()) {
                sb.append("File     >>>    " + file.getPath() + "  <<< has been created " + "(" + time + ")\n");
            } else {
                sb.append("File     >>>    " + file.getPath() + "  <<< already exists " + "(" + time + ")\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void folderCreation(String path) {
        File dir = new File("C://Users//user/Games/" + path);
        if (dir.mkdirs()) {
            sb.append("Folder   >>>   " + dir.getPath() + "    <<< has been created " + "(" + time + ")\n");
        } else if (dir.exists()) {
            sb.append("Folder   >>>   " + dir.getPath() + "    <<< already exists " + "(" + time + ")\n");
        } else {
            sb.append("Folder   >>>   " + dir.getPath() + "    <<< has not been created " + "(" + time + ")\n");
        }
    }
}

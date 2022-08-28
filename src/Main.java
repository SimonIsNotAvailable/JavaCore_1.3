import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.*;

// https://github.com/netology-code/jd-homeworks/blob/master/files/task1/README.md

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    static int saveCount = 1;
    public static void main(String[] args) {

        List<String> folders1 = Arrays.asList("src", "res", "savegames", "temp", "src/main", "src/test", "res/drawables", "res/vectors", "res/icons");
        List<String> files = Arrays.asList("src/Main.java", "src/Utils.java", "temp/temp.txt");

        folders1.forEach(f -> folderCreation(f));
        files.forEach(f -> fileCreation(f));

        List<GameProgress> savesList = Arrays.asList(
                new GameProgress(12, 1, 11, 5.7),
                new GameProgress(1, 0, 3, 0.5),
                new GameProgress(13, 2, 5, 1.2));

        savesList.forEach(s -> {

            saveCreation(s);
            saveCount++;
        });

        tempWrite(sb);

//        System.out.println(sb);

    }

    public static void saveCreation (GameProgress data) {
        try (FileOutputStream out = new FileOutputStream("C://Users//user/Games/savegames/save" + saveCount + ".dat");
             ObjectOutputStream oos = new ObjectOutputStream(out)){
            oos.writeObject(data);
        } catch (IOException ex) {
            System.out.println (ex.getMessage());
        }
    }

    public static void tempWrite (StringBuilder str) {
        try (FileWriter writer = new FileWriter ("C://Users//user/Games/temp/temp.txt", false)) {
            writer.write (str.toString());
        } catch (IOException ex) {
            System.out.println (ex.getMessage());
        }
    }

    public static void fileCreation (String filePath) {
        File file = new File ("C://Users//user/Games/" + filePath);
        Date time = new Date();
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

    public static void folderCreation (String path) {
        File dir = new File ("C://Users//user/Games/" + path);
        Date time = new Date();
        if(dir.mkdirs()) {
            sb.append("Folder   >>>   " + dir.getPath() + "    <<< has been created " + "(" + time + ")\n");
        } else if(dir.exists()) {
            sb.append("Folder   >>>   " + dir.getPath() + "    <<< already exists " + "(" + time + ")\n");
        }
        else {
            sb.append("Folder   >>>   " + dir.getPath() + "    <<< has not been created " + "(" + time + ")\n");
        }
    }
}

package BinaryFileManager;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        File currentDir = new File(projectPath);
        checkDirectoryContents(currentDir);


    }

    public static void checkDirectoryContents(File dir) {
        File[] files = dir.listFiles();
        boolean studentFile = true;
        boolean lecturerFile = true;
        boolean courseFile = true;
        boolean testFile = true;


        for (File file : files) {

            if (file.getName().contains("Student.bin")) {
                studentFile = false;
            }
            else if (file.getName().contains("Lecturer.bin")) {
                lecturerFile = false;
            }
            else if (file.getName().contains("Course.bin")) {
                courseFile = false;
            }
            else if (file.getName().contains("test.bin")) {
                testFile = false;
            }
        }

        if (!testFile){
            ArrayList<String> strArr = new ArrayList<String>();
            strArr.add("Volvo");
            strArr.add("BMW");
            strArr.add("Ford");
            strArr.add("Mazda");
            WriterManager FManager = new WriterManager("test.bin",strArr);
        }

    }
}

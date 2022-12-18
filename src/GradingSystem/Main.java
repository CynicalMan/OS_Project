package GradingSystem;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        File currentDir = new File(projectPath);
        checkDirectoryContents(currentDir);

        Lecturer lecturer1 = new Lecturer();
        lecturer1.addLecturer();
        Course c1 = new Course(1,"OS2",lecturer1, 3);
        c1.addCourse();
        Student student1 = new Student();
        student1.addStudent();
        Student student2 = new Student();
        student2.addStudent();
        Student student3 = new Student();
        student3.addStudent();
        Student student4 = new Student();
        student4.addStudent();
        Student student5 = new Student();
        student5.addStudent();
        RegisteredCourses rg1 = new RegisteredCourses();
        rg1.addRegisteredCourse();
        RegisteredCourses rg2 = new RegisteredCourses();
        rg2.addRegisteredCourse();
        RegisteredCourses rg3 = new RegisteredCourses();
        rg3.addRegisteredCourse();
        RegisteredCourses rg4 = new RegisteredCourses();
        rg4.addRegisteredCourse();
        RegisteredCourses rg5 = new RegisteredCourses();
        rg5.addRegisteredCourse();
    }

    public static void checkDirectoryContents(File dir) {
        File[] files = dir.listFiles();
        boolean studentFile = true;
        boolean lecturerFile = true;
        boolean courseFile = true;
        boolean registeredCourseFile = true;

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
            else if (file.getName().contains("RegisteredCourses.bin")) {
                registeredCourseFile = false;
            }
        }


        if (studentFile)
        {
            Student x = new Student();
            x.commitToFile();
        }
        if (lecturerFile) {
            Lecturer x = new Lecturer();
            x.commitToFile();
        }
        if (courseFile) {
            Course x = new Course();
            x.commitToFile();
        }
        if (registeredCourseFile) {
            RegisteredCourses x = new RegisteredCourses();
            x.commitToFile();
        }
    }



}
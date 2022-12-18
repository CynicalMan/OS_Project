package GradingSystem;

import BinaryFileManager.ReaderManager;
import BinaryFileManager.WriterManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable,RManager {
    private int id;
    private String name;
    private Lecturer lect;
    private int creditHours;

    //Bin File Manager
    private final String CoursesFileName = "Course.bin";
    public static ArrayList<Course> Courses = new ArrayList<Course>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    public Lecturer getLect() {
        return lect;
    }

    public Course(){}

    public Course(int id, String name, Lecturer lect, int creditHours) {
        this.id = id;
        this.name = name;
        this.lect = lect;
        this.creditHours = creditHours;
    }

    public boolean addCourse(){
        loadFromFile();
        Courses.add(this);
        return commitToFile();
    }
    public boolean commitToFile(){
        WriterManager wm = new WriterManager(CoursesFileName,Courses);
        Thread write = new Thread(wm);
        write.setName("threadWrite to registered courses");
        write.start();
        return  wm.isWritten();
    }

    @Override
    public void loadFromFile() {
        ReaderManager rm = new ReaderManager(CoursesFileName);
        Thread read = new Thread(rm);
        read.setName("threadRead to courses");
        read.start();
        if (rm.getRes() == null){
            System.out.println("Cant read from courses file");
        }else{
            Courses = (ArrayList<Course>) rm.getRes();
        }
    }
}

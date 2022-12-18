package GradingSystem;

import BinaryFileManager.Manager;
import BinaryFileManager.ReaderManager;
import BinaryFileManager.WriterManager;

import java.io.Serializable;
import java.util.ArrayList;

public class RegisteredCourses implements Serializable,RManager {

    private int id;
    private int grade;
    private Student stud;
    private Course sub;

    //Bin File Manager
    private final String regCoursesFileName = "RegisteredCourses.bin";
    public static ArrayList<RegisteredCourses> RegCourses = new ArrayList<RegisteredCourses>();

    public int getId() {return id;}
    public int getGrade() {
        return grade;
    }

    public Student getStud() {
        return stud;
    }

    public Course getSub() {
        return sub;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setStud(Student stud) {
        this.stud = stud;
    }

    public void setSub(Course sub) {
        this.sub = sub;
    }

    public RegisteredCourses() {}

    public RegisteredCourses(int id, int grade, Student stud, Course sub) {
        this.id = id;
        this.grade = grade;
        this.stud = stud;
        this.sub = sub;
    }

    public ArrayList<RegisteredCourses> returnStudentCourses(Student stud){
        ArrayList<RegisteredCourses> arr = new ArrayList<RegisteredCourses>();
        loadFromFile();
        for (int i = 0; i < RegCourses.size(); i++) {
            if (RegCourses.get(i).stud.getId() == stud.getId()) {
                arr.add(RegCourses.get(i));
            }
        }
        return arr;
    }

    public ArrayList<RegisteredCourses> returnStudents(Lecturer lect){
        ArrayList<RegisteredCourses> arr = new ArrayList<RegisteredCourses>();
        loadFromFile();
        for (int i = 0; i < RegCourses.size(); i++) {
            if (RegCourses.get(i).sub.getLect().getId() == lect.getId()) {
                arr.add(RegCourses.get(i));
            }
        }
        return arr;
    }

    public boolean addRegisteredCourse(){
        loadFromFile();
        RegCourses.add(this);
        return commitToFile();
    }

    public boolean commitToFile(){
        WriterManager wm = new WriterManager(regCoursesFileName,RegCourses);
        Thread write = new Thread(wm);
        write.setName("threadWrite to registered courses");
        write.start();
        //wait till thread is dead
        while(write.isAlive()) {}
        return  wm.isWritten();
    }

    @Override
    public void loadFromFile() {
        ReaderManager rm = new ReaderManager(regCoursesFileName);
        Thread read = new Thread(rm);
        read.setName("threadRead to registered courses");
        read.start();
        //wait till thread is dead
        while(read.isAlive()) {}
        if (rm.getRes() == null){
            System.out.println("Cant read from registered courses file");
        }else{
            RegCourses = (ArrayList<RegisteredCourses>) rm.getRes();
        }
    }
    @Override
    public String toString() {
        return "\nID: " + id + " Grade:" + grade;
    }

}

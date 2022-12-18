package GradingSystem;

import BinaryFileManager.ReaderManager;
import BinaryFileManager.WriterManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Lecturer implements Login, Serializable ,RManager{
    private int id;
    private String name;
    private String password;

    //Bin File Manager
    private final String lecturerFileName = "Lecturer.bin";
    public static ArrayList<Lecturer> Lecturers = new ArrayList<Lecturer>();

    public Lecturer(){}
    public Lecturer(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private Lecturer getLecturerById(int  Id) {
        for (int i = 0; i < Lecturers.size(); i++) {
            if (Lecturers.get(i).id == Id){
                return Lecturers.get(i);
            }
        }
        return null;
    }
    
    public void displayStudents(){
        ArrayList<RegisteredCourses> arr = new ArrayList<RegisteredCourses>();
        RegisteredCourses regCourses = new RegisteredCourses();
        arr = regCourses.returnStudents(this);

        //display arr
        System.out.println("array :   ");
        System.out.println(arr);
        System.out.println("array end :   ");
    }

    @Override
    public boolean login(int id, String password) {
        loadFromFile();
        Lecturer lect = new Lecturer();
        lect = lect.getLecturerById(id);
        return lect != null && Objects.equals(lect.getPassword(), password);
    }

    public boolean addLecturer(){
        loadFromFile();
        Lecturers.add(this);
        return commitToFile();
    }

    public boolean commitToFile(){
        WriterManager wm = new WriterManager(lecturerFileName,Lecturers);
        Thread write = new Thread(wm);
        write.setName("threadWrite to lecturer");
        write.start();
        return  wm.isWritten();
    }

    @Override
    public void loadFromFile() {
        ReaderManager rm = new ReaderManager(lecturerFileName);
        Thread read = new Thread(rm);
        read.setName("threadRead to lecturer");
        read.start();
        if (rm.getRes() == null){
            System.out.println("Cant read from lecturer file");
        }else{
            Lecturers = (ArrayList<Lecturer>) rm.getRes();
        }
    }
}

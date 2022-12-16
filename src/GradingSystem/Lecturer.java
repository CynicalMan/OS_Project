package GradingSystem;

public class Lecturer {
    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void updateGrade(int grade, RegisteredCoursse reSub) {
       reSub.setGrade(grade);
    }


}

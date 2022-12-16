package GradingSystem;

public class RegisteredCoursse {

    private int id;
    private int grade;
    private Student stud;
    private Course sub;

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


}

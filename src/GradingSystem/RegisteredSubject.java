package src.GradingSystem;

public class RegisteredSubject {

    private int id;
    private int grade;
    private Student stud;
    private Subject sub;

    public int getGrade() {
        return grade;
    }

    public Student getStud() {
        return stud;
    }

    public Subject getSub() {
        return sub;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setStud(Student stud) {
        this.stud = stud;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }


}

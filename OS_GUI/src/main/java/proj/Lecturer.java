package proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proj.GradingSystem.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Lecturer {
    public Lecturer(){}

    @FXML
    private Button exitBtn;
    @FXML
    private Button backToLogin;
    @FXML
    private Button updateBtn;
    @FXML
    private TableView<RegisteredCourses> LecturerTable;
    @FXML
    private TableColumn<RegisteredCourses,Integer> LecturerTableColumn1;
    @FXML
    private TableColumn<RegisteredCourses,String> LecturerTableColumn2;
    @FXML
    private TableColumn<RegisteredCourses, Integer> LecturerTableColumn3;
    @FXML
    private TextField studentIdField2;
    @FXML
    private TextField studentNewGrade;
    @FXML
    private TextField studentIdField1;

//    RegisteredCourses rg = new RegisteredCourses();
//    ObservableList<RegisteredCourses> list = this.regCourses();
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        LecturerTableColumn1.setCellValueFactory(new PropertyValueFactory<RegisteredCourses,Integer>("Student ID"));
//        LecturerTableColumn2.setCellValueFactory(new PropertyValueFactory<RegisteredCourses,String>("Student Name"));
//        LecturerTableColumn3.setCellValueFactory(new PropertyValueFactory<RegisteredCourses,Integer>("Course Grade"));
//
//        LecturerTable.setItems(list);
//    }

    public ObservableList<RegisteredCourses> regCourses(){
        Login lg = new Login();
        proj.GradingSystem.Lecturer l;
        l = lg.getLect();
        ArrayList<RegisteredCourses> rg;
        rg = l.displayStudents(lg.getLect().getId());
        ObservableList<RegisteredCourses> rgs = FXCollections.observableArrayList();
        rgs.addAll(rg);
        return rgs;
    }
}

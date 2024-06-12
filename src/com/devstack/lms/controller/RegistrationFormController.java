package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.Course;
import com.devstack.lms.entity.Student;
import com.devstack.lms.view.tm.CourseTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationFormController {
    public AnchorPane context;
    public TextField txtStudentName;
    public TextField txtCourseName;
    public TextField txtStudentEmail;
    public TextField txtCourseFee;
    public Button btnSave;
    public ComboBox cmbCourse;
    public ComboBox cmbStudent;
    public RadioButton rBtnCash;
    public ToggleGroup paymentType;

    public void initialize() {
        loadAllCourse();
        loadAllStudent();

        cmbCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCourseDetails((String) newValue);
        });

        cmbStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setStudentDetails((String) newValue);
        });
    }

    private void setStudentDetails(String newValue) {
        String[] splitData = newValue.split("\\|");
        String studentId = splitData[0].trim();

        try{
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
            Student student = databaseAccessCode.findStudent(studentId);

            if(student == null) {
                new Alert(Alert.AlertType.WARNING, "Student Not Found", ButtonType.OK).show();
                return;
            }

            txtStudentName.setText(student.getStudentName());
            txtStudentEmail.setText(student.getEmail());

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllStudent() {
        ObservableList<String> cmbObservableList = null;

//        clearField();

        try{

            DatabaseAccessCode db = new DatabaseAccessCode();
            cmbObservableList = FXCollections.observableArrayList(db.findAllStudents().stream().map(e->e.getStudentId()+" | "+e.getStudentName()).collect(Collectors.toList()));
            cmbStudent.setItems(cmbObservableList);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCourseDetails(String newValue) {
        String[] splitData = newValue.split("\\|");
        String courseId = splitData[0].trim();

        try{
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
            Course course = databaseAccessCode.findCourse(courseId);

            if(course == null) {
                new Alert(Alert.AlertType.WARNING, "Course Not Found", ButtonType.OK).show();
                return;
            }

            txtCourseName.setText(course.getCourseName());
            txtCourseFee.setText(String.valueOf(course.getCourseFee()));

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllCourse() {

        ObservableList<String> cmbObservableList = null;

//        clearField();

        try{

            DatabaseAccessCode db = new DatabaseAccessCode();
            cmbObservableList = FXCollections.observableArrayList(db.findAllCourse().stream().map(e->e.getCourseId()+" | "+e.getCourseName()).collect(Collectors.toList()));
            cmbCourse.setItems(cmbObservableList);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void registerNowOnAction(ActionEvent actionEvent) {

    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }

    private void clearField(){
        txtStudentName.clear();
        txtCourseName.clear();
        txtStudentEmail.clear();
        txtCourseFee.clear();
        cmbCourse.getItems().clear();
        cmbStudent.getItems().clear();
    }
}

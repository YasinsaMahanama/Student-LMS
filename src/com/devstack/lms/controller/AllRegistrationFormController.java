package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.Course;
import com.devstack.lms.entity.Student;
import com.devstack.lms.view.tm.AllRegistrationTM;
import com.devstack.lms.view.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllRegistrationFormController {

    public ComboBox<String> cmbCourse;
    public TableView<AllRegistrationTM> tblAllRegistration;
    public AnchorPane context;

    private Course selectedCourse;

    public void initialize() {
        loadAllCourse();

        cmbCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setCourseDetails((String) newValue);
            }
        });

    }

    private void loadAllCourse() {

        ObservableList<String> courseObservableList = null;

        try{

            DatabaseAccessCode db = new DatabaseAccessCode();
            courseObservableList = FXCollections.observableArrayList(db.findAllCourse().stream().map(e->e.getCourseId()+" | "+e.getCourseName()).collect(Collectors.toList()));
            cmbCourse.setItems(courseObservableList);

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
            selectedCourse = databaseAccessCode.findCourse(courseId);

            if(selectedCourse == null) {
                new Alert(Alert.AlertType.WARNING, "Course Not Found", ButtonType.OK).show();
                return;
            }

            //loadAllCourses();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

//    private void loadAllCourses() {
//
//        ObservableList<AllRegistrationTM> tmObservableList = FXCollections.observableArrayList();
//
//        try {
//            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
//
//            List<Student> allStudents = databaseAccessCode.findAllSelectedCourse();
//
//            for(Student student : allStudents) {
//                ButtonBar bar = new ButtonBar();
//                Button deleteButton = new Button("Delete");
//                Button updateButton = new Button("Update");
//                bar.getButtons().addAll(deleteButton, updateButton);
//
//                AllRegistrationTM allRegistrationTM = new AllRegistrationTM(
//                        student.getStudentId(),
//                        student.getStudentName(),
//                        student.getAddress(),
//                        student.getEmail(),
//                        student.getAge(),
//                        bar
//                );
//
//                tmObservableList.add(AllRegistrationTM);
//            }
//            tblAllRegistration.setItems(tmObservableList);
//
//        }catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
//        }
//    }



    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }

    private void clearFields(){

        cmbCourse.setValue(null);

        selectedCourse = null;
    }
}

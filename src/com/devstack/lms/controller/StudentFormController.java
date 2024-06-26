package com.devstack.lms.controller;

import  com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.Student;
import com.devstack.lms.view.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentFormController {
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtEmail;
    public TableView<StudentTM> tblStudents;
    public TableColumn<StudentTM, String> colName;
    public TableColumn<StudentTM, String> colAddress;
    public TableColumn<StudentTM, Integer> colAge;
    public TableColumn<StudentTM, String> colEmail;
    public TableColumn<StudentTM, ButtonBar> colOption;
    public TextField txtSearch;
    public Button btnSave;
    public AnchorPane context;

    private String searchText = "";
    private Student selectedStudent = null ;

    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("bar"));

        loadAllStudents();

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            loadAllStudents();
        });
    }

    private void loadAllStudents() {

        ObservableList<StudentTM> tmObservableList = FXCollections.observableArrayList();

        try {
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();

            List<Student> allStudents = databaseAccessCode.findAllStudents(searchText);

            for(Student student : allStudents) {
                ButtonBar bar = new ButtonBar();
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                bar.getButtons().addAll(deleteButton, updateButton);

                StudentTM studentTM = new StudentTM(
                        student.getStudentId(),
                        student.getStudentName(),
                        student.getAddress(),
                        student.getEmail(),
                        student.getAge(),
                        bar
                );

                deleteButton.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);

                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            DatabaseAccessCode dbAccessCode = new DatabaseAccessCode();
                            boolean isDeleted = dbAccessCode.deleteStudent(studentTM.getStudentId());

                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully").show();
                                loadAllStudents();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Something Went Wrong and try again.").show();
                            }

                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    }
                });

                updateButton.setOnAction(event -> {
                    btnSave.setText("Update Student");

                    selectedStudent = student;

                    txtName.setText(student.getStudentName());
                    txtAddress.setText(student.getAddress());
                    txtAge.setText(String.valueOf(student.getAge()));
                    txtEmail.setText(student.getEmail());
                });

                tmObservableList.add(studentTM);
            }
            tblStudents.setItems(tmObservableList);

        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void saveStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(btnSave.getText().equalsIgnoreCase("Save Student")) {
            try {
                Student student = new Student(
                        UUID.randomUUID().toString(),
                        txtName.getText().trim(),
                        txtAddress.getText().trim(),
                        txtEmail.getText().toLowerCase().trim(),
                        Integer.parseInt(txtAge.getText())
                );
                DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
                boolean isSaved = databaseAccessCode.saveStudent(student);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Student has been Saved!", ButtonType.CLOSE).show();
                    clearFields();
                    loadAllStudents();
                }
                else {
                    new Alert(Alert.AlertType.WARNING, "Try Again.", ButtonType.CLOSE).show();
                }
            }catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        }
        else {
            if(selectedStudent != null){
                try {
                    Student student = new Student(
                            selectedStudent.getStudentId(),
                            txtName.getText().trim(),
                            txtAddress.getText().trim(),
                            txtEmail.getText().toLowerCase().trim(),
                            Integer.parseInt(txtAge.getText())
                    );
                    DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
                    boolean isSaved = databaseAccessCode.updateStudent(student);

                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "Student has been Updated!", ButtonType.CLOSE).show();
                        clearFields();
                        loadAllStudents();
                        btnSave.setText("Save Student");
                    }
                    else {
                        new Alert(Alert.AlertType.WARNING, "Try Again.", ButtonType.CLOSE).show();
                    }
                }catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Please Select a student", ButtonType.CLOSE).show();
            }
        }
    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtAge.clear();
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Student");
        clearFields();
        selectedStudent = null;
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }
}

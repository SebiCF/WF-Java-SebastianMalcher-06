package com.sebastian.teacherView;

import com.sebastian.teacherView.data.ClassSchool;
import com.sebastian.teacherView.data.Teacher;
import com.sebastian.teacherView.dbAccess.TeacherDbAccess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    private TeacherDbAccess dbaccess;
    private List<Teacher> data;

    @FXML
    private TextField teacher_id;
    @FXML
    private TextField teacher_name;
    @FXML
    private TextField teacher_surname;
    @FXML
    private TextField teacher_email;
    @FXML
    private ListView<Teacher> teacher_list;
    @FXML
    private ListView<ClassSchool> teacherclasses_list;


    public void initialize() throws SQLException {

        teacher_list.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener()
        );

        data = getDbData();
        teacher_list.getItems().setAll(data);
        teacher_list.getSelectionModel().selectFirst();

    }

    private class ListSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= data.size())) {

                return; // invalid data
            }

            // set name and desc fields for the selected todo
            Teacher teacher = data.get(new_val.intValue());
            teacher_id.setText((String.valueOf(teacher.getTeacher_id())));
            teacher_name.setText(teacher.getTeacher_name());
            teacher_surname.setText(teacher.getTeacher_surname());
            teacher_email.setText(teacher.getTeacher_email());

            try {
                teacherclasses_list.getItems().setAll(TeacherDbAccess.getInstance().getAllClassesRows(teacher.getTeacher_id()));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    private ObservableList<Teacher> getDbData() {

        List<Teacher> list = null;

        try {
            list = TeacherDbAccess.getInstance().getAllTeacherRows();
        } catch (Exception e) {

            Main.displayException(e);
        }

        assert list != null;
        return FXCollections.observableArrayList(list);
    }
}
